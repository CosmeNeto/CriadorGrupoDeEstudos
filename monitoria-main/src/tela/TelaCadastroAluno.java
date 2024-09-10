package tela;

import model.Aluno;
import sistema.FileManagerUsuarios;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaCadastroAluno extends JFrame {
    private JTextField nomeField, emailField, senhaField, matriculaField, cursoField, periodoField, creField;
    private JRadioButton semestreRadioButton, anoRadioButton;
    private static final String EMAIL_SUFFIX = "@academico.ifpb.edu.br";

    public TelaCadastroAluno() {
        setTitle("Cadastro Aluno");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel nomeLabel = new JLabel("Nome:");
        nomeLabel.setBounds(10, 10, 100, 25);
        add(nomeLabel);

        nomeField = new JTextField();
        nomeField.setBounds(120, 10, 150, 25);
        add(nomeField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(10, 50, 100, 25);
        add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(120, 50, 150, 25);
        add(emailField);

        JLabel senhaLabel = new JLabel("Senha:");
        senhaLabel.setBounds(10, 90, 100, 25);
        add(senhaLabel);

        senhaField = new JPasswordField();
        senhaField.setBounds(120, 90, 150, 25);
        add(senhaField);

        JLabel matriculaLabel = new JLabel("Matrícula:");
        matriculaLabel.setBounds(10, 130, 100, 25);
        add(matriculaLabel);

        matriculaField = new JTextField();
        matriculaField.setBounds(120, 130, 150, 25);
        add(matriculaField);

        JLabel cursoLabel = new JLabel("Curso:");
        cursoLabel.setBounds(10, 170, 100, 25);
        add(cursoLabel);

        cursoField = new JTextField();
        cursoField.setBounds(120, 170, 150, 25);
        add(cursoField);

        JLabel periodoLabel = new JLabel("Período:");
        periodoLabel.setBounds(10, 210, 100, 25);
        add(periodoLabel);

        periodoField = new JTextField();
        periodoField.setBounds(120, 210, 60, 25);
        add(periodoField);

        semestreRadioButton = new JRadioButton("Semestre");
        semestreRadioButton.setBounds(190, 210, 100, 25);
        add(semestreRadioButton);

        anoRadioButton = new JRadioButton("Ano");
        anoRadioButton.setBounds(190, 240, 100, 25);
        add(anoRadioButton);

        ButtonGroup periodoGroup = new ButtonGroup();
        periodoGroup.add(semestreRadioButton);
        periodoGroup.add(anoRadioButton);

        JLabel creLabel = new JLabel("CRE:");
        creLabel.setBounds(10, 270, 100, 25);
        add(creLabel);

        creField = new JTextField();
        creField.setBounds(120, 270, 150, 25);
        add(creField);

        JButton cadastrarButton = new JButton("Cadastrar");
        cadastrarButton.setBounds(50, 320, 100, 30);
        add(cadastrarButton);

        JButton cancelarButton = new JButton("Cancelar");
        cancelarButton.setBounds(160, 320, 100, 30);
        add(cancelarButton);

        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarAluno();
            }
        });

        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private void cadastrarAluno() {
        String nome = nomeField.getText();
        String email = emailField.getText();
        String senha = new String(((JPasswordField) senhaField).getPassword());
        String matricula = matriculaField.getText();
        String curso = cursoField.getText();
        String periodo = periodoField.getText();
        String periodoTipo = semestreRadioButton.isSelected() ? "Semestre" : "Ano";
        double cre = Double.parseDouble(creField.getText());


        if (nome.isEmpty() || email.isEmpty() || senha.isEmpty() ||
                matricula.isEmpty() || curso.isEmpty() || periodo.isEmpty() ||
                periodoTipo.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Todos os campos devem ser preenchidos.");
            return;
        }

        if (cre < 0 || cre > 100) {
            JOptionPane.showMessageDialog(null, "O CRE deve ser entre 0 e 100.");
            return;
        }

        if (!email.endsWith(EMAIL_SUFFIX)) {
            JOptionPane.showMessageDialog(this, "Email inválido (utilize o email academico)");
            return;
        }

        if (FileManagerUsuarios.emailExists(email)) {
            JOptionPane.showMessageDialog(this, "Email já cadastrado.");
            return;
        }

        if (matricula.length() != 12 || !matricula.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "Matrícula deve ter exatamente 12 números.");
            return;
        }

        if (FileManagerUsuarios.matriculaExists(matricula)) {
            JOptionPane.showMessageDialog(this, "Matrícula já cadastrada.");
            return;
        }

        Aluno aluno = new Aluno(nome, email, senha, matricula, curso, periodo, periodoTipo, cre);
        FileManagerUsuarios.saveAluno(aluno);
        JOptionPane.showMessageDialog(this, "Aluno cadastrado com sucesso!");
        dispose();
    }
}


