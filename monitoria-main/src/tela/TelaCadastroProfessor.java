package tela;

import model.Professor;
import sistema.FileManagerUsuarios;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

public class TelaCadastroProfessor extends JFrame {
    private JTextField nomeField, emailField, senhaField, materiasField;
    private static final String EMAIL_SUFFIX = "@ifpb.edu.br";

    public TelaCadastroProfessor() {
        setTitle("Cadastro Professor");
        setSize(350, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel nomeLabel = new JLabel("Nome:");
        nomeLabel.setBounds(10, 10, 100, 25);
        add(nomeLabel);

        nomeField = new JTextField();
        nomeField.setBounds(120, 10, 200, 25);
        add(nomeField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(10, 50, 100, 25);
        add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(120, 50, 200, 25);
        add(emailField);

        JLabel senhaLabel = new JLabel("Senha:");
        senhaLabel.setBounds(10, 90, 100, 25);
        add(senhaLabel);

        senhaField = new JPasswordField();
        senhaField.setBounds(120, 90, 200, 25);
        add(senhaField);

        JLabel obsmateriasLabel = new JLabel("(OBS:separadas por vírgula)");
        obsmateriasLabel.setBounds(120, 155, 200, 25);
        add(obsmateriasLabel);

        JLabel materiasLabel = new JLabel("Matérias:");
        materiasLabel.setBounds(10, 130, 100, 25);
        add(materiasLabel);

        materiasField = new JTextField();
        materiasField.setBounds(120, 130, 200, 25);
        add(materiasField);

        JButton cadastrarButton = new JButton("Cadastrar");
        cadastrarButton.setBounds(70, 200, 100, 30);
        add(cadastrarButton);

        JButton cancelarButton = new JButton("Cancelar");
        cancelarButton.setBounds(180, 200, 100, 30);
        add(cancelarButton);

        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarProfessor();
            }
        });

        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private void cadastrarProfessor() {
        String nome = nomeField.getText();
        String email = emailField.getText();
        String senha = new String(((JPasswordField) senhaField).getPassword());
        String materiasText = materiasField.getText();

        if (!email.endsWith(EMAIL_SUFFIX)) {
            JOptionPane.showMessageDialog(this, "Email inválido (utilize o email academico)");
            return;
        }

        if (nome.isEmpty() || email.isEmpty() || senha.isEmpty() || materiasText.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Todos os campos devem ser preenchidos.");
            return;
        }

        if (FileManagerUsuarios.emailExists(email)) {
            JOptionPane.showMessageDialog(this, "Email já cadastrado.");
            return;
        }

        List<String> materias = Arrays.asList(materiasText.split(",\\s*"));
        Professor professor = new Professor(nome, email, senha, materias);
        FileManagerUsuarios.saveProfessor(professor);
        JOptionPane.showMessageDialog(this, "Professor cadastrado com sucesso!");
        dispose();
    }
}


