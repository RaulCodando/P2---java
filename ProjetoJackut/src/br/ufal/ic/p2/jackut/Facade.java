package br.ufal.ic.p2.jackut;

/**
 * Classe Facade atua como uma interface simplificada para intera��o com o sistema.
 * Respons�vel por gerenciar chamadas aos m�todos do sistema e garantir que ele esteja atualizado.
 */
public class Facade {
    private Sistema sistema = new Sistema();

    /**
     * Remove todos os dados do sistema, resetando para o estado inicial.
     */
    public void zerarSistema(){
        sistema.zerarSistema();
    }

    /**
     * Atualiza o sistema, recriando-o caso n�o haja usu�rios cadastrados.
     */
    public void atualizarSistema(){
        if(sistema.getUsuarios().isEmpty()){
            sistema = new Sistema();
        }
    }

    /**
     * Retorna um atributo espec�fico de um usu�rio.
     * @param login Login do usu�rio.
     * @param atributo Nome do atributo a ser consultado.
     * @return O valor do atributo solicitado.
     * @throws Exception Se o usu�rio n�o for encontrado ou o atributo for inv�lido.
     */
    public Object getAtributoUsuario(String login, String atributo) throws Exception{
        this.atualizarSistema();
        return sistema.getAtributoUsuario(login, atributo);
    }

    /**
     * Cria um novo usu�rio no sistema.
     * @param login Login do novo usu�rio.
     * @param senha Senha do novo usu�rio.
     * @param nome Nome do novo usu�rio.
     * @throws Exception Se o login j� estiver em uso ou os dados forem inv�lidos.
     */
    public void criarUsuario(String login, String senha, String nome) throws Exception{
        this.atualizarSistema();
        sistema.criarUsuario(login, senha, nome);
    }

    /**
     * Abre uma sess�o para um usu�rio autenticado.
     * @param login Login do usu�rio.
     * @param senha Senha do usu�rio.
     * @return O identificador da sess�o aberta.
     * @throws Exception Se as credenciais estiverem incorretas.
     */
    public String abrirSessao(String login, String senha) throws Exception{
        this.atualizarSistema();
        return sistema.abrirSessao(login, senha);
    }

    /**
     * Edita um atributo do perfil de um usu�rio.
     * @param id Identificador do usu�rio.
     * @param atributo Nome do atributo a ser editado.
     * @param valor Novo valor do atributo.
     * @throws InvalidUserException Se o usu�rio n�o for encontrado.
     */
    public void editarPerfil(String id, String atributo, String valor) throws InvalidUserException{
        this.atualizarSistema();
        sistema.editarPerfil(id, atributo, valor);
    }

    /**
     * Verifica se dois usu�rios s�o amigos.
     * @param login Login do primeiro usu�rio.
     * @param amigo Login do segundo usu�rio.
     * @return true se forem amigos, false caso contr�rio.
     * @throws InvalidUserException Se um dos usu�rios n�o for encontrado.
     */
    public boolean ehAmigo(String login, String amigo) throws InvalidUserException{
        this.atualizarSistema();
        return sistema.ehAmigo(login, amigo);
    }

    /**
     * Adiciona um amigo ao usu�rio especificado.
     * @param id Login do usu�rio que deseja adicionar um amigo.
     * @param amigo Login do usu�rio a ser adicionado.
     * @throws Exception Se o usu�rio n�o existir ou a adi��o for inv�lida.
     */
    public void adicionarAmigo(String id, String amigo) throws Exception{
        sistema.adicionarAmigo(id, amigo);
    }

    /**
     * Obt�m a lista de amigos de um usu�rio.
     * @param login Login do usu�rio.
     * @return Uma string contendo os amigos do usu�rio.
     */
    public String getAmigos(String login){
        return sistema.getAmigos(login);
    }

    /**
     * Envia um recado para outro usu�rio.
     * @param id Login do remetente.
     * @param destinatario Login do destinat�rio.
     * @param recado Conte�do do recado a ser enviado.
     * @throws Exception Se um dos usu�rios n�o existir ou a opera��o for inv�lida.
     */
    public void enviarRecado(String id, String destinatario, String recado) throws Exception{
        sistema.enviarRecado(id, destinatario, recado);
    }

    /**
     * L� o pr�ximo recado dispon�vel de um usu�rio.
     * @param id Login do usu�rio que deseja ler o recado.
     * @return O conte�do do recado lido.
     * @throws Exception Se o usu�rio n�o existir ou n�o houver recados.
     */
    public String lerRecado(String id) throws Exception{
        return sistema.lerRecado(id);
    }

    /**
     * Encerra o sistema, salvando os dados e limpando os usu�rios cadastrados.
     */
    public void encerrarSistema(){
        sistema.encerrarSistema();
    }
}