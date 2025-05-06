package br.ufal.ic.p2.jackut.exceptions;

/**
 * Subclasse de Exception que indica a tentativa de criar uma credencial vazia ou inválida
 */

public class NullCredentialException extends Exception{
    public NullCredentialException(String message){
        super(message);
    }
}
