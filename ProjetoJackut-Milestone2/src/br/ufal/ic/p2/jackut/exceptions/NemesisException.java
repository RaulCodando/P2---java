package br.ufal.ic.p2.jackut.exceptions;

/**
 * Exce��o lan�ada quando um usu�rio tenta realizar uma opera��o com um inimigo.
 * A opera��o � considerada inv�lida, pois o usu�rio n�o pode interagir com algu�m que � seu inimigo.
 * A exce��o inclui o nome do inimigo para informar qual usu�rio est� bloqueado para a opera��o.
 */
public class NemesisException extends Exception {
    public NemesisException(String inimigo){
        super(String.format("Fun��o inv�lida: %s � seu inimigo.", inimigo));
    }
}