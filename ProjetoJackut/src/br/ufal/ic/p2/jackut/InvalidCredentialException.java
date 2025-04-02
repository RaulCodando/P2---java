package br.ufal.ic.p2.jackut;

/**
 * Subclasse de Exception indicando que as credenciais inseriadas no sistema são inválidas.
 */

public class InvalidCredentialException extends Exception{
    public InvalidCredentialException(String message){
        super(message);
    }
}
