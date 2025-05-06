package br.ufal.ic.p2.jackut.exceptions;

/**
 * Exceção lançada quando um usuário tenta realizar uma operação com um inimigo.
 * A operação é considerada inválida, pois o usuário não pode interagir com alguém que é seu inimigo.
 * A exceção inclui o nome do inimigo para informar qual usuário está bloqueado para a operação.
 */
public class NemesisException extends Exception {
    public NemesisException(String inimigo){
        super(String.format("Função inválida: %s é seu inimigo.", inimigo));
    }
}