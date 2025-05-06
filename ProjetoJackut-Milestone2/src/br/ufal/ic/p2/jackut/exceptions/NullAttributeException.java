package br.ufal.ic.p2.jackut.exceptions;

/**
 * Subclasse de Exception que indica que o atributo sendo acessado � nulo ou n�o foi preenchido
 */

public class NullAttributeException extends Exception{
    public NullAttributeException(String message){
        super(message);
    }
}
