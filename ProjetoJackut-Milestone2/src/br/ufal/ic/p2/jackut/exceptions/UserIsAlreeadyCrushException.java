package br.ufal.ic.p2.jackut.exceptions;

/**
 * Exce��o lan�ada quando um usu�rio tenta adicionar outro usu�rio como paquera, mas ele j� est� registrado como tal.
 * Essa exce��o impede que o usu�rio seja adicionado novamente � lista de paqueras.
 */
public class UserIsAlreeadyCrushException extends Exception{
    public UserIsAlreeadyCrushException(){
        super("Usu�rio j� est� adicionado como paquera.");
    }
}