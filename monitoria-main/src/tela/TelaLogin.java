package tela;

import model.Aluno;
import model.Grupo;
import sistema.FileManagerUsuarios;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import static sistema.FileManagerChat.salvarMensagem;
import static sistema.FileManagerGrupos.salvarGrupoEmArquivo;
import static sistema.FileManagerUsuarios.saveAluno;
import static tela.TelaChat.formatarMensagemComHorario;

public class TelaLogin extends JFrame {
    private JTextField emailField;
    private JPasswordField senhaField;
    private JButton entrarButton;
    private JButton cadastrarButton;

    public TelaLogin() {
        setTitle("Tela de Login");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(50, 50, 100, 30);
        add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(150, 50, 200, 30);
        add(emailField);

        JLabel senhaLabel = new JLabel("Senha:");
        senhaLabel.setBounds(50, 100, 100, 30);
        add(senhaLabel);

        senhaField = new JPasswordField();
        senhaField.setBounds(150, 100, 200, 30);
        add(senhaField);

        entrarButton = new JButton("Entrar");
        entrarButton.setBounds(150, 150, 100, 30);
        add(entrarButton);

        cadastrarButton = new JButton("Cadastrar");
        cadastrarButton.setBounds(250, 150, 100, 30);
        add(cadastrarButton);

        entrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String senha = new String(senhaField.getPassword());
                String nomeUsuario = FileManagerUsuarios.validarLogin(email, senha);
                if (nomeUsuario != null) {
                    new TelaPrincipal(nomeUsuario).setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Email ou senha inválidos.");
                }
            }
        });

        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TelaCadastro().setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        if (args.length == 1 && args[0].equals("popular")){
            try{
                Aluno aluno1 = new Aluno("Ana Silva", "ana.silva@academico.ifpb.edu.br", "senha123", "000123456789", "ADS", "4", "Semestre", 85.50);
                Aluno aluno2 = new Aluno("Carlos Souza", "carlos.souza@academico.ifpb.edu.br", "senha456", "000987654321", "Engenharia", "2", "Ano", 90.00);
                Aluno aluno3 = new Aluno("Maria Oliveira", "maria.oliveira@academico.ifpb.edu.br", "senha789", "000112233445", "Design", "6", "Semestre", 72.40);
                Aluno aluno4 = new Aluno("Pedro Santos", "pedro.santos@academico.ifpb.edu.br", "senha321", "000223344556", "Física", "1", "Ano", 88.75);
                Aluno aluno5 = new Aluno("Julia Costa", "julia.costa@academico.ifpb.edu.br", "senha654", "000334455667", "Matemática", "3", "Semestre", 79.20);
                Aluno aluno6 = new Aluno("Lucas Pereira", "lucas.pereira@academico.ifpb.edu.br", "senha987", "000445566778", "Química", "5", "Ano", 82.90);
                Aluno aluno7 = new Aluno("Roberta Lima", "roberta.lima@academico.ifpb.edu.br", "senha000", "000556677889", "Biologia", "4", "Semestre", 91.10);

                saveAluno(aluno1);
                saveAluno(aluno2);
                saveAluno(aluno3);
                saveAluno(aluno4);
                saveAluno(aluno5);
                saveAluno(aluno6);
                saveAluno(aluno7);

                Map<String, String> horariosGrupo1 = new HashMap<>();
                horariosGrupo1.put("Segunda-feira", "10:00");
                Grupo grupo1 = new Grupo("Estudo de Cálculo", "Matemática", "Sala 101", "C123AB", horariosGrupo1);
                grupo1.getParticipantes().add("Ana Silva");
                grupo1.getParticipantes().add("Carlos Souza");

                Map<String, String> horariosGrupo2 = new HashMap<>();
                horariosGrupo2.put("Quarta-feira", "15:00");
                Grupo grupo2 = new Grupo("Laboratório de Química", "Química", "Laboratório 3", "D456CD", horariosGrupo2);
                grupo2.getParticipantes().add("Lucas Pereira");
                grupo2.getParticipantes().add("Julia Costa");

                Map<String, String> horariosGrupo3 = new HashMap<>();
                horariosGrupo3.put("Sexta-feira", "09:00");
                Grupo grupo3 = new Grupo("Debates de Filosofia", "Filosofia", "Sala 205", "E789EF", horariosGrupo3);
                grupo3.getParticipantes().add("Maria Oliveira");
                grupo3.getParticipantes().add("Pedro Santos");

                Map<String, String> horariosGrupo4 = new HashMap<>();
                horariosGrupo4.put("Terça-feira", "14:00");
                Grupo grupo4 = new Grupo("Projeto de Design Gráfico", "Design", "Sala 302", "F012GH", horariosGrupo4);
                grupo4.getParticipantes().add("Julia Costa");
                grupo4.getParticipantes().add("Roberta Lima");

                Map<String, String> horariosGrupo5 = new HashMap<>();
                horariosGrupo5.put("Quinta-feira", "11:00");
                Grupo grupo5 = new Grupo("Seminário de Física", "Física", "Auditório", "G345IJ", horariosGrupo5);
                grupo5.getParticipantes().add("Ana Silva");
                grupo5.getParticipantes().add("Carlos Souza");

                salvarGrupoEmArquivo(grupo1);
                salvarGrupoEmArquivo(grupo2);
                salvarGrupoEmArquivo(grupo3);
                salvarGrupoEmArquivo(grupo4);
                salvarGrupoEmArquivo(grupo5);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        SwingUtilities.invokeLater(() -> new TelaLogin().setVisible(true));
    }
}