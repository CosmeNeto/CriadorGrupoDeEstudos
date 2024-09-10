package model;

import java.io.Serializable;
import java.util.List;

public class Professor extends Usuario implements Serializable {
    private List<String> materias;

    public Professor(String nome, String email, String senha, List<String> materias) {
        super(nome, email, senha);
        this.materias = materias;
    }

    @Override
    public String toString() {
        return super.toString() + ";" + String.join(",", materias);
    }
}
