package br.ufal.ic.p2.jackut.sistema;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import br.ufal.ic.p2.jackut.usuario.*;

/**
 * Classe que representa o sistema da aplicação, responsável pelo gerenciamento de usuários e comunidades.
 */
public class Sistema {
    // Mapa que armazena os usuários do sistema, indexados pelo login.
    private HashMap<String, Usuario> usuarios;

    // Mapa que armazena as comunidades do sistema, indexadas pelo nome.
    private HashMap<String, Comunidade> comunidades;

    /**
     * Construtor da classe Sistema.
     * Se os arquivos "Usuarios.ser" e "Comunidades.ser" existirem,
     * carrega os dados salvos; caso contrário, inicializa mapas vazios.
     */
    public Sistema(){
        File file1 = new File("Usuarios.ser");
        File file2 = new File("Comunidades.ser");

        usuarios = new HashMap<>();
        comunidades = new HashMap<String, Comunidade>();

        // Tenta carregar os dados salvos dos arquivos, se existirem.
        if(file1.exists() && file2.exists()){
            try(ObjectInputStream objectInputStream1 = new ObjectInputStream(new FileInputStream("Usuarios.ser"));
                ObjectInputStream objectInputStream2 = new ObjectInputStream(new FileInputStream("Comunidades.ser"))){

                // Lê os objetos serializados dos arquivos e os atribui aos mapas.
                usuarios = (HashMap<String, Usuario>) objectInputStream1.readObject();
                comunidades = (HashMap<String, Comunidade>) objectInputStream2.readObject();
            }
            catch (Exception e){
                e.printStackTrace(); // Exibe erro caso ocorra problema ao ler os arquivos.
            }
        }
    }

    /**
     * Zera completamente o sistema:
     * remove todos os usuários e comunidades da memória e do disco.
     */
    public void zerarSistema(){
        usuarios.clear();
        comunidades.clear();
        Path path1 = Paths.get("Usuarios.ser");
        Path path2 = Paths.get("Comunidades.ser");
        try {
            // Remove os arquivos de persistência, se existirem.
            Files.deleteIfExists(path1);
            Files.deleteIfExists(path2);
        }
        catch (IOException e){
            e.printStackTrace(); // Exibe erro caso ocorra problema ao deletar os arquivos.
        }
    }

    /**
     * Salva os dados dos usuários e comunidades nos arquivos correspondentes e
     * encerra o sistema limpando os dados da memória.
     */
    public void encerrarSistema(){
        try(ObjectOutputStream objectOutputStream1 = new ObjectOutputStream(new FileOutputStream("Usuarios.ser"));
            ObjectOutputStream objectOutputStream2 = new ObjectOutputStream(new FileOutputStream("Comunidades.ser"))){

            // Serializa e salva os mapas no disco.
            objectOutputStream1.writeObject(usuarios);
            objectOutputStream2.writeObject(comunidades);

            // Os streams serão fechados automaticamente pelo try-with-resources.

            // Limpa os dados da memória.
            usuarios.clear();
            comunidades.clear();
        }
        catch (IOException e){
            e.printStackTrace(); // Exibe erro caso ocorra problema ao salvar os dados.
        }
    }

    /**
     * Retorna o mapa de usuários do sistema.
     * @return Mapa de usuários indexado pelo login.
     */
    public HashMap<String, Usuario> getUsuarios() {
        return usuarios;
    }

    /**
     * Retorna o mapa de comunidades do sistema.
     * @return Mapa de comunidades indexado pelo nome.
     */
    public HashMap<String, Comunidade> getComunidades() {
        return comunidades;
    }
}