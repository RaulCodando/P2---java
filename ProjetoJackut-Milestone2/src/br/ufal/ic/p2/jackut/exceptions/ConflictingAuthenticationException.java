package br.ufal.ic.p2.jackut.exceptions;

/**
 * Subclasse de Exception que indica a tentativa de cria��o de uma credencial de identifica��o
 * que j� existe
 */

public class ConflictingAuthenticationException extends Exception{
    public ConflictingAuthenticationException(){
        super("Conta com esse nome j� existe.");
    }
}
