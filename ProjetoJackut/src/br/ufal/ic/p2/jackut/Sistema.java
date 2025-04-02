package br.ufal.ic.p2.jackut;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Classe que representa o sistema da aplicação, responsável pelo gerenciamento de usuários e suas interações.
 */
public class Sistema {
    private HashMap<String, Usuario> usuarios; // Mapa que armazena os usuários do sistema, indexados pelo login.

    /**
     * Construtor da classe Sistema.
     * Se um arquivo de usuários existir,
     * carrega os dados salvos; caso contrário,
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
     * Obtém um atributo específico de um usuário.
     * @param login Login do usuário.
     * @param atributo Nome do atributo desejado.
     * @return O valor do atributo solicitado.
     * @throws InvalidUserException Se o usuário não estiver cadastrado.
     * @throws NullAttributeException Se o atributo não estiver preenchido.
     */
    public String getAtributoUsuario(String login, String atributo) throws InvalidUserException, NullAttributeException {
        if(!usuarios.containsKey(login)){
            throw new InvalidUserException("Usuário não cadastrado.");
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
            throw new NullAttributeException("Atributo não preenchido.");
        }

        return atributoUsuario;
    }

    /**
     * Cria um novo usuário no sistema.
     * @param login Login do usuário.
     * @param senha Senha do usuário.
     * @param nome Nome do usuário.
     * @throws Exception Se o login ou senha forem nulos, ou se o usuário já existir.
     */
    public void criarUsuario(String login, String senha, String nome) throws Exception{
        if(login == null){
            throw new NullCredentialException("Login inválido.");
        }
        if(senha == null){
            throw new NullCredentialException("Senha inválida.");
        }
        if(!usuarios.containsKey(login)){
            usuarios.put(login, new Usuario(login, senha, nome));
        }
        else{
            throw new ConflictingAuthenticationException("Conta com esse nome já existe.");
        }
    }

    /**
     * Abre sessão de um usuário.
     * @param login Login do usuário.
     * @param senha Senha do usuário.
     * @return O login do usuário autenticado.
     * @throws InvalidCredentialException Se as credenciais forem inválidas.
     */
    public String abrirSessao(String login, String senha) throws InvalidCredentialException{
        if(!usuarios.containsKey(login) || !usuarios.get(login).getSenha().equals(senha)){
            throw new InvalidCredentialException("Login ou senha inválidos.");
        }
        return login;
    }

    /**
     * Edita um atributo do perfil de um usuário.
     * @param id Identificação do usuário.
     * @param atributo Nome do atributo a ser modificado.
     * @param valor Novo valor do atributo.
     * @throws InvalidUserException Se o usuário não estiver cadastrado.
     */
    public void editarPerfil(String id, String atributo, String valor) throws InvalidUserException{
        if(!usuarios.containsKey(id)){
            throw new InvalidUserException("Usuário não cadastrado.");
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
     * Adiciona um usuário como amigo, enviando um pedido de amizade ou aceitando um convite pendente.
     * @param id Login do usuário que deseja adicionar um amigo.
     * @param amigo Login do usuário a ser adicionado como amigo.
     * @throws InvalidUserException Se algum dos usuários não estiver cadastrado.
     * @throws FobiddenOperationException Se o usuário tentar adicionar a si mesmo como amigo.
     * @throws NotNeededOperationException Se o usuário já for amigo ou houver um pedido pendente.
     */

    public void adicionarAmigo(String id, String amigo) throws Exception{
        if(!usuarios.containsKey(id) || !usuarios.containsKey(amigo)){
            throw new InvalidUserException("Usuário não cadastrado.");
        }
        if(id.equals(amigo)){
            throw new FobiddenOperationException("Usuário não pode adicionar a si mesmo como amigo.");
        }
        if(usuarios.get(id).getPedidos().contains(usuarios.get(amigo))){
            throw new NotNeededOperationException("Usuário já está adicionado como amigo, esperando aceitação do convite.");
        }
        if(usuarios.get(id).getAmigos().contains(usuarios.get(amigo))){
            throw new NotNeededOperationException("Usuário já está adicionado como amigo.");
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
     * Verifica se dois usuários são amigos.
     * @param login Login do primeiro usuário.
     * @param amigo Login do possível amigo.
     * @return true se forem amigos, false caso contrário.
     * @throws InvalidUserException Se o usuário não estiver cadastrado.
     */
    public boolean ehAmigo(String login, String amigo) throws InvalidUserException{
        if(!usuarios.containsKey(login)){
            throw new InvalidUserException("Usuário não cadastrado.");
        }
        return usuarios.get(login).getAmigos().contains(usuarios.get(amigo));
    }

    /**
     * Retorna uma lista formatada de amigos de um usuário.
     * @param login Login do usuário.
     * @return String formatada com os amigos do usuário.
     */
    public String getAmigos(String login){
        List<String> amigos = usuarios.get(login).getAmigos().stream()
                .map(Usuario::getLogin)
                .collect(Collectors.toList());
        return "{" + String.join(",", amigos) + "}";
    }

    /**
     * Remove todos os usuários e exclui o arquivo de armazenamento.
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
     * Salva os usuários no arquivo "Usuarios.ser" e encerra o sistema.
     * Após salvar, a lista de usuários é apagada da memória.
     */
    public void encerrarSistema(){
        try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("Usuarios.ser"))){
            objectOutputStream.writeObject(usuarios);

            // Fecha o stream explicitamente, embora o try-with-resources já faça isso automaticamente.
            objectOutputStream.close();

            // Limpa a lista de usuários da memória após salvar.
            usuarios.clear();
        }
        catch (IOException e){
            e.printStackTrace(); // Exibe erro caso ocorra problema ao salvar os usuários.
        }
    }

    /**
     * Envia um recado para outro usuário.
     * @param id Identificador do remetente.
     * @param destinatario Identificador do destinatário.
     * @param recado Mensagem a ser enviada.
     * @throws InvalidUserException Se o remetente ou destinatário não existirem.
     * @throws FobiddenOperationException Se o usuário tentar enviar um recado para si mesmo.
     */
    public void enviarRecado(String id, String destinatario, String recado) throws Exception{
        if(!usuarios.containsKey(id) || !usuarios.containsKey(destinatario)){
            throw new InvalidUserException("Usuário não cadastrado.");
        }
        if(id.equals(destinatario)){
            throw new FobiddenOperationException("Usuário não pode enviar recado para si mesmo.");
        }

        // Adiciona o recado à fila de recados do destinatário.
        usuarios.get(destinatario).getRecados().add(recado);
    }

    /**
     * Lê e remove o recado mais antigo da fila de recados de um usuário.
     * @param id Identificador do usuário.
     * @return O recado mais antigo.
     * @throws InvalidUserException Se o usuário não estiver cadastrado.
     * @throws EmptyListException Se o usuário não tiver recados.
     */
    public String lerRecado(String id) throws Exception{
        if(!usuarios.containsKey(id)){
            throw new InvalidUserException("Usuário não cadastrado.");
        }
        if(usuarios.get(id).getRecados().isEmpty()){
            throw new EmptyListException("Não há recados.");
        }

        // Remove e retorna o recado mais antigo.
        return usuarios.get(id).getRecados().remove();
    }

    /**
     * Retorna o mapa contendo todos os usuários do sistema.
     * @return HashMap contendo os usuários cadastrados.
     */
    public HashMap<String, Usuario> getUsuarios() {
        return usuarios;
    }
}