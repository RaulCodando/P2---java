package br.ufal.ic.p2.jackut.exceptions;

/**
 * Exceção lançada quando um usuário tenta adicionar a si mesmo como amigo.
 * Essa exceção é utilizada para impedir que um usuário se adicione como amigo, o que é considerado uma operação inválida no sistema.
 */
public class SelfFriendshipNotAllowedException extends Exception{
    public SelfFriendshipNotAllowedException(){
        super("Usuário não pode adicionar a si mesmo como amigo.");
    }
}