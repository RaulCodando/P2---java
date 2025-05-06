package br.ufal.ic.p2.jackut.exceptions;

/**
 * Exceção lançada quando um usuário tenta adicionar outro usuário como paquera, mas ele já está registrado como tal.
 * Essa exceção impede que o usuário seja adicionado novamente à lista de paqueras.
 */
public class UserIsAlreeadyCrushException extends Exception{
    public UserIsAlreeadyCrushException(){
        super("Usuário já está adicionado como paquera.");
    }
}