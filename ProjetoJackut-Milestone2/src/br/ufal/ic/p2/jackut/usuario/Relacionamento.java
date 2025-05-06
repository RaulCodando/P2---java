package br.ufal.ic.p2.jackut.usuario;

import java.io.Serializable;
import java.util.LinkedHashSet;

/**
 * Classe que representa os relacionamentos sociais de um usu�rio no sistema.
 * Cada tipo de relacionamento � armazenado em uma cole��o distinta.
 * A classe implementa {@link Serializable} para permitir que seus dados
 * sejam salvos e carregados via serializa��o.
 */
public class Relacionamento implements Serializable {

    // Conjunto de usu�rios que s�o amigos.
    private LinkedHashSet<Usuario> amigos = new LinkedHashSet<>();

    // Conjunto de usu�rios que s�o f�s deste usu�rio.
    private LinkedHashSet<Usuario> fas = new LinkedHashSet<>();

    // Conjunto de usu�rios que s�o �dolos deste usu�rio.
    private LinkedHashSet<Usuario> idolo = new LinkedHashSet<>();

    // Conjunto de usu�rios com os quais este usu�rio est� "paquerando".
    private LinkedHashSet<Usuario> paqueras = new LinkedHashSet<>();

    // Conjunto de usu�rios considerados inimigos.
    private LinkedHashSet<Usuario> inimigos = new LinkedHashSet<>();

    /**
     * Retorna o conjunto de amigos do usu�rio.
     *
     * @return Conjunto de amigos.
     */
    public LinkedHashSet<Usuario> getAmigos() {
        return amigos;
    }

    /**
     * Retorna o conjunto de f�s do usu�rio.
     *
     * @return Conjunto de f�s.
     */
    public LinkedHashSet<Usuario> getFas() {
        return fas;
    }

    /**
     * Retorna o conjunto de �dolos do usu�rio.
     *
     * @return Conjunto de �dolos.
     */
    public LinkedHashSet<Usuario> getIdolo() {
        return idolo;
    }

    /**
     * Retorna o conjunto de paqueras do usu�rio.
     *
     * @return Conjunto de paqueras.
     */
    public LinkedHashSet<Usuario> getPaqueras() {
        return paqueras;
    }

    /**
     * Retorna o conjunto de inimigos do usu�rio.
     *
     * @return Conjunto de inimigos.
     */
    public LinkedHashSet<Usuario> getInimigos() {
        return inimigos;
    }
}