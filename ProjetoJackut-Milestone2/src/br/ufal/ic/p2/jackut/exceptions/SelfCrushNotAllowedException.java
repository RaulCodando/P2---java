package br.ufal.ic.p2.jackut.exceptions;

/**
 * Exceção lançada quando um usuário tenta se adicionar como paquera de si mesmo.
 * Essa exceção é usada para impedir que um usuário marque a si mesmo como paquera, o que é considerado uma operação inválida no sistema.
 */
public class SelfCrushNotAllowedException extends Exception{
    public SelfCrushNotAllowedException(){
        super("Usuário não pode ser paquera de si mesmo.");
    }
}