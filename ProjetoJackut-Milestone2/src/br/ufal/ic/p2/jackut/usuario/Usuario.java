package br.ufal.ic.p2.jackut.usuario;

import java.io.Serializable;
import java.util.*;

/**
 * Representa um usuário na plataforma Jackut.
 * Armazena informações pessoais, amigos, pedidos de amizade e recados.
 */
public class Usuario implements Serializable {
    // Senha do usuário
    private String senha;
    // Login do usuário
    private String login;
    // Perfil associado ao usuário
    private Perfil perfil = new Perfil();
    // Conjunto de pedidos de amizade recebidos
    private LinkedHashSet<Usuario> pedidos = new LinkedHashSet<>();
    // Fila de recados deixados para o usuário
    private final Queue<Texto> recados = new LinkedList<>();
    // Fila de mensagens deixados para o usuário
    private final Queue<Texto> mensagens = new LinkedList<>();
    // Lista de comunidades das quais o usuário faz parte.
    private LinkedHashSet<Comunidade> comunidades = new LinkedHashSet<>();
    // Lista dos relacionamentos do usuário
    private Relacionamento relacionamento = new Relacionamento();

    /**
     * Construtor da classe Usuario.
     * @param login Login do usuário.
     * @param senha Senha do usuário.
     * @param nome Nome do usuário.
     */
    public Usuario(String login, String senha, String nome) {
        perfil.setNome(nome);
        this.senha = senha;
        this.login = login;
    }

    /**
     * Retorna o login do usuário.
     * @return O login do usuário.
     */
    public String getLogin() {
        return login;
    }

    /**
     * Retorna a senha do usuário.
     * @return A senha do usuário.
     */
    public String getSenha() {
        return senha;
    }

    /**
     * Retorna o nome do usuário.
     * @return O nome do usuário.
     */
    public String getNome() {
        return perfil.getNome();
    }

    /**
     * Retorna os pedidos de amizade do usuário.
     * @return Os pedidos de amizade do usuário.
     */
    public LinkedHashSet<Usuario> getPedidos() {
        return pedidos;
    }

    /**
     * Retorna a fila de recados do usuário.
     * @return A fila de recados do usuário.
     */
    public Queue<Texto> getRecados() {
        return recados;
    }

    /**
     * Retorna o conjunto de comunidades às quais o usuário pertence.
     * @return O conjunto de comunidades do usuário.
     */
    public LinkedHashSet<Comunidade> getComunidades() {
        return comunidades;
    }

    /**
     * Retorna a fila de mensagens do usuário.
     * @return A fila de mensagens do usuário.
     */
    public Queue<Texto> getMensagens() {
        return mensagens;
    }

    /**
     * Retorna o objeto que representa os relacionamentos do usuário, como amigos, inimigos, etc.
     * @return O objeto de relacionamentos do usuário.
     */
    public Relacionamento getRelacionamento() {
        return relacionamento;
    }

    /**
     * Retorna o perfil associado ao usuário.
     * @return O perfil do usuário.
     */
    public Perfil getPerfil() {
        return perfil;
    }

    /**
     * Adiciona um pedido de amizade para o usuário.
     * @param usuario O usuário que fez o pedido de amizade.
     */
    public void adicionarPedidoDeAmizade(Usuario usuario) {
        pedidos.add(usuario);
    }

    /**
     * Aceita um pedido de amizade de outro usuário.
     * O usuário se torna amigo e o pedido é removido da lista de pedidos.
     * @param usuario O usuário cujo pedido de amizade será aceito.
     */
    public void aceitarPedidoDeAmizade(Usuario usuario) {
        if (pedidos.contains(usuario)) {
            pedidos.remove(usuario);
            relacionamento.getAmigos().add(usuario);
        }
    }

    /**
     * Rejeita um pedido de amizade de outro usuário.
     * O pedido é simplesmente removido da lista de pedidos.
     * @param usuario O usuário cujo pedido de amizade será rejeitado.
     */
    public void rejeitarPedidoDeAmizade(Usuario usuario) {
        pedidos.remove(usuario);
    }

    /**
     * Adiciona um recado à fila de recados do usuário.
     * @param recado O recado a ser adicionado.
     */
    public void adicionarRecado(Texto recado) {
        recados.add(recado);
    }

    /**
     * Adiciona uma mensagem à fila de mensagens do usuário.
     * @param mensagem A mensagem a ser adicionada.
     */
    public void adicionarMensagem(Texto mensagem) {
        mensagens.add(mensagem);
    }

    /**
     * Adiciona o usuário a uma comunidade.
     * A comunidade é associada ao usuário.
     * @param comunidade A comunidade à qual o usuário será adicionado.
     */
    public void adicionarComunidade(Comunidade comunidade) {
        comunidades.add(comunidade);
    }
}