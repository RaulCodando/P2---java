package br.ufal.ic.p2.jackut;

import java.io.Serializable;
import java.util.*;

/**
 * Representa um usuário na plataforma Jackut.
 * Armazena informações pessoais, amigos, pedidos de amizade e recados.
 */
public class Usuario implements Serializable {
    private String nome; //Nome do usuário
    private String login; //Login do usuário
    private String senha; //Senha do usuário
    private String descricao; //Descrição do usuário
    private String estadoCivil; //Estado civil do usuário
    private String aniversario; //Aniversário do usuário
    private String filhos; //Informações sobre filhos do usuário
    private String idiomas; //Idiomas falados pelo usuário
    private String cidadeNatal; //Cidade natal do usuário
    private String estilo; //Estilo do usuário
    private String fumo; //Informa se o usuário fuma e com qual frequência
    private String bebo; //Informações sobre consumo de bebidas alcoólicas do usuário
    private String moro; //Informações sobre o ambiente onde o usuário mora

    // Conjunto de amigos do usuário
    private LinkedHashSet<Usuario> amigos = new LinkedHashSet<>();
    // Conjunto de pedidos de amizade recebidos
    private LinkedHashSet<Usuario> pedidos = new LinkedHashSet<>();
    // Fila de recados deixados para o usuário
    private final Queue<String> recados = new LinkedList<>();

    /**
     * Construtor da classe Usuario.
     *
     * @param login Login do usuário.
     * @param senha Senha do usuário.
     * @param nome Nome do usuário.
     */
    public Usuario(String login, String senha, String nome) {
        this.nome = nome;
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
        return nome;
    }

    /**
     * Retorna a descrição do usuário.
     * @return A descrição do usuário.
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Retorna o estado civil do usuário.
     * @return O estado civil do usuário.
     */
    public String getEstadoCivil() {
        return estadoCivil;
    }

    /**
     * Retorna o aniversário do usuário.
     * @return O aniversário do usuário.
     */
    public String getAniversario() {
        return aniversario;
    }

    /**
     * Retorna informações sobre filhos do usuário.
     * @return Os filhos do usuário.
     */
    public String getFilhos() {
        return filhos;
    }

    /**
     * Retorna os idiomas que o usuário fala.
     * @return Os idiomas que o usuário fala.
     */
    public String getIdiomas() {
        return idiomas;
    }

    /**
     * Retorna a cidade natal do usuário.
     * @return A cidade natal do usuário.
     */
    public String getCidadeNatal() {
        return cidadeNatal;
    }

    /**
     * Retorna o estilo do usuário.
     * @return O estilo do usuário.
     */
    public String getEstilo() {
        return estilo;
    }

    /**
     * Retorna se o usuário fuma ou não.
     * @return A resposta sobre o fumo do usuário.
     */
    public String getFumo() {
        return fumo;
    }

    /**
     * Retorna informações sobre consumo de bebidas alcoólicas do usuário.
     * @return A resposta sobre o consumo de bebida do usuário.
     */
    public String getBebo() {
        return bebo;
    }

    /**
     * Retorna informações sobre o local onde o usuário mora.
     * @return O local onde o usuário mora.
     */
    public String getMoro() {
        return moro;
    }

    /**
     * Retorna os amigos do usuário.
     * @return Os amigos do usuário.
     */
    public LinkedHashSet<Usuario> getAmigos() {
        return amigos;
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
    public Queue<String> getRecados() {
        return recados;
    }

    /**
     * Define o nome do usuário.
     * @param nome O novo nome do usuário.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Define a descrição do usuário.
     * @param descricao A nova descrição do usuário.
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Define o estado civil do usuário.
     * @param estadoCivil O novo estado civil do usuário.
     */
    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    /**
     * Define o aniversário do usuário.
     * @param aniversario O novo aniversário do usuário.
     */
    public void setAniversario(String aniversario) {
        this.aniversario = aniversario;
    }

    /**
     * Define informações sobre filhos do usuário.
     * @param filhos Os novos filhos do usuário.
     */
    public void setFilhos(String filhos) {
        this.filhos = filhos;
    }

    /**
     * Define os idiomas que o usuário fala.
     * @param idiomas Os novos idiomas do usuário.
     */
    public void setIdiomas(String idiomas) {
        this.idiomas = idiomas;
    }

    /**
     * Define a cidade natal do usuário.
     * @param cidadeNatal A nova cidade natal do usuário.
     */
    public void setCidadeNatal(String cidadeNatal) {
        this.cidadeNatal = cidadeNatal;
    }

    /**
     * Define o estilo do usuário.
     * @param estilo O novo estilo do usuário.
     */
    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }

    /**
     * Define se o usuário fuma ou não.
     * @param fumo A nova resposta sobre o fumo do usuário.
     */
    public void setFumo(String fumo) {
        this.fumo = fumo;
    }

    /**
     * Define informações sobre consumo de bebidas alcoólicas do usuário
     * @param bebo A nova resposta sobre o consumo de bebida do usuário.
     */
    public void setBebo(String bebo) {
        this.bebo = bebo;
    }

    /**
     * Define informações sobre o local onde o usuário mora.
     * @param moro O local onde o usuário mora.
     */
    public void setMoro(String moro) {
        this.moro = moro;
    }
}