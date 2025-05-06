package br.ufal.ic.p2.jackut.exceptions;

/**
 * Exceção lançada quando um usuário tenta adicionar outro usuário como ídolo,
 * mas o usuário já está na lista de ídolos.
 * Essa exceção impede que a operação de adição de ídolo seja realizada quando a condição de ídolo já existe.
 */
public class UserIsAlreadyIdolException extends Exception{
    public UserIsAlreadyIdolException(){
        super("Usuário já está adicionado como ídolo.");
    }
}