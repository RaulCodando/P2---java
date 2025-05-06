package br.ufal.ic.p2.jackut.exceptions;

/**
 * Exceção lançada quando um usuário tenta se tornar fã de si mesmo.
 * Essa exceção é utilizada para impedir que um usuário se adicione como ídolo, o que é considerado uma operação inválida no sistema.
 */
public class SelfIdolNotAllowedException extends Exception{
    public SelfIdolNotAllowedException(){
        super("Usuário não pode ser fã de si mesmo.");
    }
}