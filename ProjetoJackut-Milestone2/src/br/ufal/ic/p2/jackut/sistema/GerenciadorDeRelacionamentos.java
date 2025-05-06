package br.ufal.ic.p2.jackut.sistema;

import br.ufal.ic.p2.jackut.exceptions.*;
import br.ufal.ic.p2.jackut.usuario.*;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe respons�vel por gerenciar os relacionamentos entre usu�rios na plataforma Jackut.
 * Ela permite a adi��o e verifica��o de amigos, �dolos, paqueras e inimigos, al�m de gerar
 * mensagens autom�ticas em casos de paqueras m�tuas. Todas as intera��es levam em considera��o
 * regras de valida��o, como proibi��o de auto-relacionamentos e impedimentos por inimizade.
 */
public class GerenciadorDeRelacionamentos extends Gerenciador {

    public GerenciadorDeRelacionamentos(HashMap<String, Usuario> usuarios, HashMap<String, Comunidade> comunidades){
        super(usuarios, comunidades);
    }

    /**
     * Adiciona um usu�rio como amigo, enviando um pedido de amizade ou aceitando um convite pendente.
     * @param id Login do usu�rio que deseja adicionar um amigo.
     * @param amigo Login do usu�rio a ser adicionado como amigo.
     * @throws InvalidUserException Se algum dos usu�rios n�o estiver cadastrado.
     * @throws UserIsAlreadyFriendException Se o usu�rio tentar adicionar a si mesmo como amigo.
     * @throws UserIsAlreadyFriendException Se o usu�rio j� for amigo ou houver um pedido pendente.
     */
    public void adicionarAmigo(String id, String amigo) throws Exception {
        if(!this.getUsuarios().containsKey(id) || !this.getUsuarios().containsKey(amigo)){
            throw new InvalidUserException();
        }
        if(id.equals(amigo)){
            throw new SelfFriendshipNotAllowedException();
        }
        if(this.getUsuarios().get(id).getPedidos().contains(this.getUsuarios().get(amigo))){
            throw new UserIsAlreadyFriendException("Usu�rio j� est� adicionado como amigo, esperando aceita��o do convite.");
        }
        if(this.getUsuarios().get(amigo).getRelacionamento().getInimigos().contains(this.getUsuarios().get(id))){
            throw new NemesisException(this.getUsuarios().get(amigo).getPerfil().getNome());
        }

        LinkedHashSet<Usuario> amigosUsr = this.getUsuarios().get(id).getRelacionamento().getAmigos();

        if(amigosUsr.contains(this.getUsuarios().get(amigo))){
            throw new UserIsAlreadyFriendException("Usu�rio j� est� adicionado como amigo.");
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
     * Verifica se dois usu�rios s�o amigos.
     * @param login Login do primeiro usu�rio.
     * @param amigo Login do poss�vel amigo.
     * @return true se forem amigos, false caso contr�rio.
     * @throws InvalidUserException Se o usu�rio n�o estiver cadastrado.
     */
    public boolean ehAmigo(String login, String amigo) throws InvalidUserException {
        if(!this.getUsuarios().containsKey(login)){
            throw new InvalidUserException();
        }
        return this.getUsuarios().get(login).getRelacionamento().getAmigos().contains(this.getUsuarios().get(amigo));
    }

    /**
     * Retorna uma lista formatada de amigos de um usu�rio.
     * @param login Login do usu�rio.
     * @return String formatada com os amigos do usu�rio.
     */
    public String getAmigos(String login){
        List<String> amigos = this.getUsuarios().get(login).getRelacionamento().getAmigos().stream()
                .map(Usuario::getLogin)
                .collect(Collectors.toList());
        return "{" + String.join(",", amigos) + "}";
    }

    /**
     * Verifica se um usu�rio � f� de outro.
     * @param login Login do f�.
     * @param idolo Login do �dolo.
     * @return true se for f�, false caso contr�rio.
     */
    public boolean ehFa(String login, String idolo){
        return this.getUsuarios().get(login).getRelacionamento().getIdolo().contains(this.getUsuarios().get(idolo));
    }

    /**
     * Adiciona um �dolo ao usu�rio.
     * @param id Login do f�.
     * @param idolo Login do �dolo.
     * @throws Exception Se o usu�rio tentar idolatrar a si mesmo, se algum usu�rio n�o estiver cadastrado,
     * ou se o �dolo j� for inimigo do usu�rio.
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
     * Retorna uma lista formatada de f�s de um usu�rio.
     * @param login Login do �dolo.
     * @return String formatada com os f�s do usu�rio.
     */
    public String getFas(String login){
        List<String> fas = this.getUsuarios().get(login).getRelacionamento().getFas().stream()
                .map(Usuario::getLogin)
                .collect(Collectors.toList());
        return "{" + String.join(",", fas) + "}";
    }

    /**
     * Verifica se um usu�rio � paquera de outro.
     * @param id Login do usu�rio.
     * @param paquera Login do poss�vel paquera.
     * @return true se for paquera, false caso contr�rio.
     */
    public boolean ehPaquera(String id, String paquera){
        return this.getUsuarios().get(id).getRelacionamento().getPaqueras().contains(this.getUsuarios().get(paquera));
    }

    /**
     * Adiciona um paquera ao usu�rio.
     * Se o paquera tamb�m adicionou o usu�rio, envia recados autom�ticos para ambos.
     * @param id Login do usu�rio.
     * @param paquera Login do usu�rio paquerado.
     * @throws Exception Se o usu�rio tentar paquerar a si mesmo, se algum dos usu�rios n�o existir,
     * se j� houver paquera, ou se o outro for inimigo.
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
            String mensagem1 = String.format("%s � seu paquera - Recado do Jackut.", this.getUsuarios().get(paquera).getPerfil().getNome());
            String mensagem2 = String.format("%s � seu paquera - Recado do Jackut.", this.getUsuarios().get(id).getPerfil().getNome());
            recadoAutomatico(id, mensagem1);
            recadoAutomatico(paquera, mensagem2);
        }
    }

    /**
     * Retorna uma lista formatada de paqueras de um usu�rio.
     * @param id Login do usu�rio.
     * @return String formatada com os logins dos paqueras.
     */
    public String getPaqueras(String id){
        List<String> paqueras = this.getUsuarios().get(id).getRelacionamento().getPaqueras().stream()
                .map(Usuario::getLogin)
                .collect(Collectors.toList());
        return "{" + String.join(",", paqueras) + "}";
    }

    /**
     * Adiciona um inimigo ao usu�rio.
     * @param id Login do usu�rio.
     * @param inimigo Login do inimigo.
     * @throws Exception Se o usu�rio tentar ser inimigo de si mesmo,
     * se algum dos usu�rios n�o existir, ou se o inimigo j� estiver na lista.
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