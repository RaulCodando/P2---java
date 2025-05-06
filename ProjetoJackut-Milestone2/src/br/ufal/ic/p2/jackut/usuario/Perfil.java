package br.ufal.ic.p2.jackut.usuario;

import java.io.Serializable;

public class Perfil implements Serializable {
    //Nome do usu�rio
    private String nome;
    //Descri��o do usu�rio
    private String descricao;
    //Estado civil do usu�rio
    private String estadoCivil;
    //Anivers�rio do usu�rio
    private String aniversario;
    //Informa��es sobre filhos do usu�rio
    private String filhos;
    //Idiomas falados pelo usu�rio
    private String idiomas;
    //Cidade natal do usu�rio
    private String cidadeNatal;
    //Estilo do usu�rio
    private String estilo;
    //Informa se o usu�rio fuma e com qual frequ�ncia
    private String fumo;
    //Informa��es sobre consumo de bebidas alco�licas do usu�rio
    private String bebo;
    //Informa��es sobre o ambiente onde o usu�rio mora
    private String moro;

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
