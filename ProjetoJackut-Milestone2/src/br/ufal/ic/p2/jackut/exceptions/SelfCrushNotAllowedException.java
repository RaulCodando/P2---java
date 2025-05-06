package br.ufal.ic.p2.jackut.exceptions;

/**
 * Exce��o lan�ada quando um usu�rio tenta se adicionar como paquera de si mesmo.
 * Essa exce��o � usada para impedir que um usu�rio marque a si mesmo como paquera, o que � considerado uma opera��o inv�lida no sistema.
 */
public class SelfCrushNotAllowedException extends Exception{
    public SelfCrushNotAllowedException(){
        super("Usu�rio n�o pode ser paquera de si mesmo.");
    }
}