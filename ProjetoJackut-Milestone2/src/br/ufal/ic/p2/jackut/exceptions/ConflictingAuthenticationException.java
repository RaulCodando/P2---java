package br.ufal.ic.p2.jackut.exceptions;

/**
 * Subclasse de Exception que indica a tentativa de criação de uma credencial de identificação
 * que já existe
 */

public class ConflictingAuthenticationException extends Exception{
    public ConflictingAuthenticationException(){
        super("Conta com esse nome já existe.");
    }
}
