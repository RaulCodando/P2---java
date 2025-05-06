package br.ufal.ic.p2.jackut.sistema;

import br.ufal.ic.p2.jackut.exceptions.*;
import br.ufal.ic.p2.jackut.usuario.*;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe respons�vel por gerenciar as funcionalidades relacionadas �s comunidades
 * no sistema Jackut, como cria��o, adi��o de membros, envio de mensagens, etc.
 *
 * Herda de Gerenciador para reutilizar os mapas de usu�rios e comunidades,
 * e centralizar comportamentos comuns.
 */
public class GerenciadorDeComunidades extends Gerenciador {

    /**
     * Construtor que recebe os mapas compartilhados de usu�rios e comunidades
     * e os repassa para a superclasse.
     */
    public GerenciadorDeComunidades(HashMap<String, Usuario> usuarios, HashMap<String, Comunidade> comunidades){
        super(usuarios, comunidades);
    }

    /**
     * L� e remove a pr�xima mensagem da fila de mensagens de um usu�rio.
     *
     * @param id Login do usu�rio que deseja ler a mensagem
     * @return Conte�do da mensagem
     * @throws EmptyListException Se o usu�rio n�o tiver mensagens pendentes
     */
    public String lerMensagem(String id) throws EmptyListException {
        if (this.getUsuarios().get(id).getMensagens().isEmpty()) {
            throw new EmptyListException("N�o h� mensagens.");
        }
        return this.getUsuarios().get(id).getMensagens().remove().getConteudo();
    }

    /**
     * Cria uma nova comunidade associada ao usu�rio da sess�o.
     *
     * @param sessao Login do criador da comunidade
     * @param nome Nome da nova comunidade
     * @param descricao Descri��o da comunidade
     * @throws CommunityAlreadyExistsException Se j� existir uma comunidade com esse nome
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
     * Retorna a descri��o de uma comunidade existente.
     *
     * @param nome Nome da comunidade
     * @return Descri��o da comunidade
     * @throws InvalidUserException Se a comunidade n�o existir
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
     * @throws InvalidCommunityException Se a comunidade n�o existir
     */
    public String getDonoComunidade(String nome) throws InvalidCommunityException {
        if (!this.getComunidades().containsKey(nome)) {
            throw new InvalidCommunityException();
        }
        return this.getComunidades().get(nome).getDonoLogin();
    }

    /**
     * Retorna uma representa��o textual dos logins dos membros da comunidade.
     *
     * @param nome Nome da comunidade
     * @return String formatada com os logins dos membros: {login1,login2,...}
     * @throws InvalidCommunityException Se a comunidade n�o existir
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
     * Retorna uma lista das comunidades que o usu�rio participa.
     *
     * @param login Login do usu�rio
     * @return String formatada com os nomes das comunidades: {com1,com2,...}
     * @throws InvalidUserException Se o usu�rio n�o existir
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
     * Adiciona o usu�rio da sess�o como membro de uma comunidade.
     *
     * @param sessao Login do usu�rio que deseja entrar
     * @param nome Nome da comunidade
     * @throws InvalidCommunityException Se a comunidade n�o existir
     * @throws InvalidUserException Se o usu�rio n�o existir
     * @throws UserIsAlreadyMemberException Se o usu�rio j� for membro da comunidade
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
     * Permite que um usu�rio envie uma mensagem para uma comunidade.
     *
     * @param id Login do usu�rio remetente
     * @param comunidade Nome da comunidade de destino
     * @param mensagem Texto da mensagem
     * @throws InvalidCommunityException Se a comunidade n�o existir
     * @throws InvalidUserException Se o usu�rio n�o existir
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
