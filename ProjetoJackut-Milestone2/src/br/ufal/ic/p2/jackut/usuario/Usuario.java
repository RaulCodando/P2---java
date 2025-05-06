package br.ufal.ic.p2.jackut.usuario;

import java.io.Serializable;
import java.util.*;

/**
 * Representa um usu�rio na plataforma Jackut.
 * Armazena informa��es pessoais, amigos, pedidos de amizade e recados.
 */
public class Usuario implements Serializable {
    // Senha do usu�rio
    private String senha;
    // Login do usu�rio
    private String login;
    // Perfil associado ao usu�rio
    private Perfil perfil = new Perfil();
    // Conjunto de pedidos de amizade recebidos
    private LinkedHashSet<Usuario> pedidos = new LinkedHashSet<>();
    // Fila de recados deixados para o usu�rio
    private final Queue<Texto> recados = new LinkedList<>();
    // Fila de mensagens deixados para o usu�rio
    private final Queue<Texto> mensagens = new LinkedList<>();
    // Lista de comunidades das quais o usu�rio faz parte.
    private LinkedHashSet<Comunidade> comunidades = new LinkedHashSet<>();
    // Lista dos relacionamentos do usu�rio
    private Relacionamento relacionamento = new Relacionamento();

    /**
     * Construtor da classe Usuario.
     * @param login Login do usu�rio.
     * @param senha Senha do usu�rio.
     * @param nome Nome do usu�rio.
     */
    public Usuario(String login, String senha, String nome) {
        perfil.setNome(nome);
        this.senha = senha;
        this.login = login;
    }

    /**
     * Retorna o login do usu�rio.
     * @return O login do usu�rio.
     */
    public String getLogin() {
        return login;
    }

    /**
     * Retorna a senha do usu�rio.
     * @return A senha do usu�rio.
     */
    public String getSenha() {
        return senha;
    }

    /**
     * Retorna o nome do usu�rio.
     * @return O nome do usu�rio.
     */
    public String getNome() {
        return perfil.getNome();
    }

    /**
     * Retorna os pedidos de amizade do usu�rio.
     * @return Os pedidos de amizade do usu�rio.
     */
    public LinkedHashSet<Usuario> getPedidos() {
        return pedidos;
    }

    /**
     * Retorna a fila de recados do usu�rio.
     * @return A fila de recados do usu�rio.
     */
    public Queue<Texto> getRecados() {
        return recados;
    }

    /**
     * Retorna o conjunto de comunidades �s quais o usu�rio pertence.
     * @return O conjunto de comunidades do usu�rio.
     */
    public LinkedHashSet<Comunidade> getComunidades() {
        return comunidades;
    }

    /**
     * Retorna a fila de mensagens do usu�rio.
     * @return A fila de mensagens do usu�rio.
     */
    public Queue<Texto> getMensagens() {
        return mensagens;
    }

    /**
     * Retorna o objeto que representa os relacionamentos do usu�rio, como amigos, inimigos, etc.
     * @return O objeto de relacionamentos do usu�rio.
     */
    public Relacionamento getRelacionamento() {
        return relacionamento;
    }

    /**
     * Retorna o perfil associado ao usu�rio.
     * @return O perfil do usu�rio.
     */
    public Perfil getPerfil() {
        return perfil;
    }

    /**
     * Adiciona um pedido de amizade para o usu�rio.
     * @param usuario O usu�rio que fez o pedido de amizade.
     */
    public void adicionarPedidoDeAmizade(Usuario usuario) {
        pedidos.add(usuario);
    }

    /**
     * Aceita um pedido de amizade de outro usu�rio.
     * O usu�rio se torna amigo e o pedido � removido da lista de pedidos.
     * @param usuario O usu�rio cujo pedido de amizade ser� aceito.
     */
    public void aceitarPedidoDeAmizade(Usuario usuario) {
        if (pedidos.contains(usuario)) {
            pedidos.remove(usuario);
            relacionamento.getAmigos().add(usuario);
        }
    }

    /**
     * Rejeita um pedido de amizade de outro usu�rio.
     * O pedido � simplesmente removido da lista de pedidos.
     * @param usuario O usu�rio cujo pedido de amizade ser� rejeitado.
     */
    public void rejeitarPedidoDeAmizade(Usuario usuario) {
        pedidos.remove(usuario);
    }

    /**
     * Adiciona um recado � fila de recados do usu�rio.
     * @param recado O recado a ser adicionado.
     */
    public void adicionarRecado(Texto recado) {
        recados.add(recado);
    }

    /**
     * Adiciona uma mensagem � fila de mensagens do usu�rio.
     * @param mensagem A mensagem a ser adicionada.
     */
    public void adicionarMensagem(Texto mensagem) {
        mensagens.add(mensagem);
    }

    /**
     * Adiciona o usu�rio a uma comunidade.
     * A comunidade � associada ao usu�rio.
     * @param comunidade A comunidade � qual o usu�rio ser� adicionado.
     */
    public void adicionarComunidade(Comunidade comunidade) {
        comunidades.add(comunidade);
    }
}