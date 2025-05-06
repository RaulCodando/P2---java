package br.ufal.ic.p2.jackut.sistema;

import br.ufal.ic.p2.jackut.exceptions.*;

/**
 * Classe Facade atua como uma interface simplificada para intera��o com o sistema.
 * Respons�vel por gerenciar chamadas aos m�todos do sistema e garantir que ele esteja atualizado.
 */
public class Facade {
    private final Sistema sistema = new Sistema();
    private final GerenciadorDeUsuario gerenciadorDeUsuario = new GerenciadorDeUsuario(sistema.getUsuarios(), sistema.getComunidades());
    private final GerenciadorDeComunidades gerenciadorDeComunidades = new GerenciadorDeComunidades(sistema.getUsuarios(), sistema.getComunidades());
    private final GerenciadorDeRelacionamentos gerenciadorDeRelacionamentos = new GerenciadorDeRelacionamentos(sistema.getUsuarios(), sistema.getComunidades());

    /**
     * Remove todos os dados do sistema, resetando para o estado inicial.
     */
    public void zerarSistema(){
        sistema.zerarSistema();
    }

    /**
     * Retorna um atributo espec�fico de um usu�rio.
     * @param login Login do usu�rio.
     * @param atributo Nome do atributo a ser consultado.
     * @return O valor do atributo solicitado.
     * @throws Exception Se o usu�rio n�o for encontrado ou o atributo for inv�lido.
     */
    public String getAtributoUsuario(String login, String atributo) throws Exception{
        return gerenciadorDeUsuario.getAtributoUsuario(login, atributo);
    }

    /**
     * Cria um novo usu�rio no sistema.
     * @param login Login do novo usu�rio.
     * @param senha Senha do novo usu�rio.
     * @param nome Nome do novo usu�rio.
     * @throws Exception Se o login j� estiver em uso ou os dados forem inv�lidos.
     */
    public void criarUsuario(String login, String senha, String nome) throws Exception{
        gerenciadorDeUsuario.criarUsuario(login, senha, nome);
    }

    /**
     * Abre uma sess�o para um usu�rio autenticado.
     * @param login Login do usu�rio.
     * @param senha Senha do usu�rio.
     * @return O identificador da sess�o aberta.
     * @throws Exception Se as credenciais estiverem incorretas.
     */
    public String abrirSessao(String login, String senha) throws Exception{
        return gerenciadorDeUsuario.abrirSessao(login, senha);
    }

    /**
     * Edita um atributo do perfil de um usu�rio.
     * @param id Identificador do usu�rio.
     * @param atributo Nome do atributo a ser editado.
     * @param valor Novo valor do atributo.
     * @throws InvalidUserException Se o usu�rio n�o for encontrado.
     */
    public void editarPerfil(String id, String atributo, String valor) throws InvalidUserException{
        gerenciadorDeUsuario.editarPerfil(id, atributo, valor);
    }

    /**
     * Verifica se dois usu�rios s�o amigos.
     * @param login Login do primeiro usu�rio.
     * @param amigo Login do segundo usu�rio.
     * @return true se forem amigos, false caso contr�rio.
     * @throws InvalidUserException Se um dos usu�rios n�o for encontrado.
     */
    public boolean ehAmigo(String login, String amigo) throws InvalidUserException{
        return gerenciadorDeRelacionamentos.ehAmigo(login, amigo);
    }

    /**
     * Adiciona um amigo ao usu�rio especificado.
     * @param id Login do usu�rio que deseja adicionar um amigo.
     * @param amigo Login do usu�rio a ser adicionado.
     * @throws Exception Se o usu�rio n�o existir ou a adi��o for inv�lida.
     */
    public void adicionarAmigo(String id, String amigo) throws Exception{
        gerenciadorDeRelacionamentos.adicionarAmigo(id, amigo);
    }

    /**
     * Obt�m a lista de amigos de um usu�rio.
     * @param login Login do usu�rio.
     * @return Uma string contendo os amigos do usu�rio.
     */
    public String getAmigos(String login){
        return gerenciadorDeRelacionamentos.getAmigos(login);
    }

    /**
     * Envia um recado para outro usu�rio.
     * @param id Login do remetente.
     * @param destinatario Login do destinat�rio.
     * @param recado Conte�do do recado a ser enviado.
     * @throws Exception Se um dos usu�rios n�o existir ou a opera��o for inv�lida.
     */
    public void enviarRecado(String id, String destinatario, String recado) throws Exception{
        gerenciadorDeUsuario.enviarRecado(id, destinatario, recado);
    }

    /**
     * L� o pr�ximo recado dispon�vel de um usu�rio.
     * @param id Login do usu�rio que deseja ler o recado.
     * @return O conte�do do recado lido.
     * @throws Exception Se o usu�rio n�o existir ou n�o houver recados.
     */
    public String lerRecado(String id) throws Exception{
        return gerenciadorDeUsuario.lerRecado(id);
    }

    /**
     * L� a pr�xima mensagem dispon�vel de um usu�rio.
     * @param id Login do usu�rio que deseja ler a mensagem.
     * @return O conte�do da mensagem lido.
     * @throws Exception Se o usu�rio n�o existir ou n�o houver mensagens.
     */
    public String lerMensagem(String id) throws Exception{
        return gerenciadorDeComunidades.lerMensagem(id);
    }

    /**
     * Encerra o sistema, salvando os dados e limpando os usu�rios cadastrados.
     */
    public void encerrarSistema(){
        sistema.encerrarSistema();
    }

    /**
     * Cria uma nova comunidade com o nome e descri��o especificados, associando-a ao usu�rio da sess�o fornecida.
     * Lan�a uma exce��o se j� existir uma comunidade com o mesmo nome.
     */
    public void criarComunidade(String sessao, String nome, String descricao) throws CommunityAlreadyExistsException{
        gerenciadorDeComunidades.criarComunidade(sessao, nome, descricao);
    }

    /**
     * Retorna a descri��o da comunidade especificada pelo nome.
     * Lan�a uma exce��o se a comunidade n�o existir.
     */
    public String getDescricaoComunidade(String nome) throws InvalidCommunityException{
        return gerenciadorDeComunidades.getDescricaoComunidade(nome);
    }

    /**
     * Retorna o login do dono (criador) da comunidade informada.
     * Lan�a uma exce��o se a comunidade n�o for encontrada.
     */
    public String getDonoComunidade(String nome) throws InvalidCommunityException{
        return gerenciadorDeComunidades.getDonoComunidade(nome);
    }

    /**
     * Retorna uma representa��o textual (provavelmente formatada) dos membros da comunidade especificada.
     * Lan�a uma exce��o caso a comunidade n�o exista.
     */
    public String getMembrosComunidade(String nome) throws InvalidCommunityException{
        return gerenciadorDeComunidades.getMembrosComunidade(nome);
    }

    /**
     * Retorna uma lista (formatada como string) das comunidades das quais o usu�rio com o login fornecido participa.
     * Lan�a uma exce��o se o usu�rio n�o existir.
     */
    public String getComunidades(String login) throws InvalidUserException{
        return gerenciadorDeComunidades.getComunidades(login);
    }

    /**
     * Adiciona o usu�rio da sess�o atual como membro de uma comunidade espec�fica.
     * Lan�a uma exce��o gen�rica se ocorrer algum erro (ex: comunidade n�o existe, usu�rio j� � membro, etc).
     */
    public void adicionarComunidade(String sessao, String nome) throws Exception{
        gerenciadorDeComunidades.adicionarComunidade(sessao, nome);
    }

    /**
     * Envia uma mensagem para uma comunidade da qual o usu�rio participa.
     * @param id Login do usu�rio remetente.
     * @param comunidade Nome da comunidade destinat�ria.
     * @param mensagem Conte�do da mensagem.
     * @throws Exception Se o usu�rio ou a comunidade n�o existir, ou se o envio for inv�lido.
     */
    public void enviarMensagem(String id, String comunidade, String mensagem) throws Exception{
        gerenciadorDeComunidades.enviarMensagem(id, comunidade, mensagem);
    }

    /**
     * Verifica se um usu�rio � f� de outro.
     * @param login Login do poss�vel f�.
     * @param idolo Login do poss�vel �dolo.
     * @return true se for f�, false caso contr�rio.
     */
    public boolean ehFa(String login, String idolo){
        return gerenciadorDeRelacionamentos.ehFa(login, idolo);
    }

    /**
     * Adiciona um �dolo � lista de �dolos de um usu�rio.
     * @param id Login do f�.
     * @param idolo Login do usu�rio a ser adicionado como �dolo.
     * @throws Exception Se os logins forem inv�lidos ou a opera��o n�o for permitida.
     */
    public void adicionarIdolo(String id, String idolo) throws Exception{
        gerenciadorDeRelacionamentos.adicionarIdolo(id, idolo);
    }

    /**
     * Retorna a lista de f�s de um usu�rio.
     * @param login Login do usu�rio consultado.
     * @return String contendo os logins dos f�s.
     */
    public String getFas(String login){
        return gerenciadorDeRelacionamentos.getFas(login);
    }

    /**
     * Verifica se um usu�rio � paquera de outro.
     * @param id Login do usu�rio.
     * @param paquera Login do poss�vel paquera.
     * @return true se for paquera, false caso contr�rio.
     */
    public boolean ehPaquera(String id, String paquera){
        return gerenciadorDeRelacionamentos.ehPaquera(id, paquera);
    }

    /**
     * Adiciona um paquera � lista de paqueras de um usu�rio.
     * @param id Login do usu�rio.
     * @param paquera Login do usu�rio a ser adicionado como paquera.
     * @throws Exception Se os logins forem inv�lidos ou a opera��o n�o for permitida.
     */
    public void adicionarPaquera(String id, String paquera) throws Exception{
        gerenciadorDeRelacionamentos.adicionarPaquera(id, paquera);
    }

    /**
     * Retorna a lista de paqueras de um usu�rio.
     * @param id Login do usu�rio.
     * @return String contendo os logins das paqueras.
     */
    public String getPaqueras(String id){
        return gerenciadorDeRelacionamentos.getPaqueras(id);
    }

    /**
     * Adiciona um inimigo � lista de inimigos de um usu�rio.
     * @param id Login do usu�rio.
     * @param inimigo Login do usu�rio a ser adicionado como inimigo.
     * @throws Exception Se os logins forem inv�lidos ou a opera��o n�o for permitida.
     */
    public void adicionarInimigo(String id, String inimigo) throws Exception{
        gerenciadorDeRelacionamentos.adicionarInimigo(id, inimigo);
    }

    /**
     * Remove um usu�rio do sistema.
     * @param id Login do usu�rio a ser removido.
     * @throws InvalidUserException Se o usu�rio n�o existir.
     */
    public void removerUsuario(String id) throws InvalidUserException{
        gerenciadorDeUsuario.removerUsuario(id);
    }
}