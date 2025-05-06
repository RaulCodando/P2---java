package br.ufal.ic.p2.jackut.exceptions;

/**
 * Exceção lançada quando um usuário tenta adicionar outro usuário como amigo,
 * mas este já está na lista de amigos.
 * Essa exceção impede que a operação de amizade seja realizada quando a condição de amizade já existe.
 */
public class UserIsAlreadyFriendException extends Exception{
    public UserIsAlreadyFriendException(String message){
        super(message);
    }
}