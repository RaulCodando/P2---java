package br.ufal.ic.p2.jackut;

/**
 * Subclasse de Exception que indica uma tentativa de executar uma determinada opera��o
 * que j� foi ou n�o precisa ser realizada.
 */

public class NotNeededOperationException extends Exception{
    public NotNeededOperationException(String message){
        super(message);
    }
}
