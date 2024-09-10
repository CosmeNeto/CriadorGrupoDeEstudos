package tela;

import sistema.FileManagerGrupos;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaEntrarNoGrupo extends JFrame {

    private JTextField codigoGrupoField;
    private JButton verificarButton;
    private JButton cancelarButton;
    private String usuarioAtual;

    public TelaEntrarNoGrupo(String usuarioAtual) {
        this.usuarioAtual = usuarioAtual;

        setTitle("Entrar no Grupo");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel codigoGrupoLabel = new JLabel("Código do Grupo:");
        codigoGrupoLabel.setBounds(50, 30, 120, 30);
        add(codigoGrupoLabel);

        codigoGrupoField = new JTextField();
        codigoGrupoField.setBounds(180, 30, 100, 30);
        add(codigoGrupoField);

        verificarButton = new JButton("Verificar");
        verificarButton.setBounds(50, 80, 100, 30);
        add(verificarButton);

        cancelarButton = new JButton("Cancelar");
        cancelarButton.setBounds(180, 80, 100, 30);
        add(cancelarButton);

        verificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String codigoGrupo = codigoGrupoField.getText();
                if (FileManagerGrupos.codigoJaExiste(codigoGrupo)) {
                    if (FileManagerGrupos.adicionarParticipante(codigoGrupo, usuarioAtual)) {
                        JOptionPane.showMessageDialog(null, "Você foi adicionado ao grupo com sucesso.");
                        codigoGrupoField.setText("");
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Você já está no grupo.");
                        dispose();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Código do grupo inválido. Tente novamente.");
                }
            }
        });

        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}



