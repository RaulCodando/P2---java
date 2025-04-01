package br.ufal.ic.p2.jackut;

import java.io.Serializable;
import java.util.*;

/**
 * Representa um usu�rio na plataforma Jackut.
 * Armazena informa��es pessoais, amigos, pedidos de amizade e recados.
 */
public class Usuario implements Serializable {
    private String nome; //Nome do usu�rio
    private String login; //Login do usu�rio
    private String senha; //Senha do usu�rio
    private String descricao; //Descri��o do usu�rio
    private String estadoCivil; //Estado civil do usu�rio
    private String aniversario; //Anivers�rio do usu�rio
    private String filhos; //Informa��es sobre filhos do usu�rio
    private String idiomas; //Idiomas falados pelo usu�rio
    private String cidadeNatal; //Cidade natal do usu�rio
    private String estilo; //Estilo do usu�rio
    private String fumo; //Informa se o usu�rio fuma e com qual frequ�ncia
    private String bebo; //Informa��es sobre consumo de bebidas alco�licas do usu�rio
    private String moro; //Informa��es sobre o ambiente onde o usu�rio mora

    // Conjunto de amigos do usu�rio
    private LinkedHashSet<Usuario> amigos = new LinkedHashSet<>();
    // Conjunto de pedidos de amizade recebidos
    private LinkedHashSet<Usuario> pedidos = new LinkedHashSet<>();
    // Fila de recados deixados para o usu�rio
    private final Queue<String> recados = new LinkedList<>();

    /**
     * Construtor da classe Usuario.
     *
     * @param login Login do usu�rio.
     * @param senha Senha do usu�rio.
     * @param nome Nome do usu�rio.
     */
    public Usuario(String login, String senha, String nome) {
        this.nome = nome;
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
        return nome;
    }

    /**
     * Retorna a descri��o do usu�rio.
     * @return A descri��o do usu�rio.
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Retorna o estado civil do usu�rio.
     * @return O estado civil do usu�rio.
     */
    public String getEstadoCivil() {
        return estadoCivil;
    }

    /**
     * Retorna o anivers�rio do usu�rio.
     * @return O anivers�rio do usu�rio.
     */
    public String getAniversario() {
        return aniversario;
    }

    /**
     * Retorna informa��es sobre filhos do usu�rio.
     * @return Os filhos do usu�rio.
     */
    public String getFilhos() {
        return filhos;
    }

    /**
     * Retorna os idiomas que o usu�rio fala.
     * @return Os idiomas que o usu�rio fala.
     */
    public String getIdiomas() {
        return idiomas;
    }

    /**
     * Retorna a cidade natal do usu�rio.
     * @return A cidade natal do usu�rio.
     */
    public String getCidadeNatal() {
        return cidadeNatal;
    }

    /**
     * Retorna o estilo do usu�rio.
     * @return O estilo do usu�rio.
     */
    public String getEstilo() {
        return estilo;
    }

    /**
     * Retorna se o usu�rio fuma ou n�o.
     * @return A resposta sobre o fumo do usu�rio.
     */
    public String getFumo() {
        return fumo;
    }

    /**
     * Retorna informa��es sobre consumo de bebidas alco�licas do usu�rio.
     * @return A resposta sobre o consumo de bebida do usu�rio.
     */
    public String getBebo() {
        return bebo;
    }

    /**
     * Retorna informa��es sobre o local onde o usu�rio mora.
     * @return O local onde o usu�rio mora.
     */
    public String getMoro() {
        return moro;
    }

    /**
     * Retorna os amigos do usu�rio.
     * @return Os amigos do usu�rio.
     */
    public LinkedHashSet<Usuario> getAmigos() {
        return amigos;
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
    public Queue<String> getRecados() {
        return recados;
    }

    /**
     * Define o nome do usu�rio.
     * @param nome O novo nome do usu�rio.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Define a descri��o do usu�rio.
     * @param descricao A nova descri��o do usu�rio.
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Define o estado civil do usu�rio.
     * @param estadoCivil O novo estado civil do usu�rio.
     */
    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    /**
     * Define o anivers�rio do usu�rio.
     * @param aniversario O novo anivers�rio do usu�rio.
     */
    public void setAniversario(String aniversario) {
        this.aniversario = aniversario;
    }

    /**
     * Define informa��es sobre filhos do usu�rio.
     * @param filhos Os novos filhos do usu�rio.
     */
    public void setFilhos(String filhos) {
        this.filhos = filhos;
    }

    /**
     * Define os idiomas que o usu�rio fala.
     * @param idiomas Os novos idiomas do usu�rio.
     */
    public void setIdiomas(String idiomas) {
        this.idiomas = idiomas;
    }

    /**
     * Define a cidade natal do usu�rio.
     * @param cidadeNatal A nova cidade natal do usu�rio.
     */
    public void setCidadeNatal(String cidadeNatal) {
        this.cidadeNatal = cidadeNatal;
    }

    /**
     * Define o estilo do usu�rio.
     * @param estilo O novo estilo do usu�rio.
     */
    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }

    /**
     * Define se o usu�rio fuma ou n�o.
     * @param fumo A nova resposta sobre o fumo do usu�rio.
     */
    public void setFumo(String fumo) {
        this.fumo = fumo;
    }

    /**
     * Define informa��es sobre consumo de bebidas alco�licas do usu�rio
     * @param bebo A nova resposta sobre o consumo de bebida do usu�rio.
     */
    public void setBebo(String bebo) {
        this.bebo = bebo;
    }

    /**
     * Define informa��es sobre o local onde o usu�rio mora.
     * @param moro O local onde o usu�rio mora.
     */
    public void setMoro(String moro) {
        this.moro = moro;
    }
}