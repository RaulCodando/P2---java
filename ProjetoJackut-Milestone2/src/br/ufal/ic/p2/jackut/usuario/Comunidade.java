package br.ufal.ic.p2.jackut.usuario;

import java.io.Serializable;
import java.util.LinkedHashSet;

/**
 * Representa uma comunidade dentro do sistema Jackut.
 * Cada comunidade possui um dono, um nome, uma descrição e um conjunto de membros.
 * A classe implementa {@link Serializable} para permitir sua persistência em arquivos.
 */
public class Comunidade implements Serializable {

    // Login do usuário que é dono (criador) da comunidade.
    private String donoLogin;

    // Nome da comunidade.
    private String nome;

    // Descrição da comunidade.
    private String descricao;

    // Conjunto de membros da comunidade. O dono é adicionado automaticamente.
    private LinkedHashSet<Usuario> membros;

    /**
     * Construtor da classe Comunidade.
     * Cria uma nova comunidade com o dono, nome e descrição especificados.
     * O dono é automaticamente adicionado à lista de membros.
     * @param dono O usuário que cria e se torna o dono da comunidade.
     * @param nome O nome da comunidade.
     * @param descricao A descrição da comunidade.
     */
    public Comunidade(Usuario dono, String nome, String descricao){
        this.donoLogin = dono.getLogin();
        this.nome = nome;
        this.descricao = descricao;

        membros = new LinkedHashSet<Usuario>();
        membros.add(dono); // O dono também é considerado um membro.
    }

    /**
     * Retorna o nome da comunidade.
     * @return Nome da comunidade.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Retorna a descrição da comunidade.
     * @return Descrição da comunidade.
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Retorna o login do dono da comunidade.
     * @return Login do usuário dono.
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