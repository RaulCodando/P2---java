package br.ufal.ic.p2.jackut.exceptions;

/**
 * Exce��o lan�ada quando se tenta acessar ou realizar uma opera��o em uma comunidade que n�o existe.
 * Essa exce��o � usada para garantir que opera��es sejam realizadas apenas em comunidades v�lidas.
 */
public class InvalidCommunityException extends Exception {
    public InvalidCommunityException(){
        super("Comunidade n�o existe.");
    }
}