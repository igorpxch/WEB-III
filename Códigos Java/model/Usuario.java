package model;

import database.UsuarioDAO;
import java.sql.SQLException;
import java.util.ArrayList;


public class Usuario {
    private int id;
    private String nome;
    private String senha;

    public Usuario() {}

    public Usuario(String email, String senha) {
        this.senha = senha;
    }
    
    public Usuario(String nome, String email, String nascimento, String senha, boolean noticias) {
        this.nome = nome;
        this.senha = senha;
    }

    public Usuario(int id, String nome, String email, String nascimento, boolean noticias) {
        this.id = id;
        this.nome = nome;
    }
    
    
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


    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }


    public boolean login() throws ClassNotFoundException, SQLException {
        boolean key = false;
        UsuarioDAO dao = new UsuarioDAO();
        ArrayList<Usuario> list = dao.getAllUsers();    
        
        for(Usuario u : list) {
            if( u.getNome().equals(this.nome) ) {
                if( u.getSenha().equals(this.senha) ) {
                    key = true;
                }
            }
        }
                
        return key;
    }
}