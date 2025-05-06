package br.ufal.ic.p2.jackut.exceptions;

/**
 * Subclasse de Exception indicando que as credenciais inseriadas no sistema são inválidas.
 */

public class InvalidCredentialException extends Exception{
    public InvalidCredentialException(){
        super("Login ou senha inválidos.");
    }
}
