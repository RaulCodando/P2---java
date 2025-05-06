package br.ufal.ic.p2.jackut.exceptions;

/**
 * Exce��o lan�ada quando um usu�rio tenta enviar um recado para si mesmo.
 * Essa exce��o � usada para impedir que um usu�rio envie mensagens para o pr�prio perfil, o que � considerado uma opera��o inv�lida no sistema.
 */
public class SelfMessagingNotAllowedException extends Exception {
    public SelfMessagingNotAllowedException(){
        super("Usu�rio n�o pode enviar recado para si mesmo.");
    }
}