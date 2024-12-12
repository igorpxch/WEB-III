package model;

import database.PrecoDAO; 
import java.sql.SQLException;
import java.util.ArrayList;

public class Preco {
    private int id;
    private String descricao;
    private double valor;

    public Preco() {}

    public Preco(double valor) {
        this.valor = valor;
    }

    public Preco(String descricao, double valor) {
        this.descricao = descricao;
        this.valor = valor;
    }

    public Preco(int id, String descricao, double valor) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public boolean verificarPreco() throws ClassNotFoundException, SQLException {
        boolean key = false;
        PrecoDAO dao = new PrecoDAO();
        ArrayList<Preco> list = dao.getAllPrecos(); 
        
        for (Preco p : list) {
            if (p.getDescricao().equals(this.descricao)) {
                if (p.getValor() == this.valor) {
                    key = true;
                }
            }
        }

        return key;
    }
}
