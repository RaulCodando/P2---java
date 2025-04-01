package br.ufal.ic.p2.jackut;

/**
 * Subclasse de Exception que indica a tentativa de cria��o de uma credencial de identifica��o
 * que j� existe
 */

public class ConflictingAuthenticationException extends Exception{
    ConflictingAuthenticationException(String message){
        super(message);
    }
}
