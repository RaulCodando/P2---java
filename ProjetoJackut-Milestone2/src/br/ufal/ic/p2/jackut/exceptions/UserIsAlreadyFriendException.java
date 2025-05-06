package br.ufal.ic.p2.jackut.exceptions;

/**
 * Exce��o lan�ada quando um usu�rio tenta adicionar outro usu�rio como amigo,
 * mas este j� est� na lista de amigos.
 * Essa exce��o impede que a opera��o de amizade seja realizada quando a condi��o de amizade j� existe.
 */
public class UserIsAlreadyFriendException extends Exception{
    public UserIsAlreadyFriendException(String message){
        super(message);
    }
}