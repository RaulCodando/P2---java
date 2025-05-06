package br.ufal.ic.p2.jackut.exceptions;

/**
 * Exceção lançada quando se tenta acessar ou realizar uma operação em uma comunidade que não existe.
 * Essa exceção é usada para garantir que operações sejam realizadas apenas em comunidades válidas.
 */
public class InvalidCommunityException extends Exception {
    public InvalidCommunityException(){
        super("Comunidade não existe.");
    }
}