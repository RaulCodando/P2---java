package br.ufal.ic.p2.jackut;

/**
 * Classe Facade atua como uma interface simplificada para interação com o sistema.
 * Responsável por gerenciar chamadas aos métodos do sistema e garantir que ele esteja atualizado.
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
     * Atualiza o sistema, recriando-o caso não haja usuários cadastrados.
     */
    public void atualizarSistema(){
        if(sistema.getUsuarios().isEmpty()){
            sistema = new Sistema();
        }
    }

    /**
     * Retorna um atributo específico de um usuário.
     * @param login Login do usuário.
     * @param atributo Nome do atributo a ser consultado.
     * @return O valor do atributo solicitado.
     * @throws Exception Se o usuário não for encontrado ou o atributo for inválido.
     */
    public Object getAtributoUsuario(String login, String atributo) throws Exception{
        this.atualizarSistema();
        return sistema.getAtributoUsuario(login, atributo);
    }

    /**
     * Cria um novo usuário no sistema.
     * @param login Login do novo usuário.
     * @param senha Senha do novo usuário.
     * @param nome Nome do novo usuário.
     * @throws Exception Se o login já estiver em uso ou os dados forem inválidos.
     */
    public void criarUsuario(String login, String senha, String nome) throws Exception{
        this.atualizarSistema();
        sistema.criarUsuario(login, senha, nome);
    }

    /**
     * Abre uma sessão para um usuário autenticado.
     * @param login Login do usuário.
     * @param senha Senha do usuário.
     * @return O identificador da sessão aberta.
     * @throws Exception Se as credenciais estiverem incorretas.
     */
    public String abrirSessao(String login, String senha) throws Exception{
        this.atualizarSistema();
        return sistema.abrirSessao(login, senha);
    }

    /**
     * Edita um atributo do perfil de um usuário.
     * @param id Identificador do usuário.
     * @param atributo Nome do atributo a ser editado.
     * @param valor Novo valor do atributo.
     * @throws InvalidUserException Se o usuário não for encontrado.
     */
    public void editarPerfil(String id, String atributo, String valor) throws InvalidUserException{
        this.atualizarSistema();
        sistema.editarPerfil(id, atributo, valor);
    }

    /**
     * Verifica se dois usuários são amigos.
     * @param login Login do primeiro usuário.
     * @param amigo Login do segundo usuário.
     * @return true se forem amigos, false caso contrário.
     * @throws InvalidUserException Se um dos usuários não for encontrado.
     */
    public boolean ehAmigo(String login, String amigo) throws InvalidUserException{
        this.atualizarSistema();
        return sistema.ehAmigo(login, amigo);
    }

    /**
     * Adiciona um amigo ao usuário especificado.
     * @param id Login do usuário que deseja adicionar um amigo.
     * @param amigo Login do usuário a ser adicionado.
     * @throws Exception Se o usuário não existir ou a adição for inválida.
     */
    public void adicionarAmigo(String id, String amigo) throws Exception{
        sistema.adicionarAmigo(id, amigo);
    }

    /**
     * Obtém a lista de amigos de um usuário.
     * @param login Login do usuário.
     * @return Uma string contendo os amigos do usuário.
     */
    public String getAmigos(String login){
        return sistema.getAmigos(login);
    }

    /**
     * Envia um recado para outro usuário.
     * @param id Login do remetente.
     * @param destinatario Login do destinatário.
     * @param recado Conteúdo do recado a ser enviado.
     * @throws Exception Se um dos usuários não existir ou a operação for inválida.
     */
    public void enviarRecado(String id, String destinatario, String recado) throws Exception{
        sistema.enviarRecado(id, destinatario, recado);
    }

    /**
     * Lê o próximo recado disponível de um usuário.
     * @param id Login do usuário que deseja ler o recado.
     * @return O conteúdo do recado lido.
     * @throws Exception Se o usuário não existir ou não houver recados.
     */
    public String lerRecado(String id) throws Exception{
        return sistema.lerRecado(id);
    }

    /**
     * Encerra o sistema, salvando os dados e limpando os usuários cadastrados.
     */
    public void encerrarSistema(){
        sistema.encerrarSistema();
    }
}