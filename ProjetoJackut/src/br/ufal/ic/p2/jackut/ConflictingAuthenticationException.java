package br.ufal.ic.p2.jackut;

/**
 * Subclasse de Exception que indica a tentativa de criação de uma credencial de identificação
 * que já existe
 */

public class ConflictingAuthenticationException extends Exception{
    ConflictingAuthenticationException(String message){
        super(message);
    }
}
