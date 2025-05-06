package br.ufal.ic.p2.jackut.exceptions;

/**
 * Exceção lançada quando um usuário tenta enviar um recado para si mesmo.
 * Essa exceção é usada para impedir que um usuário envie mensagens para o próprio perfil, o que é considerado uma operação inválida no sistema.
 */
public class SelfMessagingNotAllowedException extends Exception {
    public SelfMessagingNotAllowedException(){
        super("Usuário não pode enviar recado para si mesmo.");
    }
}