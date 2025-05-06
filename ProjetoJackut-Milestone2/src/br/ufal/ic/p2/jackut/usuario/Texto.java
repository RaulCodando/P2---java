package br.ufal.ic.p2.jackut.usuario;

import java.io.Serializable;

/**
 * Representa um texto enviado por um usuário na plataforma Jackut.
 * Pode ser utilizado para mensagens, recados ou qualquer tipo de comunicação escrita.
 */
public class Texto implements Serializable {
    // O login do usuário que enviou o texto
    private String remetente;
    // O conteúdo da mensagem ou recado
    private String conteudo;

    /**
     * Construtor da classe Texto.
     *
     * @param remetente O login do usuário que enviou o texto.
     * @param conteudo O conteúdo da mensagem ou recado.
     */
    public Texto(String remetente, String conteudo){
        this.remetente = remetente;
        this.conteudo = conteudo;
    }

    /**
     * Retorna o remetente do texto.
     * @return O login do usuário que enviou o texto.
     */
    public String getRemetente() {
        return remetente;
    }

    /**
     * Retorna o conteúdo do texto.
     * @return O conteúdo da mensagem ou recado.
     */
    public String getConteudo() {
        return conteudo;
    }
}