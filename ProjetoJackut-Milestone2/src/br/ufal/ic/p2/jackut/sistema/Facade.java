package br.ufal.ic.p2.jackut.sistema;

import br.ufal.ic.p2.jackut.exceptions.*;

/**
 * Classe Facade atua como uma interface simplificada para interação com o sistema.
 * Responsável por gerenciar chamadas aos métodos do sistema e garantir que ele esteja atualizado.
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
     * Retorna um atributo específico de um usuário.
     * @param login Login do usuário.
     * @param atributo Nome do atributo a ser consultado.
     * @return O valor do atributo solicitado.
     * @throws Exception Se o usuário não for encontrado ou o atributo for inválido.
     */
    public String getAtributoUsuario(String login, String atributo) throws Exception{
        return gerenciadorDeUsuario.getAtributoUsuario(login, atributo);
    }

    /**
     * Cria um novo usuário no sistema.
     * @param login Login do novo usuário.
     * @param senha Senha do novo usuário.
     * @param nome Nome do novo usuário.
     * @throws Exception Se o login já estiver em uso ou os dados forem inválidos.
     */
    public void criarUsuario(String login, String senha, String nome) throws Exception{
        gerenciadorDeUsuario.criarUsuario(login, senha, nome);
    }

    /**
     * Abre uma sessão para um usuário autenticado.
     * @param login Login do usuário.
     * @param senha Senha do usuário.
     * @return O identificador da sessão aberta.
     * @throws Exception Se as credenciais estiverem incorretas.
     */
    public String abrirSessao(String login, String senha) throws Exception{
        return gerenciadorDeUsuario.abrirSessao(login, senha);
    }

    /**
     * Edita um atributo do perfil de um usuário.
     * @param id Identificador do usuário.
     * @param atributo Nome do atributo a ser editado.
     * @param valor Novo valor do atributo.
     * @throws InvalidUserException Se o usuário não for encontrado.
     */
    public void editarPerfil(String id, String atributo, String valor) throws InvalidUserException{
        gerenciadorDeUsuario.editarPerfil(id, atributo, valor);
    }

    /**
     * Verifica se dois usuários são amigos.
     * @param login Login do primeiro usuário.
     * @param amigo Login do segundo usuário.
     * @return true se forem amigos, false caso contrário.
     * @throws InvalidUserException Se um dos usuários não for encontrado.
     */
    public boolean ehAmigo(String login, String amigo) throws InvalidUserException{
        return gerenciadorDeRelacionamentos.ehAmigo(login, amigo);
    }

    /**
     * Adiciona um amigo ao usuário especificado.
     * @param id Login do usuário que deseja adicionar um amigo.
     * @param amigo Login do usuário a ser adicionado.
     * @throws Exception Se o usuário não existir ou a adição for inválida.
     */
    public void adicionarAmigo(String id, String amigo) throws Exception{
        gerenciadorDeRelacionamentos.adicionarAmigo(id, amigo);
    }

    /**
     * Obtém a lista de amigos de um usuário.
     * @param login Login do usuário.
     * @return Uma string contendo os amigos do usuário.
     */
    public String getAmigos(String login){
        return gerenciadorDeRelacionamentos.getAmigos(login);
    }

    /**
     * Envia um recado para outro usuário.
     * @param id Login do remetente.
     * @param destinatario Login do destinatário.
     * @param recado Conteúdo do recado a ser enviado.
     * @throws Exception Se um dos usuários não existir ou a operação for inválida.
     */
    public void enviarRecado(String id, String destinatario, String recado) throws Exception{
        gerenciadorDeUsuario.enviarRecado(id, destinatario, recado);
    }

    /**
     * Lê o próximo recado disponível de um usuário.
     * @param id Login do usuário que deseja ler o recado.
     * @return O conteúdo do recado lido.
     * @throws Exception Se o usuário não existir ou não houver recados.
     */
    public String lerRecado(String id) throws Exception{
        return gerenciadorDeUsuario.lerRecado(id);
    }

    /**
     * Lê a próxima mensagem disponível de um usuário.
     * @param id Login do usuário que deseja ler a mensagem.
     * @return O conteúdo da mensagem lido.
     * @throws Exception Se o usuário não existir ou não houver mensagens.
     */
    public String lerMensagem(String id) throws Exception{
        return gerenciadorDeComunidades.lerMensagem(id);
    }

    /**
     * Encerra o sistema, salvando os dados e limpando os usuários cadastrados.
     */
    public void encerrarSistema(){
        sistema.encerrarSistema();
    }

    /**
     * Cria uma nova comunidade com o nome e descrição especificados, associando-a ao usuário da sessão fornecida.
     * Lança uma exceção se já existir uma comunidade com o mesmo nome.
     */
    public void criarComunidade(String sessao, String nome, String descricao) throws CommunityAlreadyExistsException{
        gerenciadorDeComunidades.criarComunidade(sessao, nome, descricao);
    }

    /**
     * Retorna a descrição da comunidade especificada pelo nome.
     * Lança uma exceção se a comunidade não existir.
     */
    public String getDescricaoComunidade(String nome) throws InvalidCommunityException{
        return gerenciadorDeComunidades.getDescricaoComunidade(nome);
    }

    /**
     * Retorna o login do dono (criador) da comunidade informada.
     * Lança uma exceção se a comunidade não for encontrada.
     */
    public String getDonoComunidade(String nome) throws InvalidCommunityException{
        return gerenciadorDeComunidades.getDonoComunidade(nome);
    }

    /**
     * Retorna uma representação textual (provavelmente formatada) dos membros da comunidade especificada.
     * Lança uma exceção caso a comunidade não exista.
     */
    public String getMembrosComunidade(String nome) throws InvalidCommunityException{
        return gerenciadorDeComunidades.getMembrosComunidade(nome);
    }

    /**
     * Retorna uma lista (formatada como string) das comunidades das quais o usuário com o login fornecido participa.
     * Lança uma exceção se o usuário não existir.
     */
    public String getComunidades(String login) throws InvalidUserException{
        return gerenciadorDeComunidades.getComunidades(login);
    }

    /**
     * Adiciona o usuário da sessão atual como membro de uma comunidade específica.
     * Lança uma exceção genérica se ocorrer algum erro (ex: comunidade não existe, usuário já é membro, etc).
     */
    public void adicionarComunidade(String sessao, String nome) throws Exception{
        gerenciadorDeComunidades.adicionarComunidade(sessao, nome);
    }

    /**
     * Envia uma mensagem para uma comunidade da qual o usuário participa.
     * @param id Login do usuário remetente.
     * @param comunidade Nome da comunidade destinatária.
     * @param mensagem Conteúdo da mensagem.
     * @throws Exception Se o usuário ou a comunidade não existir, ou se o envio for inválido.
     */
    public void enviarMensagem(String id, String comunidade, String mensagem) throws Exception{
        gerenciadorDeComunidades.enviarMensagem(id, comunidade, mensagem);
    }

    /**
     * Verifica se um usuário é fã de outro.
     * @param login Login do possível fã.
     * @param idolo Login do possível ídolo.
     * @return true se for fã, false caso contrário.
     */
    public boolean ehFa(String login, String idolo){
        return gerenciadorDeRelacionamentos.ehFa(login, idolo);
    }

    /**
     * Adiciona um ídolo à lista de ídolos de um usuário.
     * @param id Login do fã.
     * @param idolo Login do usuário a ser adicionado como ídolo.
     * @throws Exception Se os logins forem inválidos ou a operação não for permitida.
     */
    public void adicionarIdolo(String id, String idolo) throws Exception{
        gerenciadorDeRelacionamentos.adicionarIdolo(id, idolo);
    }

    /**
     * Retorna a lista de fãs de um usuário.
     * @param login Login do usuário consultado.
     * @return String contendo os logins dos fãs.
     */
    public String getFas(String login){
        return gerenciadorDeRelacionamentos.getFas(login);
    }

    /**
     * Verifica se um usuário é paquera de outro.
     * @param id Login do usuário.
     * @param paquera Login do possível paquera.
     * @return true se for paquera, false caso contrário.
     */
    public boolean ehPaquera(String id, String paquera){
        return gerenciadorDeRelacionamentos.ehPaquera(id, paquera);
    }

    /**
     * Adiciona um paquera à lista de paqueras de um usuário.
     * @param id Login do usuário.
     * @param paquera Login do usuário a ser adicionado como paquera.
     * @throws Exception Se os logins forem inválidos ou a operação não for permitida.
     */
    public void adicionarPaquera(String id, String paquera) throws Exception{
        gerenciadorDeRelacionamentos.adicionarPaquera(id, paquera);
    }

    /**
     * Retorna a lista de paqueras de um usuário.
     * @param id Login do usuário.
     * @return String contendo os logins das paqueras.
     */
    public String getPaqueras(String id){
        return gerenciadorDeRelacionamentos.getPaqueras(id);
    }

    /**
     * Adiciona um inimigo à lista de inimigos de um usuário.
     * @param id Login do usuário.
     * @param inimigo Login do usuário a ser adicionado como inimigo.
     * @throws Exception Se os logins forem inválidos ou a operação não for permitida.
     */
    public void adicionarInimigo(String id, String inimigo) throws Exception{
        gerenciadorDeRelacionamentos.adicionarInimigo(id, inimigo);
    }

    /**
     * Remove um usuário do sistema.
     * @param id Login do usuário a ser removido.
     * @throws InvalidUserException Se o usuário não existir.
     */
    public void removerUsuario(String id) throws InvalidUserException{
        gerenciadorDeUsuario.removerUsuario(id);
    }
}