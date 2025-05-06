package br.ufal.ic.p2.jackut.sistema;

import br.ufal.ic.p2.jackut.exceptions.InvalidUserException;
import br.ufal.ic.p2.jackut.usuario.*;
import java.io.Serializable;
import java.util.HashMap;

/**
 * Classe abstrata respons�vel por fornecer funcionalidades comuns de gerenciamento
 * para componentes do sistema Jackut, como usu�rios e comunidades.
 *
 * Essa classe serve como base para outras classes concretas (ex: GerenciadorDeUsuarios,
 * GerenciadorDeComunidades), encapsulando o acesso e manipula��o dos usu�rios e comunidades.
 *
 * Implementa Serializable para permitir persist�ncia do estado dos gerenciadores.
 */
public abstract class Gerenciador implements Serializable {

    // Mapa de todos os usu�rios do sistema, indexados pelo login
    private HashMap<String, Usuario> usuarios;

    // Mapa de todas as comunidades do sistema, indexadas pelo nome
    private HashMap<String, Comunidade> comunidades;

    /**
     * Construtor da classe que recebe as estruturas de dados compartilhadas entre os gerenciadores.
     *
     * @param usuarios Mapa contendo os usu�rios registrados no sistema
     * @param comunidades Mapa contendo as comunidades registradas no sistema
     */
    public Gerenciador(HashMap<String, Usuario> usuarios, HashMap<String, Comunidade> comunidades){
        this.usuarios = usuarios;
        this.comunidades = comunidades;
    }

    /**
     * Retorna o mapa de usu�rios do sistema.
     *
     * @return HashMap de logins para objetos Usuario
     */
    public HashMap<String, Usuario> getUsuarios() {
        return usuarios;
    }

    /**
     * Retorna o mapa de comunidades do sistema.
     *
     * @return HashMap de nomes para objetos Comunidade
     */
    public HashMap<String, Comunidade> getComunidades() {
        return comunidades;
    }

    /**
     * Envia um recado autom�tico para um usu�rio, com o remetente fixo "Sistema".
     *
     * @param destinatario Login do usu�rio que receber� o recado
     * @param recado Texto do recado a ser enviado
     * @throws InvalidUserException Se o destinat�rio n�o estiver registrado
     */
    public void recadoAutomatico(String destinatario, String recado) throws InvalidUserException {
        if (!usuarios.containsKey(destinatario)) {
            throw new InvalidUserException();
        }

        usuarios.get(destinatario).getRecados().add(new Texto("Sistema", recado));
    }
}
