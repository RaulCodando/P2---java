package br.ufal.ic.p2.jackut.exceptions;

/**
 * Exce��o lan�ada quando um usu�rio tenta adicionar a si mesmo como amigo.
 * Essa exce��o � utilizada para impedir que um usu�rio se adicione como amigo, o que � considerado uma opera��o inv�lida no sistema.
 */
public class SelfFriendshipNotAllowedException extends Exception{
    public SelfFriendshipNotAllowedException(){
        super("Usu�rio n�o pode adicionar a si mesmo como amigo.");
    }
}