package br.ufal.ic.p2.jackut.exceptions;

/**
 * Subclasse de Exception que indica a tentativa de acessar um usu�rio n�o cadastrado no sistema
 */

public class InvalidUserException extends Exception{
    public InvalidUserException(){
        super("Usu�rio n�o cadastrado.");
    }
}
