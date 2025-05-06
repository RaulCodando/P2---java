package br.ufal.ic.p2.jackut.sistema;

import br.ufal.ic.p2.jackut.exceptions.InvalidUserException;
import br.ufal.ic.p2.jackut.usuario.*;
import java.io.Serializable;
import java.util.HashMap;

/**
 * Classe abstrata responsável por fornecer funcionalidades comuns de gerenciamento
 * para componentes do sistema Jackut, como usuários e comunidades.
 *
 * Essa classe serve como base para outras classes concretas (ex: GerenciadorDeUsuarios,
 * GerenciadorDeComunidades), encapsulando o acesso e manipulação dos usuários e comunidades.
 *
 * Implementa Serializable para permitir persistência do estado dos gerenciadores.
 */
public abstract class Gerenciador implements Serializable {

    // Mapa de todos os usuários do sistema, indexados pelo login
    private HashMap<String, Usuario> usuarios;

    // Mapa de todas as comunidades do sistema, indexadas pelo nome
    private HashMap<String, Comunidade> comunidades;

    /**
     * Construtor da classe que recebe as estruturas de dados compartilhadas entre os gerenciadores.
     *
     * @param usuarios Mapa contendo os usuários registrados no sistema
     * @param comunidades Mapa contendo as comunidades registradas no sistema
     */
    public Gerenciador(HashMap<String, Usuario> usuarios, HashMap<String, Comunidade> comunidades){
        this.usuarios = usuarios;
        this.comunidades = comunidades;
    }

    /**
     * Retorna o mapa de usuários do sistema.
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
     * Envia um recado automático para um usuário, com o remetente fixo "Sistema".
     *
     * @param destinatario Login do usuário que receberá o recado
     * @param recado Texto do recado a ser enviado
     * @throws InvalidUserException Se o destinatário não estiver registrado
     */
    public void recadoAutomatico(String destinatario, String recado) throws InvalidUserException {
        if (!usuarios.containsKey(destinatario)) {
            throw new InvalidUserException();
        }

        usuarios.get(destinatario).getRecados().add(new Texto("Sistema", recado));
    }
}
