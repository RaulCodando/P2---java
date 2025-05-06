package br.ufal.ic.p2.jackut.exceptions;

/**
 * Subclasse de Exception indicando que as credenciais inseriadas no sistema s�o inv�lidas.
 */

public class InvalidCredentialException extends Exception{
    public InvalidCredentialException(){
        super("Login ou senha inv�lidos.");
    }
}
