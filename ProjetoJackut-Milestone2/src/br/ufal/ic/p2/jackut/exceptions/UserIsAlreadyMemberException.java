package br.ufal.ic.p2.jackut.exceptions;

/**
 * Exceção lançada quando um usuário tenta ingressar em uma comunidade da qual já faz parte.
 * Essa exceção impede que o usuário seja adicionado novamente à comunidade.
 */
public class UserIsAlreadyMemberException extends Exception {
    public UserIsAlreadyMemberException(){
        super("Usuario já faz parte dessa comunidade.");
    }
}