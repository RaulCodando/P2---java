package br.ufal.ic.p2.jackut.sistema;

import br.ufal.ic.p2.jackut.exceptions.*;
import br.ufal.ic.p2.jackut.usuario.*;

import java.util.HashMap;
import java.util.LinkedHashSet;

/**
 * Classe responsável por gerenciar operações relacionadas aos usuários no sistema Jackut.
 * Estende a classe Gerenciador e lida com funcionalidades como:
 * - Criação e autenticação de usuários
 * - Edição de perfil
 * - Envio e leitura de recados
 * - Consulta de atributos do perfil
 * - Remoção de usuários e limpeza de dados associados (recados, mensagens, comunidades)
 * A classe também trata diversas exceções personalizadas para garantir a integridade
 * e a consistência das operações de usuário dentro do sistema.
 */
public class GerenciadorDeUsuario extends Gerenciador {
    public GerenciadorDeUsuario(HashMap<String, Usuario> usuarios, HashMap<String, Comunidade> comunidades) {
        super(usuarios, comunidades);
    }

    /**
     * Obtém um atributo específico de um usuário.
     * @param login Login do usuário.
     * @param atributo Nome do atributo desejado.
     * @return O valor do atributo solicitado.
     * @throws InvalidUserException Se o usuário não estiver cadastrado.
     * @throws NullAttributeException Se o atributo não estiver preenchido.
     */
    public String getAtributoUsuario(String login, String atributo) throws Exception {
        if (!this.getUsuarios().containsKey(login)) {
            throw new InvalidUserException();
        }

        String atributoUsuario = switch (atributo) {
            case "nome" -> this.getUsuarios().get(login).getPerfil().getNome();
            case "login" -> this.getUsuarios().get(login).getLogin();
            case "descricao" -> this.getUsuarios().get(login).getPerfil().getDescricao();
            case "estadoCivil" -> this.getUsuarios().get(login).getPerfil().getEstadoCivil();
            case "aniversario" -> this.getUsuarios().get(login).getPerfil().getAniversario();
            case "filhos" -> this.getUsuarios().get(login).getPerfil().getFilhos();
            case "idiomas" -> this.getUsuarios().get(login).getPerfil().getIdiomas();
            case "cidadeNatal" -> this.getUsuarios().get(login).getPerfil().getCidadeNatal();
            case "estilo" -> this.getUsuarios().get(login).getPerfil().getEstilo();
            case "fumo" -> this.getUsuarios().get(login).getPerfil().getFumo();
            case "bebo" -> this.getUsuarios().get(login).getPerfil().getBebo();
            case "moro" -> this.getUsuarios().get(login).getPerfil().getMoro();
            default -> null;
        };

        if (atributoUsuario == null) {
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
    public void criarUsuario(String login, String senha, String nome) throws Exception {
        if (login == null) {
            throw new NullCredentialException("Login inválido.");
        }
        if (senha == null) {
            throw new NullCredentialException("Senha inválida.");
        }
        if (!this.getUsuarios().containsKey(login)) {
            this.getUsuarios().put(login, new Usuario(login, senha, nome));
        } else {
            throw new ConflictingAuthenticationException();
        }
    }

    /**
     * Abre sessão de um usuário.
     * @param login Login do usuário.
     * @param senha Senha do usuário.
     * @return O login do usuário autenticado.
     * @throws InvalidCredentialException Se as credenciais forem inválidas.
     */
    public String abrirSessao(String login, String senha) throws InvalidCredentialException {
        if (!this.getUsuarios().containsKey(login) || !this.getUsuarios().get(login).getSenha().equals(senha)) {
            throw new InvalidCredentialException();
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
    public void editarPerfil(String id, String atributo, String valor) throws InvalidUserException {
        if (!this.getUsuarios().containsKey(id)) {
            throw new InvalidUserException();
        }
        switch (atributo) {
            case "nome" -> this.getUsuarios().get(id).getPerfil().setNome(valor);
            case "descricao" -> this.getUsuarios().get(id).getPerfil().setDescricao(valor);
            case "estadoCivil" -> this.getUsuarios().get(id).getPerfil().setEstadoCivil(valor);
            case "aniversario" -> this.getUsuarios().get(id).getPerfil().setAniversario(valor);
            case "filhos" -> this.getUsuarios().get(id).getPerfil().setFilhos(valor);
            case "idiomas" -> this.getUsuarios().get(id).getPerfil().setIdiomas(valor);
            case "cidadeNatal" -> this.getUsuarios().get(id).getPerfil().setCidadeNatal(valor);
            case "estilo" -> this.getUsuarios().get(id).getPerfil().setEstilo(valor);
            case "fumo" -> this.getUsuarios().get(id).getPerfil().setFumo(valor);
            case "bebo" -> this.getUsuarios().get(id).getPerfil().setBebo(valor);
            case "moro" -> this.getUsuarios().get(id).getPerfil().setMoro(valor);
        }
    }

    /**
     * Envia um recado para outro usuário.
     * @param id Identificador do remetente.
     * @param destinatario Identificador do destinatário.
     * @param recado Mensagem a ser enviada.
     * @throws InvalidUserException Se o remetente ou destinatário não existirem.
     * @throws SelfMessagingNotAllowedException Se o usuário tentar enviar um recado para si mesmo.
     * @throws NemesisException Se o destinatário for inimigo do remetente.
     */
    public void enviarRecado(String id, String destinatario, String recado) throws Exception {
        if (this.getUsuarios().containsKey(id)) {
            if (!this.getUsuarios().containsKey(destinatario)) {
                throw new InvalidUserException();
            }
            if (this.getUsuarios().get(destinatario).getRelacionamento().getInimigos().contains(this.getUsuarios().get(id))) {
                throw new NemesisException(this.getUsuarios().get(destinatario).getPerfil().getNome());
            }
            if (id.equals(destinatario)) {
                throw new SelfMessagingNotAllowedException();
            }
        }

        // Adiciona o recado à fila de recados do destinatário.
        this.getUsuarios().get(destinatario).getRecados().add(new Texto(id, recado));
    }

    /**
     * Lê e remove o recado mais antigo da fila de recados de um usuário.
     * @param id Identificador do usuário.
     * @return O recado mais antigo.
     * @throws InvalidUserException Se o usuário não estiver cadastrado.
     * @throws EmptyListException Se o usuário não tiver recados.
     */
    public String lerRecado(String id) throws Exception {
        if (!this.getUsuarios().containsKey(id)) {
            throw new InvalidUserException();
        }
        if (this.getUsuarios().get(id).getRecados().isEmpty()) {
            throw new EmptyListException("Não há recados.");
        }

        // Remove e retorna o recado mais antigo.
        return this.getUsuarios().get(id).getRecados().remove().getConteudo();
    }

    /**
     * Remove um usuário do sistema, eliminando seus vínculos com comunidades,
     * recados e mensagens enviadas a outros usuários.
     * @param id Identificador do usuário a ser removido.
     * @throws InvalidUserException Se o usuário não estiver cadastrado.
     */
    public void removerUsuario(String id) throws InvalidUserException {
        if (!this.getUsuarios().containsKey(id)) {
            throw new InvalidUserException();
        }

        HashMap<String, Comunidade> cmddTemp = new HashMap(this.getComunidades());
        this.getUsuarios().get(id).getComunidades().clear();

        cmddTemp.forEach((nome, comunidade) -> {
            for (Usuario membro : comunidade.getMembros()) {
                membro.getComunidades().remove(this.getComunidades().get(nome));
            }

            if (comunidade.getDonoLogin().equals(id)) {
                this.getComunidades().remove(nome);
            }
        });

        this.getUsuarios().forEach((login, usuario) -> {
            LinkedHashSet<Texto> recados = new LinkedHashSet<>(usuario.getRecados());
            LinkedHashSet<Texto> mensagens = new LinkedHashSet<>(usuario.getMensagens());

            for (Texto recado : recados) {
                if (recado.getRemetente().equals(id)) {
                    this.getUsuarios().get(login).getRecados().remove(recado);
                }
            }

            for (Texto mensagem : mensagens) {
                if (mensagem.getRemetente().equals(id)) {
                    this.getUsuarios().get(login).getMensagens().remove(mensagem);
                }
            }
        });

        this.getUsuarios().remove(id);
    }
}