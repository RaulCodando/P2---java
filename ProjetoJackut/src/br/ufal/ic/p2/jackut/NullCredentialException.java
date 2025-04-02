package br.ufal.ic.p2.jackut;

/**
 * Subclasse de Exception que indica a tentativa de criar uma credencial vazia ou inv�lida
 */

public class NullCredentialException extends Exception{
    NullCredentialException(String message){
        super(message);
    }
}
