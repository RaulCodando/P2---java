package br.ufal.ic.p2.jackut;

/**
 * Subclase de Exception que indica uma tentatica de realizar
 * uma opera��o n�o permitida pelas diretrizes do programa.
 */

public class FobiddenOperationException extends Exception{
    FobiddenOperationException(String message){
        super(message);
    }
}
