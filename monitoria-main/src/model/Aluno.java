package model;

import java.io.Serializable;

public class Aluno extends Usuario implements Serializable {
    private String matricula;
    private String curso;
    private String periodo;
    private String periodoTipo;
    private double cre;

    public Aluno(String nome, String email, String senha, String matricula, String curso, String periodo, String periodoTipo, double cre) {
        super(nome, email, senha);
        this.matricula = matricula;
        this.curso = curso;
        this.periodo = periodo;
        this.periodoTipo = periodoTipo;
        this.cre = cre;
    }

    public String getMatricula() {
        return matricula;
    }

    @Override
    public String toString() {
        return super.toString() + ";" + matricula + ";" + curso + ";" + periodo + ";" + periodoTipo + ";" + cre;
    }
}
