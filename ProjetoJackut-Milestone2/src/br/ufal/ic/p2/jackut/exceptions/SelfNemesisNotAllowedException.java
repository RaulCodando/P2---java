package br.ufal.ic.p2.jackut.exceptions;

/**
 * Exceção lançada quando um usuário tenta se tornar inimigo de si mesmo.
 * Essa exceção impede que um usuário registre a si mesmo como inimigo, o que é uma operação inválida no sistema.
 */
public class SelfNemesisNotAllowedException extends Exception{
    public SelfNemesisNotAllowedException(){
        super("Usuário não pode ser inimigo de si mesmo.");
    }
}