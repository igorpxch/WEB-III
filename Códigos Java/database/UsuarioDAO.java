package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Usuario;

public class UsuarioDAO {
    private static Connection conn;
    
    public UsuarioDAO() throws ClassNotFoundException, SQLException {
        conn = Conexao.getConn();
    }
    
    public ArrayList<Usuario> getAllUsers() throws SQLException {
        
        ArrayList<Usuario> list = new ArrayList();
        
        String query = "select * from usuarios;";
        
        try (PreparedStatement prep = conn.prepareStatement(query)) {
            ResultSet res = prep.executeQuery();
            
            while( res.next() ) {
                Usuario user = new Usuario();
                
                user.setNome(res.getString("nome") );
                user.setSenha(res.getString("senha") );
                list.add(user);
                System.out.println(user);
            }
        }
        return list;
    }
    
    
    public void setNewUser(Usuario user) throws SQLException {
        String query = "insert into usuarios(nome, senha) "
                     + "values(?, sha1(?))";
        
        try (PreparedStatement prep = conn.prepareStatement(query)) {
            prep.setString(1, user.getNome() );
            prep.setString(4, user.getSenha() );
            
            prep.execute();
        }
    }

    
    public Usuario getOneUser(int id) throws SQLException {
        String query = "select * from usuarios where id = " + id;
        
        Usuario u;
        try (PreparedStatement prep = conn.prepareStatement(query)) {
            ResultSet res = prep.executeQuery();
            u = new Usuario();
            if( res.next() ) {
                u.setNome(res.getString("nome"));
                u.setSenha(res.getString("senha"));
                
            }
        }
        return u;
    }
    
    public Usuario getOneUserByEmail(String email) throws SQLException {
        String query = "select * from usuarios where email = ?";
        
        Usuario u;
        try (PreparedStatement prep = conn.prepareStatement(query)) {
            prep.setString(1, email);
            ResultSet res = prep.executeQuery();
            u = new Usuario();
            if( res.next() ) {
                u.setNome(res.getString("nome"));
                u.setSenha(res.getString("senha"));
            }
        }
        return u;
    }
    
    
    public void updateUser(Usuario user) throws SQLException {
        String query = "update usuarios set nome = ?, "
                     + "email = ?, nascimento = ?, noticias = ? "
                     + "where id = ?";
        
        try (PreparedStatement prep = conn.prepareStatement(query)) {
            prep.setString(1, user.getNome() );
            
            
            prep.execute();
        }
    }
}