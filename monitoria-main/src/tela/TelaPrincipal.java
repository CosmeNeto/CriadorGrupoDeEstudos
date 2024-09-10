package tela;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaPrincipal extends JFrame {

    private String nomeUsuario;
    private JLabel bemVindoLabel;
    private JButton criarGrupoButton;
    private JButton entrarNoGrupoButton;
    private JButton verGruposButton;
    private JButton sairButton;

    public TelaPrincipal(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
        setTitle("Tela Principal - Criador de Grupos de Estudos");
        setSize(300, 320);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        bemVindoLabel = new JLabel("Bem-vindo! " + nomeUsuario);
        bemVindoLabel.setBounds(10, 10, 400, 30);
        add(bemVindoLabel);

        criarGrupoButton = new JButton("Criar Grupo");
        criarGrupoButton.setBounds(45, 60, 200, 30);
        add(criarGrupoButton);

        entrarNoGrupoButton = new JButton("Entrar no Grupo");
        entrarNoGrupoButton.setBounds(45, 100, 200, 30);
        add(entrarNoGrupoButton);

        verGruposButton = new JButton("Ver Grupos");
        verGruposButton.setBounds(45, 140, 200, 30);
        add(verGruposButton);

        sairButton = new JButton("Sair");
        sairButton.setBounds(45, 180, 200, 30);
        add(sairButton);

        criarGrupoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TelaCriarGrupo(nomeUsuario).setVisible(true);
            }
        });

        entrarNoGrupoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TelaEntrarNoGrupo(nomeUsuario).setVisible(true);
            }
        });

        verGruposButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TelaVerGrupos(nomeUsuario).setVisible(true);
            }
        });

        sairButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new TelaLogin().setVisible(true);
            }
        });
    }
}