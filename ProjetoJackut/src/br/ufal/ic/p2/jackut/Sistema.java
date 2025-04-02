package br.ufal.ic.p2.jackut;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Classe que representa o sistema da aplica��o, respons�vel pelo gerenciamento de usu�rios e suas intera��es.
 */
public class Sistema {
    private HashMap<String, Usuario> usuarios; // Mapa que armazena os usu�rios do sistema, indexados pelo login.

    /**
     * Construtor da classe Sistema.
     * Se um arquivo de usu�rios existir,
     * carrega os dados salvos; caso contr�rio,
     * inicializa um novo sistema.
     */
    public Sistema(){
        File file = new File("Usuarios.ser");

        if(!file.exists()){
            usuarios = new HashMap<>();
        }
        else{
            try(ObjectInputStream objectInputStream1 = new ObjectInputStream(new FileInputStream("Usuarios.ser"))){
                usuarios = (HashMap<String, Usuario>) objectInputStream1.readObject();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    /**
     * Obt�m um atributo espec�fico de um usu�rio.
     * @param login Login do usu�rio.
     * @param atributo Nome do atributo desejado.
     * @return O valor do atributo solicitado.
     * @throws InvalidUserException Se o usu�rio n�o estiver cadastrado.
     * @throws NullAttributeException Se o atributo n�o estiver preenchido.
     */
    public String getAtributoUsuario(String login, String atributo) throws InvalidUserException, NullAttributeException {
        if(!usuarios.containsKey(login)){
            throw new InvalidUserException("Usu�rio n�o cadastrado.");
        }

        String atributoUsuario = switch (atributo) {
            case "nome" -> usuarios.get(login).getNome();
            case "login" -> usuarios.get(login).getLogin();
            case "descricao" -> usuarios.get(login).getDescricao();
            case "estadoCivil" -> usuarios.get(login).getEstadoCivil();
            case "aniversario" -> usuarios.get(login).getAniversario();
            case "filhos" -> usuarios.get(login).getFilhos();
            case "idiomas" -> usuarios.get(login).getIdiomas();
            case "cidadeNatal" -> usuarios.get(login).getCidadeNatal();
            case "estilo" -> usuarios.get(login).getEstilo();
            case "fumo" -> usuarios.get(login).getFumo();
            case "bebo" -> usuarios.get(login).getBebo();
            case "moro" -> usuarios.get(login).getMoro();
            default -> null;
        };

        if(atributoUsuario == null){
            throw new NullAttributeException("Atributo n�o preenchido.");
        }

        return atributoUsuario;
    }

    /**
     * Cria um novo usu�rio no sistema.
     * @param login Login do usu�rio.
     * @param senha Senha do usu�rio.
     * @param nome Nome do usu�rio.
     * @throws Exception Se o login ou senha forem nulos, ou se o usu�rio j� existir.
     */
    public void criarUsuario(String login, String senha, String nome) throws Exception{
        if(login == null){
            throw new NullCredentialException("Login inv�lido.");
        }
        if(senha == null){
            throw new NullCredentialException("Senha inv�lida.");
        }
        if(!usuarios.containsKey(login)){
            usuarios.put(login, new Usuario(login, senha, nome));
        }
        else{
            throw new ConflictingAuthenticationException("Conta com esse nome j� existe.");
        }
    }

    /**
     * Abre sess�o de um usu�rio.
     * @param login Login do usu�rio.
     * @param senha Senha do usu�rio.
     * @return O login do usu�rio autenticado.
     * @throws InvalidCredentialException Se as credenciais forem inv�lidas.
     */
    public String abrirSessao(String login, String senha) throws InvalidCredentialException{
        if(!usuarios.containsKey(login) || !usuarios.get(login).getSenha().equals(senha)){
            throw new InvalidCredentialException("Login ou senha inv�lidos.");
        }
        return login;
    }

    /**
     * Edita um atributo do perfil de um usu�rio.
     * @param id Identifica��o do usu�rio.
     * @param atributo Nome do atributo a ser modificado.
     * @param valor Novo valor do atributo.
     * @throws InvalidUserException Se o usu�rio n�o estiver cadastrado.
     */
    public void editarPerfil(String id, String atributo, String valor) throws InvalidUserException{
        if(!usuarios.containsKey(id)){
            throw new InvalidUserException("Usu�rio n�o cadastrado.");
        }
        switch (atributo) {
            case "nome" -> usuarios.get(id).setNome(valor);
            case "descricao" -> usuarios.get(id).setDescricao(valor);
            case "estadoCivil" -> usuarios.get(id).setEstadoCivil(valor);
            case "aniversario" -> usuarios.get(id).setAniversario(valor);
            case "filhos" -> usuarios.get(id).setFilhos(valor);
            case "idiomas" -> usuarios.get(id).setIdiomas(valor);
            case "cidadeNatal" -> usuarios.get(id).setCidadeNatal(valor);
            case "estilo" -> usuarios.get(id).setEstilo(valor);
            case "fumo" -> usuarios.get(id).setFumo(valor);
            case "bebo" -> usuarios.get(id).setBebo(valor);
            case "moro" -> usuarios.get(id).setMoro(valor);
        }
    }

    /**
     * Adiciona um usu�rio como amigo, enviando um pedido de amizade ou aceitando um convite pendente.
     * @param id Login do usu�rio que deseja adicionar um amigo.
     * @param amigo Login do usu�rio a ser adicionado como amigo.
     * @throws InvalidUserException Se algum dos usu�rios n�o estiver cadastrado.
     * @throws FobiddenOperationException Se o usu�rio tentar adicionar a si mesmo como amigo.
     * @throws NotNeededOperationException Se o usu�rio j� for amigo ou houver um pedido pendente.
     */

    public void adicionarAmigo(String id, String amigo) throws Exception{
        if(!usuarios.containsKey(id) || !usuarios.containsKey(amigo)){
            throw new InvalidUserException("Usu�rio n�o cadastrado.");
        }
        if(id.equals(amigo)){
            throw new FobiddenOperationException("Usu�rio n�o pode adicionar a si mesmo como amigo.");
        }
        if(usuarios.get(id).getPedidos().contains(usuarios.get(amigo))){
            throw new NotNeededOperationException("Usu�rio j� est� adicionado como amigo, esperando aceita��o do convite.");
        }
        if(usuarios.get(id).getAmigos().contains(usuarios.get(amigo))){
            throw new NotNeededOperationException("Usu�rio j� est� adicionado como amigo.");
        }
        if(usuarios.get(amigo).getPedidos().contains(usuarios.get(id))){
            usuarios.get(id).getAmigos().add(usuarios.get(amigo));
            usuarios.get(amigo).getAmigos().add(usuarios.get(id));

            usuarios.get(amigo).getPedidos().remove(usuarios.get(id));
        }
        else{
            usuarios.get(id).getPedidos().add(usuarios.get(amigo));
        }
    }

    /**
     * Verifica se dois usu�rios s�o amigos.
     * @param login Login do primeiro usu�rio.
     * @param amigo Login do poss�vel amigo.
     * @return true se forem amigos, false caso contr�rio.
     * @throws InvalidUserException Se o usu�rio n�o estiver cadastrado.
     */
    public boolean ehAmigo(String login, String amigo) throws InvalidUserException{
        if(!usuarios.containsKey(login)){
            throw new InvalidUserException("Usu�rio n�o cadastrado.");
        }
        return usuarios.get(login).getAmigos().contains(usuarios.get(amigo));
    }

    /**
     * Retorna uma lista formatada de amigos de um usu�rio.
     * @param login Login do usu�rio.
     * @return String formatada com os amigos do usu�rio.
     */
    public String getAmigos(String login){
        List<String> amigos = usuarios.get(login).getAmigos().stream()
                .map(Usuario::getLogin)
                .collect(Collectors.toList());
        return "{" + String.join(",", amigos) + "}";
    }

    /**
     * Remove todos os usu�rios e exclui o arquivo de armazenamento.
     */
    public void zerarSistema(){
        usuarios.clear();
        Path path = Paths.get("Usuarios.ser");
        try {
            Files.deleteIfExists(path);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Salva os usu�rios no arquivo "Usuarios.ser" e encerra o sistema.
     * Ap�s salvar, a lista de usu�rios � apagada da mem�ria.
     */
    public void encerrarSistema(){
        try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("Usuarios.ser"))){
            objectOutputStream.writeObject(usuarios);

            // Fecha o stream explicitamente, embora o try-with-resources j� fa�a isso automaticamente.
            objectOutputStream.close();

            // Limpa a lista de usu�rios da mem�ria ap�s salvar.
            usuarios.clear();
        }
        catch (IOException e){
            e.printStackTrace(); // Exibe erro caso ocorra problema ao salvar os usu�rios.
        }
    }

    /**
     * Envia um recado para outro usu�rio.
     * @param id Identificador do remetente.
     * @param destinatario Identificador do destinat�rio.
     * @param recado Mensagem a ser enviada.
     * @throws InvalidUserException Se o remetente ou destinat�rio n�o existirem.
     * @throws FobiddenOperationException Se o usu�rio tentar enviar um recado para si mesmo.
     */
    public void enviarRecado(String id, String destinatario, String recado) throws Exception{
        if(!usuarios.containsKey(id) || !usuarios.containsKey(destinatario)){
            throw new InvalidUserException("Usu�rio n�o cadastrado.");
        }
        if(id.equals(destinatario)){
            throw new FobiddenOperationException("Usu�rio n�o pode enviar recado para si mesmo.");
        }

        // Adiciona o recado � fila de recados do destinat�rio.
        usuarios.get(destinatario).getRecados().add(recado);
    }

    /**
     * L� e remove o recado mais antigo da fila de recados de um usu�rio.
     * @param id Identificador do usu�rio.
     * @return O recado mais antigo.
     * @throws InvalidUserException Se o usu�rio n�o estiver cadastrado.
     * @throws EmptyListException Se o usu�rio n�o tiver recados.
     */
    public String lerRecado(String id) throws Exception{
        if(!usuarios.containsKey(id)){
            throw new InvalidUserException("Usu�rio n�o cadastrado.");
        }
        if(usuarios.get(id).getRecados().isEmpty()){
            throw new EmptyListException("N�o h� recados.");
        }

        // Remove e retorna o recado mais antigo.
        return usuarios.get(id).getRecados().remove();
    }

    /**
     * Retorna o mapa contendo todos os usu�rios do sistema.
     * @return HashMap contendo os usu�rios cadastrados.
     */
    public HashMap<String, Usuario> getUsuarios() {
        return usuarios;
    }
}