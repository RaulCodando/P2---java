package br.ufal.ic.p2.jackut.exceptions;

/**
 * Subclasse de Exception que indica a tentativa de acessar um usuário não cadastrado no sistema
 */

public class InvalidUserException extends Exception{
    public InvalidUserException(){
        super("Usuário não cadastrado.");
    }
}
