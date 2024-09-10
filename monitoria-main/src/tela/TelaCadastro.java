package tela;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaCadastro extends JFrame {

    public TelaCadastro() {
        setTitle("Cadastro");
        setSize(250, 100);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JButton alunoButton = new JButton("Aluno");
        alunoButton.setBounds(10, 20, 100, 30);
        add(alunoButton);

        JButton professorButton = new JButton("Professor");
        professorButton.setBounds(125, 20, 100, 30);
        add(professorButton);

        alunoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaCadastroAluno telaCadastroAluno = new TelaCadastroAluno();
                telaCadastroAluno.setVisible(true);
                dispose();
            }
        });

        professorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaCadastroProfessor telaCadastroProfessor = new TelaCadastroProfessor();
                telaCadastroProfessor.setVisible(true);
                dispose();
            }
        });
    }
}
