package br.ufal.ic.p2.jackut;

/**
 * Subclasse de Exception que indica a tentativa de acessar um usu�rio n�o cadastrado no sistema
 */

public class InvalidUserException extends Exception{
    public InvalidUserException(String message){
        super(message);
    }
}
