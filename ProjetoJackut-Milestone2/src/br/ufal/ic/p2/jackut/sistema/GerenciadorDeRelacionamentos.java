package br.ufal.ic.p2.jackut.sistema;

import br.ufal.ic.p2.jackut.exceptions.*;
import br.ufal.ic.p2.jackut.usuario.*;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe responsável por gerenciar os relacionamentos entre usuários na plataforma Jackut.
 * Ela permite a adição e verificação de amigos, ídolos, paqueras e inimigos, além de gerar
 * mensagens automáticas em casos de paqueras mútuas. Todas as interações levam em consideração
 * regras de validação, como proibição de auto-relacionamentos e impedimentos por inimizade.
 */
public class GerenciadorDeRelacionamentos extends Gerenciador {

    public GerenciadorDeRelacionamentos(HashMap<String, Usuario> usuarios, HashMap<String, Comunidade> comunidades){
        super(usuarios, comunidades);
    }

    /**
     * Adiciona um usuário como amigo, enviando um pedido de amizade ou aceitando um convite pendente.
     * @param id Login do usuário que deseja adicionar um amigo.
     * @param amigo Login do usuário a ser adicionado como amigo.
     * @throws InvalidUserException Se algum dos usuários não estiver cadastrado.
     * @throws UserIsAlreadyFriendException Se o usuário tentar adicionar a si mesmo como amigo.
     * @throws UserIsAlreadyFriendException Se o usuário já for amigo ou houver um pedido pendente.
     */
    public void adicionarAmigo(String id, String amigo) throws Exception {
        if(!this.getUsuarios().containsKey(id) || !this.getUsuarios().containsKey(amigo)){
            throw new InvalidUserException();
        }
        if(id.equals(amigo)){
            throw new SelfFriendshipNotAllowedException();
        }
        if(this.getUsuarios().get(id).getPedidos().contains(this.getUsuarios().get(amigo))){
            throw new UserIsAlreadyFriendException("Usuário já está adicionado como amigo, esperando aceitação do convite.");
        }
        if(this.getUsuarios().get(amigo).getRelacionamento().getInimigos().contains(this.getUsuarios().get(id))){
            throw new NemesisException(this.getUsuarios().get(amigo).getPerfil().getNome());
        }

        LinkedHashSet<Usuario> amigosUsr = this.getUsuarios().get(id).getRelacionamento().getAmigos();

        if(amigosUsr.contains(this.getUsuarios().get(amigo))){
            throw new UserIsAlreadyFriendException("Usuário já está adicionado como amigo.");
        }
        if(this.getUsuarios().get(amigo).getPedidos().contains(this.getUsuarios().get(id))){
            LinkedHashSet<Usuario> amigosAmg = this.getUsuarios().get(amigo).getRelacionamento().getAmigos();
            amigosUsr.add(this.getUsuarios().get(amigo));
            amigosAmg.add(this.getUsuarios().get(id));

            this.getUsuarios().get(amigo).getPedidos().remove(this.getUsuarios().get(id));
        }
        else{
            this.getUsuarios().get(id).getPedidos().add(this.getUsuarios().get(amigo));
        }
    }

    /**
     * Verifica se dois usuários são amigos.
     * @param login Login do primeiro usuário.
     * @param amigo Login do possível amigo.
     * @return true se forem amigos, false caso contrário.
     * @throws InvalidUserException Se o usuário não estiver cadastrado.
     */
    public boolean ehAmigo(String login, String amigo) throws InvalidUserException {
        if(!this.getUsuarios().containsKey(login)){
            throw new InvalidUserException();
        }
        return this.getUsuarios().get(login).getRelacionamento().getAmigos().contains(this.getUsuarios().get(amigo));
    }

    /**
     * Retorna uma lista formatada de amigos de um usuário.
     * @param login Login do usuário.
     * @return String formatada com os amigos do usuário.
     */
    public String getAmigos(String login){
        List<String> amigos = this.getUsuarios().get(login).getRelacionamento().getAmigos().stream()
                .map(Usuario::getLogin)
                .collect(Collectors.toList());
        return "{" + String.join(",", amigos) + "}";
    }

    /**
     * Verifica se um usuário é fã de outro.
     * @param login Login do fã.
     * @param idolo Login do ídolo.
     * @return true se for fã, false caso contrário.
     */
    public boolean ehFa(String login, String idolo){
        return this.getUsuarios().get(login).getRelacionamento().getIdolo().contains(this.getUsuarios().get(idolo));
    }

    /**
     * Adiciona um ídolo ao usuário.
     * @param id Login do fã.
     * @param idolo Login do ídolo.
     * @throws Exception Se o usuário tentar idolatrar a si mesmo, se algum usuário não estiver cadastrado,
     * ou se o ídolo já for inimigo do usuário.
     */
    public void adicionarIdolo(String id, String idolo) throws Exception {
        if(id.equals(idolo)){
            throw new SelfIdolNotAllowedException();
        }
        if(!this.getUsuarios().containsKey(id) || !this.getUsuarios().containsKey(idolo)){
            throw new InvalidUserException();
        }
        if(this.getUsuarios().get(idolo).getRelacionamento().getInimigos().contains(this.getUsuarios().get(id))){
            throw new NemesisException(this.getUsuarios().get(idolo).getPerfil().getNome());
        }
        LinkedHashSet<Usuario> idolos = this.getUsuarios().get(id).getRelacionamento().getIdolo();
        if(idolos.contains(this.getUsuarios().get(idolo))){
            throw new UserIsAlreadyIdolException();
        }
        idolos.add(this.getUsuarios().get(idolo));
        this.getUsuarios().get(idolo).getRelacionamento().getFas().add(this.getUsuarios().get(id));
    }

    /**
     * Retorna uma lista formatada de fãs de um usuário.
     * @param login Login do ídolo.
     * @return String formatada com os fãs do usuário.
     */
    public String getFas(String login){
        List<String> fas = this.getUsuarios().get(login).getRelacionamento().getFas().stream()
                .map(Usuario::getLogin)
                .collect(Collectors.toList());
        return "{" + String.join(",", fas) + "}";
    }

    /**
     * Verifica se um usuário é paquera de outro.
     * @param id Login do usuário.
     * @param paquera Login do possível paquera.
     * @return true se for paquera, false caso contrário.
     */
    public boolean ehPaquera(String id, String paquera){
        return this.getUsuarios().get(id).getRelacionamento().getPaqueras().contains(this.getUsuarios().get(paquera));
    }

    /**
     * Adiciona um paquera ao usuário.
     * Se o paquera também adicionou o usuário, envia recados automáticos para ambos.
     * @param id Login do usuário.
     * @param paquera Login do usuário paquerado.
     * @throws Exception Se o usuário tentar paquerar a si mesmo, se algum dos usuários não existir,
     * se já houver paquera, ou se o outro for inimigo.
     */
    public void adicionarPaquera(String id, String paquera) throws Exception {
        if(id.equals(paquera)){
            throw new SelfCrushNotAllowedException();
        }
        if(!this.getUsuarios().containsKey(id) || !this.getUsuarios().containsKey(paquera)){
            throw new InvalidUserException();
        }
        if(this.getUsuarios().get(paquera).getRelacionamento().getInimigos().contains(this.getUsuarios().get(id))){
            throw new NemesisException(this.getUsuarios().get(paquera).getPerfil().getNome());
        }

        LinkedHashSet<Usuario> paqueras = this.getUsuarios().get(id).getRelacionamento().getPaqueras();

        if(paqueras.contains(this.getUsuarios().get(paquera))){
            throw new UserIsAlreeadyCrushException();
        }

        paqueras.add(this.getUsuarios().get(paquera));
        if(this.getUsuarios().get(paquera).getRelacionamento().getPaqueras().contains(this.getUsuarios().get(id))){
            String mensagem1 = String.format("%s é seu paquera - Recado do Jackut.", this.getUsuarios().get(paquera).getPerfil().getNome());
            String mensagem2 = String.format("%s é seu paquera - Recado do Jackut.", this.getUsuarios().get(id).getPerfil().getNome());
            recadoAutomatico(id, mensagem1);
            recadoAutomatico(paquera, mensagem2);
        }
    }

    /**
     * Retorna uma lista formatada de paqueras de um usuário.
     * @param id Login do usuário.
     * @return String formatada com os logins dos paqueras.
     */
    public String getPaqueras(String id){
        List<String> paqueras = this.getUsuarios().get(id).getRelacionamento().getPaqueras().stream()
                .map(Usuario::getLogin)
                .collect(Collectors.toList());
        return "{" + String.join(",", paqueras) + "}";
    }

    /**
     * Adiciona um inimigo ao usuário.
     * @param id Login do usuário.
     * @param inimigo Login do inimigo.
     * @throws Exception Se o usuário tentar ser inimigo de si mesmo,
     * se algum dos usuários não existir, ou se o inimigo já estiver na lista.
     */
    public void adicionarInimigo(String id, String inimigo) throws Exception {
        if(id.equals(inimigo)){
            throw new SelfNemesisNotAllowedException();
        }
        if(!this.getUsuarios().containsKey(id) || !this.getUsuarios().containsKey(inimigo)){
            throw new InvalidUserException();
        }
        if(this.getUsuarios().get(id).getRelacionamento().getInimigos().contains(this.getUsuarios().get(inimigo))){
            throw new UserIsAlreadyNemesisException();
        }
        this.getUsuarios().get(id).getRelacionamento().getInimigos().add(this.getUsuarios().get(inimigo));
    }
}