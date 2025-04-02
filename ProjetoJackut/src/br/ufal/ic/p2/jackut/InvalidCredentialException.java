package br.ufal.ic.p2.jackut;

/**
 * Subclasse de Exception indicando que as credenciais inseriadas no sistema s�o inv�lidas.
 */

public class InvalidCredentialException extends Exception{
    public InvalidCredentialException(String message){
        super(message);
    }
}
