package br.ufal.ic.p2.jackut.exceptions;

/**
 * Exce��o lan�ada quando um usu�rio tenta se tornar inimigo de si mesmo.
 * Essa exce��o impede que um usu�rio registre a si mesmo como inimigo, o que � uma opera��o inv�lida no sistema.
 */
public class SelfNemesisNotAllowedException extends Exception{
    public SelfNemesisNotAllowedException(){
        super("Usu�rio n�o pode ser inimigo de si mesmo.");
    }
}