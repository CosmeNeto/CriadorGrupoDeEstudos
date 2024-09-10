package sistema;

import model.Aluno;
import model.Professor;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileManagerUsuarios {

    private static final String USUARIOS_DIR = "monitoria-main/resources/database/usuarios/";
    private static final String ALUNO_FILE = USUARIOS_DIR + "alunos.txt";
    private static final String PROFESSOR_FILE = USUARIOS_DIR + "professores.txt";

    static {
        try {
            new File(USUARIOS_DIR).mkdirs();
            new File(ALUNO_FILE).createNewFile();
            new File(PROFESSOR_FILE).createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveAluno(Aluno aluno) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ALUNO_FILE, true))) {
            writer.write(aluno.toString());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveProfessor(Professor professor) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PROFESSOR_FILE, true))) {
            writer.write(professor.toString());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Aluno> loadAlunos() {
        List<Aluno> alunos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ALUNO_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length != 8) {
                    System.err.println("Linha inválida em " + ALUNO_FILE + ": " + line);
                    continue;
                }
                String nome = parts[0];
                String email = parts[1];
                String senha = parts[2];
                String matricula = parts[3];
                String curso = parts[4];
                String periodo = parts[5];
                String periodoTipo = parts[6];
                double cre = Double.parseDouble(parts[7]);
                Aluno aluno = new Aluno(nome, email, senha, matricula, curso, periodo, periodoTipo, cre);
                alunos.add(aluno);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return alunos;
    }

    public static List<Professor> loadProfessores() {
        List<Professor> professores = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(PROFESSOR_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length != 4) {
                    System.err.println("Linha inválida em " + PROFESSOR_FILE + ": " + line);
                    continue;
                }
                String nome = parts[0];
                String email = parts[1];
                String senha = parts[2];
                List<String> materias = Arrays.asList(parts[3].split(","));
                Professor professor = new Professor(nome, email, senha, materias);
                professores.add(professor);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return professores;
    }

    public static boolean emailExists(String email) {
        List<Aluno> alunos = loadAlunos();
        for (Aluno aluno : alunos) {
            if (aluno.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }

        List<Professor> professores = loadProfessores();
        for (Professor professor : professores) {
            if (professor.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }
        return false;
    }

    public static boolean matriculaExists(String matricula) {
        List<Aluno> alunos = loadAlunos();
        for (Aluno aluno : alunos) {
            if (aluno.getMatricula().equals(matricula)) {
                return true;
            }
        }
        return false;
    }

    public static String validarLogin(String email, String senha) {
        String nomeUsuario = verificarUsuario(ALUNO_FILE, email, senha);
        if (nomeUsuario != null) {
            return nomeUsuario;
        }
        return verificarUsuario(PROFESSOR_FILE, email, senha);
    }

    private static String verificarUsuario(String arquivo, String email, String senha) {
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length >= 4) {
                    String nomeArquivo = partes[0];
                    String emailArquivo = partes[1];
                    String senhaArquivo = partes[2];
                    if (email.equals(emailArquivo) && senha.equals(senhaArquivo)) {
                        return nomeArquivo;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}



