package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Grupo {
    private String nomeGrupo;
    private String materia;
    private String local;
    private String codigoGrupo;
    private Map<String, String> horarios;
    private List<String> participantes;

    public Grupo(String nomeGrupo, String materia, String local, String codigoGrupo, Map<String, String> horarios) {
        this.nomeGrupo = nomeGrupo;
        this.materia = materia;
        this.local = local;
        this.codigoGrupo = codigoGrupo;
        this.horarios = horarios;
        this.participantes = new ArrayList<>();
    }

    public String getNomeGrupo() {
        return nomeGrupo;
    }

    public String getMateria() {
        return materia;
    }

    public String getLocal() {
        return local;
    }

    public String getCodigoGrupo() {
        return codigoGrupo;
    }

    public Map<String, String> getHorarios() {
        return horarios;
    }

    public List<String> getParticipantes() {
        return participantes;
    }

    public void adicionarParticipante(String participante) {
        this.participantes.add(participante);
    }
}