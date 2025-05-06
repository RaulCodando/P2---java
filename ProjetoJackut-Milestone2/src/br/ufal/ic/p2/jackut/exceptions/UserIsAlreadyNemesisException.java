package br.ufal.ic.p2.jackut.exceptions;

/**
 * Exceção lançada quando um usuário tenta adicionar outro usuário como inimigo, mas ele já está registrado como tal.
 * Essa exceção impede que o usuário seja adicionado novamente à lista de inimigos.
 */
public class UserIsAlreadyNemesisException extends Exception{
    public UserIsAlreadyNemesisException(){
        super("Usuário já está adicionado como inimigo.");
    }
}