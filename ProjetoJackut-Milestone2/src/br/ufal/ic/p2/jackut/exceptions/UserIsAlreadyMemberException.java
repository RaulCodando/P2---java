package br.ufal.ic.p2.jackut.exceptions;

/**
 * Exce��o lan�ada quando um usu�rio tenta ingressar em uma comunidade da qual j� faz parte.
 * Essa exce��o impede que o usu�rio seja adicionado novamente � comunidade.
 */
public class UserIsAlreadyMemberException extends Exception {
    public UserIsAlreadyMemberException(){
        super("Usuario j� faz parte dessa comunidade.");
    }
}