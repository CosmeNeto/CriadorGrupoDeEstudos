package sistema;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileManagerChat {

    private static final String CHATS_DIR = "monitoria-main/resources/database/chats/";

    public static void verificarOuCriarDiretorio() {
        File diretorio = new File(CHATS_DIR);
        if (!diretorio.exists()) {
            if (diretorio.mkdirs()) {
                System.out.println("Diretório criado: " + CHATS_DIR);
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao criar diretório: " + CHATS_DIR);
            }
        }
    }

    public static String carregarMensagens(String grupoNome) {
        String caminhoArquivo = CHATS_DIR + grupoNome + "_chat.txt";
        Path caminho = Paths.get(caminhoArquivo);
        System.out.println("Tentando carregar o arquivo: " + caminhoArquivo);
        try {
            if (Files.exists(caminho)) {
                return new String(Files.readAllBytes(caminho));
            } else {
                System.out.println("Arquivo não encontrado, criando novo: " + caminhoArquivo);
                File arquivo = new File(caminhoArquivo);
                if (!arquivo.createNewFile()) {
                    JOptionPane.showMessageDialog(null, "Erro ao criar o arquivo de chat.");
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar as mensagens: " + e.getMessage());
        }
        return "";
    }

    public static void salvarMensagem(String grupoNome, String mensagem) {
        String caminhoArquivo = CHATS_DIR + grupoNome + "_chat.txt";
        File arquivo = new File(caminhoArquivo);
        System.out.println("Salvando mensagem no arquivo: " + caminhoArquivo);
        try {
            if (!arquivo.exists()) {
                if (!arquivo.createNewFile()) {
                    JOptionPane.showMessageDialog(null, "Erro ao criar o arquivo de chat.");
                }
            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo, true))) {
                writer.write(mensagem);
                writer.newLine();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar a mensagem: " + e.getMessage());
        }
    }
}