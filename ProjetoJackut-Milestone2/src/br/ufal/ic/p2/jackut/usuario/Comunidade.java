package br.ufal.ic.p2.jackut.usuario;

import java.io.Serializable;
import java.util.LinkedHashSet;

/**
 * Representa uma comunidade dentro do sistema Jackut.
 * Cada comunidade possui um dono, um nome, uma descri��o e um conjunto de membros.
 * A classe implementa {@link Serializable} para permitir sua persist�ncia em arquivos.
 */
public class Comunidade implements Serializable {

    // Login do usu�rio que � dono (criador) da comunidade.
    private String donoLogin;

    // Nome da comunidade.
    private String nome;

    // Descri��o da comunidade.
    private String descricao;

    // Conjunto de membros da comunidade. O dono � adicionado automaticamente.
    private LinkedHashSet<Usuario> membros;

    /**
     * Construtor da classe Comunidade.
     * Cria uma nova comunidade com o dono, nome e descri��o especificados.
     * O dono � automaticamente adicionado � lista de membros.
     * @param dono O usu�rio que cria e se torna o dono da comunidade.
     * @param nome O nome da comunidade.
     * @param descricao A descri��o da comunidade.
     */
    public Comunidade(Usuario dono, String nome, String descricao){
        this.donoLogin = dono.getLogin();
        this.nome = nome;
        this.descricao = descricao;

        membros = new LinkedHashSet<Usuario>();
        membros.add(dono); // O dono tamb�m � considerado um membro.
    }

    /**
     * Retorna o nome da comunidade.
     * @return Nome da comunidade.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Retorna a descri��o da comunidade.
     * @return Descri��o da comunidade.
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Retorna o login do dono da comunidade.
     * @return Login do usu�rio dono.
     */
    public String getDonoLogin() {
        return donoLogin;
    }

    /**
     * Retorna o conjunto de membros da comunidade.
     *
     * @return Conjunto de membros.
     */
    public LinkedHashSet<Usuario> getMembros() {
        return membros;
    }
}