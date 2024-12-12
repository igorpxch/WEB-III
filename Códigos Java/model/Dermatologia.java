package model;

import database.DermatologiaDAO; 
import java.sql.SQLException;
import java.util.ArrayList;

public class Dermatologia {
    private int id;
    private String nome; 
    private double preco; 

    public Dermatologia() {}

    public Dermatologia(double preco) {
        this.preco = preco;
    }

    public Dermatologia(int id, String nome, double preco) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public boolean verificarServico() throws ClassNotFoundException, SQLException {
        boolean key = false;
        DermatologiaDAO dao = new DermatologiaDAO();
        ArrayList<Dermatologia> list = dao.getAllServicos(); 
        
        for (Dermatologia d : list) {
            if (d.getNome().equals(this.nome)) {
                if (d.getPreco() == this.preco) {
                    key = true;
                }
            }
        }

        return key;
    }
}
