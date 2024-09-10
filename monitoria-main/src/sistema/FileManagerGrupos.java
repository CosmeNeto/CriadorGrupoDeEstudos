package sistema;

import model.Grupo;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManagerGrupos {

    private static final String GRUPOS_DIR = "monitoria-main/resources/database/grupos/";
    private static final String GRUPOS_FILE = GRUPOS_DIR + "grupos.txt";

    static {
        try {
            new File(GRUPOS_DIR).mkdirs();
            new File(GRUPOS_FILE).createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean grupoJaExiste(String nomeGrupo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(GRUPOS_FILE))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                if (linha.contains("Nome do Grupo: " + nomeGrupo)) {
                    return true;
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao verificar o nome do grupo: " + e.getMessage());
        }
        return false;
    }

    public static boolean codigoJaExiste(String codigo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(GRUPOS_FILE))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                if (linha.contains("Código do Grupo: " + codigo)) {
                    return true;
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao verificar o código do grupo: " + e.getMessage());
        }
        return false;
    }

    public static void salvarGrupoEmArquivo(Grupo grupo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(GRUPOS_FILE, true))) {
            writer.write("Nome do Grupo: " + grupo.getNomeGrupo() + "\n");
            writer.write("Matéria: " + grupo.getMateria() + "\n");
            writer.write("Local: " + grupo.getLocal() + "\n");
            writer.write("Horário: " + grupo.getHorarios().toString() + "\n");
            writer.write("Código do Grupo: " + grupo.getCodigoGrupo() + "\n");
            writer.write("Participantes: " + String.join(", ", grupo.getParticipantes()) + "\n");
            writer.write("------------------------------\n");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar o grupo no arquivo: " + e.getMessage());
        }
    }

    public static boolean adicionarParticipante(String codigoGrupo, String usuarioAtual) {
        List<String> linhas = new ArrayList<>();
        boolean grupoEncontrado = false;
        boolean usuarioJaNoGrupo = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(GRUPOS_FILE))) {
            String linha;
            boolean dentroDoGrupo = false;

            while ((linha = reader.readLine()) != null) {
                if (linha.startsWith("Código do Grupo: " + codigoGrupo)) {
                    grupoEncontrado = true;
                    dentroDoGrupo = true;
                    linhas.add(linha);
                } else if (linha.startsWith("Código do Grupo: ")) {
                    dentroDoGrupo = false;
                    linhas.add(linha);
                } else if (dentroDoGrupo && linha.startsWith("Participantes:")) {
                    String participantes = linha.split("Participantes: ")[1].trim();
                    if (participantes.contains(usuarioAtual)) {
                        usuarioJaNoGrupo = true;
                    } else {
                        participantes += ", " + usuarioAtual;
                        linhas.add("Participantes: " + participantes);
                    }
                } else {
                    linhas.add(linha);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao ler o arquivo de grupos: " + e.getMessage());
        }

        if (grupoEncontrado && !usuarioJaNoGrupo) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(GRUPOS_FILE))) {
                for (String linha : linhas) {
                    writer.write(linha + "\n");
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Erro ao atualizar o arquivo de grupos: " + e.getMessage());
            }
            return true;
        }
        return false;
    }

    public static List<String> carregarGruposParaUsuario(String usuario) {
        List<String> grupos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(GRUPOS_FILE))) {
            String linha;
            boolean adicionarGrupo = false;
            String grupoAtual = null;
            while ((linha = reader.readLine()) != null) {
                if (linha.startsWith("Nome do Grupo:")) {
                    grupoAtual = linha.substring(14);
                    adicionarGrupo = false;
                } else if (linha.startsWith("Participantes:") && linha.contains(usuario)) {
                    adicionarGrupo = true;
                } else if (linha.equals("------------------------------") && adicionarGrupo && grupoAtual != null) {
                    grupos.add(grupoAtual);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar os grupos: " + e.getMessage());
        }
        return grupos;
    }

    public static String obterInformacoesDoGrupo(String nomeGrupo) {
        StringBuilder informacoes = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(GRUPOS_FILE))) {
            String linha;
            boolean grupoEncontrado = false;
            while ((linha = reader.readLine()) != null) {
                if (linha.startsWith("Nome do Grupo:")) {
                    if (grupoEncontrado) {
                        break;
                    }
                    if (linha.substring(14).equals(nomeGrupo)) {
                        grupoEncontrado = true;
                        informacoes.append(linha).append("\n");
                    }
                } else if (grupoEncontrado) {
                    informacoes.append(linha).append("\n");
                    if (linha.equals("------------------------------")) {
                        grupoEncontrado = false;
                    }
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar as informações do grupo: " + e.getMessage());
        }
        return informacoes.toString();
    }
}