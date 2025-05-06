package br.ufal.ic.p2.jackut.sistema;

import br.ufal.ic.p2.jackut.exceptions.*;
import br.ufal.ic.p2.jackut.usuario.*;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe responsável por gerenciar as funcionalidades relacionadas às comunidades
 * no sistema Jackut, como criação, adição de membros, envio de mensagens, etc.
 *
 * Herda de Gerenciador para reutilizar os mapas de usuários e comunidades,
 * e centralizar comportamentos comuns.
 */
public class GerenciadorDeComunidades extends Gerenciador {

    /**
     * Construtor que recebe os mapas compartilhados de usuários e comunidades
     * e os repassa para a superclasse.
     */
    public GerenciadorDeComunidades(HashMap<String, Usuario> usuarios, HashMap<String, Comunidade> comunidades){
        super(usuarios, comunidades);
    }

    /**
     * Lê e remove a próxima mensagem da fila de mensagens de um usuário.
     *
     * @param id Login do usuário que deseja ler a mensagem
     * @return Conteúdo da mensagem
     * @throws EmptyListException Se o usuário não tiver mensagens pendentes
     */
    public String lerMensagem(String id) throws EmptyListException {
        if (this.getUsuarios().get(id).getMensagens().isEmpty()) {
            throw new EmptyListException("Não há mensagens.");
        }
        return this.getUsuarios().get(id).getMensagens().remove().getConteudo();
    }

    /**
     * Cria uma nova comunidade associada ao usuário da sessão.
     *
     * @param sessao Login do criador da comunidade
     * @param nome Nome da nova comunidade
     * @param descricao Descrição da comunidade
     * @throws CommunityAlreadyExistsException Se já existir uma comunidade com esse nome
     */
    public void criarComunidade(String sessao, String nome, String descricao) throws CommunityAlreadyExistsException {
        if (this.getComunidades().containsKey(nome)) {
            throw new CommunityAlreadyExistsException();
        }

        Comunidade comunidade = new Comunidade(this.getUsuarios().get(sessao), nome, descricao);
        this.getComunidades().put(nome, comunidade);
        this.getUsuarios().get(sessao).getComunidades().add(comunidade);
    }

    /**
     * Retorna a descrição de uma comunidade existente.
     *
     * @param nome Nome da comunidade
     * @return Descrição da comunidade
     * @throws InvalidUserException Se a comunidade não existir
     */
    public String getDescricaoComunidade(String nome) throws InvalidCommunityException {
        if (!this.getComunidades().containsKey(nome)) {
            throw new InvalidCommunityException();
        }
        return this.getComunidades().get(nome).getDescricao();
    }

    /**
     * Retorna o login do dono (criador) da comunidade.
     *
     * @param nome Nome da comunidade
     * @return Login do dono
     * @throws InvalidCommunityException Se a comunidade não existir
     */
    public String getDonoComunidade(String nome) throws InvalidCommunityException {
        if (!this.getComunidades().containsKey(nome)) {
            throw new InvalidCommunityException();
        }
        return this.getComunidades().get(nome).getDonoLogin();
    }

    /**
     * Retorna uma representação textual dos logins dos membros da comunidade.
     *
     * @param nome Nome da comunidade
     * @return String formatada com os logins dos membros: {login1,login2,...}
     * @throws InvalidCommunityException Se a comunidade não existir
     */
    public String getMembrosComunidade(String nome) throws InvalidCommunityException {
        if (!this.getComunidades().containsKey(nome)) {
            throw new InvalidCommunityException();
        }
        List<String> membros = this.getComunidades().get(nome).getMembros().stream()
                .map(Usuario::getLogin)
                .collect(Collectors.toList());
        return "{" + String.join(",", membros) + "}";
    }

    /**
     * Retorna uma lista das comunidades que o usuário participa.
     *
     * @param login Login do usuário
     * @return String formatada com os nomes das comunidades: {com1,com2,...}
     * @throws InvalidUserException Se o usuário não existir
     */
    public String getComunidades(String login) throws InvalidUserException {
        if (!this.getUsuarios().containsKey(login)) {
            throw new InvalidUserException();
        }
        List<String> comList = this.getUsuarios().get(login).getComunidades().stream()
                .map(Comunidade::getNome)
                .collect(Collectors.toList());
        return "{" + String.join(",", comList) + "}";
    }

    /**
     * Adiciona o usuário da sessão como membro de uma comunidade.
     *
     * @param sessao Login do usuário que deseja entrar
     * @param nome Nome da comunidade
     * @throws InvalidCommunityException Se a comunidade não existir
     * @throws InvalidUserException Se o usuário não existir
     * @throws UserIsAlreadyMemberException Se o usuário já for membro da comunidade
     */
    public void adicionarComunidade(String sessao, String nome) throws Exception {
        if (!this.getComunidades().containsKey(nome)) {
            throw new InvalidCommunityException();
        }
        if (!this.getUsuarios().containsKey(sessao)) {
            throw new InvalidUserException();
        }
        if (this.getComunidades().get(nome).getMembros().contains(this.getUsuarios().get(sessao))) {
            throw new UserIsAlreadyMemberException();
        }

        this.getUsuarios().get(sessao).getComunidades().add(this.getComunidades().get(nome));
        this.getComunidades().get(nome).getMembros().add(this.getUsuarios().get(sessao));
    }

    /**
     * Permite que um usuário envie uma mensagem para uma comunidade.
     *
     * @param id Login do usuário remetente
     * @param comunidade Nome da comunidade de destino
     * @param mensagem Texto da mensagem
     * @throws InvalidCommunityException Se a comunidade não existir
     * @throws InvalidUserException Se o usuário não existir
     */
    public void enviarMensagem(String id, String comunidade, String mensagem) throws Exception {
        if (!this.getComunidades().containsKey(comunidade)) {
            throw new InvalidCommunityException();
        }
        if (!this.getUsuarios().containsKey(id)) {
            throw new InvalidUserException();
        }

        for(Usuario usuario: this.getComunidades().get(comunidade).getMembros()){
            usuario.getMensagens().add(new Texto(id, mensagem));
        }
    }
}
