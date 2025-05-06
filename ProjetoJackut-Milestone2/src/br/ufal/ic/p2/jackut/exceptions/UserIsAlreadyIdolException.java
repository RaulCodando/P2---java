package br.ufal.ic.p2.jackut.exceptions;

/**
 * Exce��o lan�ada quando um usu�rio tenta adicionar outro usu�rio como �dolo,
 * mas o usu�rio j� est� na lista de �dolos.
 * Essa exce��o impede que a opera��o de adi��o de �dolo seja realizada quando a condi��o de �dolo j� existe.
 */
public class UserIsAlreadyIdolException extends Exception{
    public UserIsAlreadyIdolException(){
        super("Usu�rio j� est� adicionado como �dolo.");
    }
}