package br.ufal.ic.p2.jackut.exceptions;

/**
 * Exce��o lan�ada quando se tenta criar uma comunidade com um nome que j� existe.
 * A exce��o � gerada para evitar a duplica��o de comunidades com o mesmo nome.
 */
public class CommunityAlreadyExistsException extends Exception {
    public CommunityAlreadyExistsException(){
        super("Comunidade com esse nome j� existe.");
    }
}