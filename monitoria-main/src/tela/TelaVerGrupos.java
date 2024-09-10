package tela;

import sistema.FileManagerGrupos;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TelaVerGrupos extends JFrame {
    private JList<String> gruposList;
    private DefaultListModel<String> listModel;
    private JButton abrirChatButton;
    private JButton verInformacoesButton;
    private String usuario;

    public TelaVerGrupos(String usuario) {
        this.usuario = usuario;

        setTitle("Ver Grupos");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel tituloLabel = new JLabel("Grupos que você está participando:");
        tituloLabel.setBounds(10, 10, 300, 30);
        add(tituloLabel);

        listModel = new DefaultListModel<>();
        gruposList = new JList<>(listModel);
        gruposList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(gruposList);
        scrollPane.setBounds(10, 50, 360, 150);
        add(scrollPane);

        abrirChatButton = new JButton("Abrir Chat");
        abrirChatButton.setBounds(210, 220, 100, 30);
        add(abrirChatButton);

        verInformacoesButton = new JButton("Ver Informações");
        verInformacoesButton.setBounds(50, 220, 150, 30);
        add(verInformacoesButton);

        carregarGrupos();

        abrirChatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String grupoSelecionado = gruposList.getSelectedValue();
                if (grupoSelecionado != null) {
                    new TelaChat(grupoSelecionado, usuario).setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione um grupo para abrir o chat.");
                }
            }
        });

        verInformacoesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String grupoSelecionado = gruposList.getSelectedValue();
                if (grupoSelecionado != null) {
                    String informacoes = FileManagerGrupos.obterInformacoesDoGrupo(grupoSelecionado);
                    if (!informacoes.isEmpty()) {
                        JOptionPane.showMessageDialog(null, informacoes, "Informações do Grupo", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Informações do grupo não encontradas.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione um grupo para ver as informações.");
                }
            }
        });
    }

    private void carregarGrupos() {
        List<String> grupos = FileManagerGrupos.carregarGruposParaUsuario(usuario);
        for (String grupo : grupos) {
            listModel.addElement(grupo);
        }
    }
}