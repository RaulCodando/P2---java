package br.ufal.ic.p2.jackut.exceptions;

/**
 * Subclasse de Exception que indica uma tentativa de acessar uma lista vazia.
 */

public class EmptyListException extends Exception{
    public EmptyListException(String message){
        super(message);
    }
}
