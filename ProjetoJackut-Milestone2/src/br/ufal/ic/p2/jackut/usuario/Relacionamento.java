package br.ufal.ic.p2.jackut.usuario;

import java.io.Serializable;
import java.util.LinkedHashSet;

/**
 * Classe que representa os relacionamentos sociais de um usuário no sistema.
 * Cada tipo de relacionamento é armazenado em uma coleção distinta.
 * A classe implementa {@link Serializable} para permitir que seus dados
 * sejam salvos e carregados via serialização.
 */
public class Relacionamento implements Serializable {

    // Conjunto de usuários que são amigos.
    private LinkedHashSet<Usuario> amigos = new LinkedHashSet<>();

    // Conjunto de usuários que são fãs deste usuário.
    private LinkedHashSet<Usuario> fas = new LinkedHashSet<>();

    // Conjunto de usuários que são ídolos deste usuário.
    private LinkedHashSet<Usuario> idolo = new LinkedHashSet<>();

    // Conjunto de usuários com os quais este usuário está "paquerando".
    private LinkedHashSet<Usuario> paqueras = new LinkedHashSet<>();

    // Conjunto de usuários considerados inimigos.
    private LinkedHashSet<Usuario> inimigos = new LinkedHashSet<>();

    /**
     * Retorna o conjunto de amigos do usuário.
     *
     * @return Conjunto de amigos.
     */
    public LinkedHashSet<Usuario> getAmigos() {
        return amigos;
    }

    /**
     * Retorna o conjunto de fãs do usuário.
     *
     * @return Conjunto de fãs.
     */
    public LinkedHashSet<Usuario> getFas() {
        return fas;
    }

    /**
     * Retorna o conjunto de ídolos do usuário.
     *
     * @return Conjunto de ídolos.
     */
    public LinkedHashSet<Usuario> getIdolo() {
        return idolo;
    }

    /**
     * Retorna o conjunto de paqueras do usuário.
     *
     * @return Conjunto de paqueras.
     */
    public LinkedHashSet<Usuario> getPaqueras() {
        return paqueras;
    }

    /**
     * Retorna o conjunto de inimigos do usuário.
     *
     * @return Conjunto de inimigos.
     */
    public LinkedHashSet<Usuario> getInimigos() {
        return inimigos;
    }
}