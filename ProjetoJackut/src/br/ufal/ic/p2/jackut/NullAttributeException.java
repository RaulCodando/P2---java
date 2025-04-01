package br.ufal.ic.p2.jackut;

/**
 * Subclasse de Exception que indica que o atributo sendo acessado é nulo ou não foi preenchido
 */

public class NullAttributeException extends Exception{
    public NullAttributeException(String message){
        super(message);
    }
}
