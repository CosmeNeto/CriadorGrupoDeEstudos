package tela;

import model.Grupo;
import sistema.FileManagerGrupos;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class TelaCriarGrupo extends JFrame {

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
    private static final String CAMINHO_ARQUIVO_GRUPOS = "monitoria-main/resources/database/grupos/grupos.txt";
    private JTextField nomeGrupoField;
    private JTextField materiaField;
    private JTextField localField;
    private JButton criarGrupoButton;
    private JButton cancelarButton;

    private Map<String, JCheckBox> diasSemanaCheckboxes;
    private Map<String, JTextField> horariosFields;

    private String usuarioCriador;

    public TelaCriarGrupo(String usuarioCriador) {
        this.usuarioCriador = usuarioCriador;

        setTitle("Criar Grupo de Estudo");
        setSize(400, 570);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel nomeGrupoLabel = new JLabel("Nome do Grupo:");
        nomeGrupoLabel.setBounds(50, 20, 120, 30);
        add(nomeGrupoLabel);

        nomeGrupoField = new JTextField();
        nomeGrupoField.setBounds(180, 20, 150, 30);
        add(nomeGrupoField);

        JLabel materiaLabel = new JLabel("Matéria:");
        materiaLabel.setBounds(50, 60, 120, 30);
        add(materiaLabel);

        materiaField = new JTextField();
        materiaField.setBounds(180, 60, 150, 30);
        add(materiaField);

        JLabel localLabel = new JLabel("Local:");
        localLabel.setBounds(50, 100, 120, 30);
        add(localLabel);

        localField = new JTextField();
        localField.setBounds(180, 100, 150, 30);
        add(localField);

        JLabel instrucoesDias = new JLabel("Selecione os dias:");
        instrucoesDias.setBounds(50, 140, 150, 30);
        add(instrucoesDias);


        diasSemanaCheckboxes = new HashMap<>();
        horariosFields = new HashMap<>();
        String[] diasDaSemana = {"Domingo", "Segunda", "Terça", "Quarta", "Quinta", "Sexta", "Sábado"};

        int yPosition = 180;

        for (String dia : diasDaSemana) {
            JCheckBox diaCheckbox = new JCheckBox(dia);
            diaCheckbox.setBounds(50, yPosition, 120, 30);
            add(diaCheckbox);
            diasSemanaCheckboxes.put(dia, diaCheckbox);

            JLabel horarioLabel = new JLabel("Horário:");
            horarioLabel.setBounds(180, yPosition, 80, 30);
            horarioLabel.setVisible(false);
            add(horarioLabel);

            JTextField horarioField = new JTextField();
            horarioField.setBounds(260, yPosition, 70, 30);
            horarioField.setVisible(false);
            add(horarioField);
            horariosFields.put(dia, horarioField);

            diaCheckbox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    boolean selecionado = diaCheckbox.isSelected();
                    horarioLabel.setVisible(selecionado);
                    horarioField.setVisible(selecionado);
                }
            });

            yPosition += 40;
        }

        criarGrupoButton = new JButton("Criar Grupo");
        criarGrupoButton.setBounds(50, yPosition + 20, 120, 30);
        add(criarGrupoButton);

        cancelarButton = new JButton("Cancelar");
        cancelarButton.setBounds(210, yPosition + 20, 120, 30);
        add(cancelarButton);

        criarGrupoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomeGrupo = nomeGrupoField.getText();
                String materia = materiaField.getText();
                String local = localField.getText();

                if (nomeGrupo.isEmpty() || materia.isEmpty() || local.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Todos os campos devem ser preenchidos.");
                    return;
                }

                if (FileManagerGrupos.grupoJaExiste(nomeGrupo)) {
                    JOptionPane.showMessageDialog(null, "Já existe um grupo com esse nome. Por favor, escolha outro nome.");
                    return;
                }

                Map<String, String> horarios = new HashMap<>();
                for (String dia : diasDaSemana) {
                    JCheckBox checkbox = diasSemanaCheckboxes.get(dia);
                    if (checkbox.isSelected()) {
                        String horario = horariosFields.get(dia).getText();
                        if (!validarHorario(horario)) {
                            JOptionPane.showMessageDialog(null, "Horário inválido para " + dia + ". Deve estar no formato HH:MM, com HH entre 07 e 18 e MM entre 00 e 59.");
                            return;
                        }
                        horarios.put(dia, horario);
                    }
                }

                String codigoGrupo = gerarCodigoGrupo();
                while (FileManagerGrupos.codigoJaExiste(codigoGrupo)) {
                    codigoGrupo = gerarCodigoGrupo();
                }

                Grupo grupo = new Grupo(nomeGrupo, materia, local, codigoGrupo, horarios);
                grupo.adicionarParticipante(usuarioCriador);

                FileManagerGrupos.salvarGrupoEmArquivo(grupo);

                JOptionPane.showMessageDialog(null, "Grupo criado com sucesso! Código do Grupo: " + codigoGrupo);
                dispose();
            }
        });

        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private boolean validarHorario(String horario) {
        if (!horario.matches("\\d{2}:\\d{2}")) {
            return false;
        }

        String[] partes = horario.split(":");
        int horas = Integer.parseInt(partes[0]);
        int minutos = Integer.parseInt(partes[1]);

        return horas >= 7 && horas <= 18 && minutos >= 0 && minutos <= 59;
    }

    private String gerarCodigoGrupo() {
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder codigo = new StringBuilder(6);
        for (int i = 0; i < 6; i++) {
            codigo.append(caracteres.charAt(random.nextInt(caracteres.length())));
        }
        return codigo.toString();
    }

}