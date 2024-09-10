package tela;

import sistema.FileManagerChat;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TelaChat extends JFrame {
    private JTextArea chatArea;
    private JTextField mensagemField;
    private JButton enviarButton;
    private String grupoNome;
    private String usuario;

    public TelaChat(String grupoNome, String usuario) {
        this.grupoNome = grupoNome;
        this.usuario = usuario;

        setTitle("Chat - " + grupoNome);
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(chatArea);
        scrollPane.setBounds(10, 10, 360, 270);
        add(scrollPane);

        mensagemField = new JTextField();
        mensagemField.setBounds(10, 300, 260, 30);
        add(mensagemField);

        enviarButton = new JButton("Enviar");
        enviarButton.setBounds(280, 300, 90, 30);
        add(enviarButton);

        FileManagerChat.verificarOuCriarDiretorio();
        carregarMensagens();

        enviarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String mensagem = mensagemField.getText();
                if (!mensagem.isEmpty()) {
                    String mensagemComHorario = formatarMensagemComHorario(usuario, mensagem);
                    adicionarMensagem(mensagemComHorario);
                    FileManagerChat.salvarMensagem(grupoNome, mensagemComHorario);
                    mensagemField.setText("");
                }
            }
        });
    }

    private void carregarMensagens() {
        String mensagens = FileManagerChat.carregarMensagens(grupoNome);
        chatArea.setText(mensagens);
    }

    private void adicionarMensagem(String mensagem) {
        chatArea.append(mensagem + "\n");
    }

    public static String formatarMensagemComHorario(String usuario, String mensagem) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String dataHora = formatter.format(date);
        return usuario + " [" + dataHora + "]: " + mensagem;
    }
}