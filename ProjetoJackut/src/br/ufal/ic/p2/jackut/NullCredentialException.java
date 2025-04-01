package br.ufal.ic.p2.jackut;

/**
 * Subclasse de Exception que indica a tentativa de criar uma credencial vazia ou inválida
 */

public class NullCredentialException extends Exception{
    NullCredentialException(String message){
        super(message);
    }
}
