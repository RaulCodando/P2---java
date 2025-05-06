package br.ufal.ic.p2.jackut.exceptions;

/**
 * Exceção lançada quando se tenta criar uma comunidade com um nome que já existe.
 * A exceção é gerada para evitar a duplicação de comunidades com o mesmo nome.
 */
public class CommunityAlreadyExistsException extends Exception {
    public CommunityAlreadyExistsException(){
        super("Comunidade com esse nome já existe.");
    }
}