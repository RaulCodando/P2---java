package br.ufal.ic.p2.jackut.exceptions;

/**
 * Exce��o lan�ada quando um usu�rio tenta se tornar f� de si mesmo.
 * Essa exce��o � utilizada para impedir que um usu�rio se adicione como �dolo, o que � considerado uma opera��o inv�lida no sistema.
 */
public class SelfIdolNotAllowedException extends Exception{
    public SelfIdolNotAllowedException(){
        super("Usu�rio n�o pode ser f� de si mesmo.");
    }
}