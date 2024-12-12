package database;

import model.Preco;
import java.sql.*;
import java.util.ArrayList;

public class PrecoDAO {
    private Connection connection;

    // Constructor to initialize the database connection
    public PrecoDAO() throws SQLException, ClassNotFoundException {
        // Load the JDBC driver (if necessary)
        Class.forName("com.mysql.cj.jdbc.Driver");
        // Replace with your actual database URL, username, and password
        this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/your_database_name", "root", "password");
    }

    // Method to retrieve all prices from the database
    public ArrayList<Preco> getAllPrecos() throws SQLException {
        ArrayList<Preco> precos = new ArrayList<>();
        String query = "SELECT * FROM preco";  // Assuming the table name is 'preco'
        
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String descricao = rs.getString("descricao");
                double valor = rs.getDouble("valor");
                Preco preco = new Preco(id, descricao, valor);
                precos.add(preco);
            }
        }
        return precos;
    }

    // Method to add a new price record to the database
    public boolean addPreco(Preco preco) throws SQLException {
        String query = "INSERT INTO preco (descricao, valor) VALUES (?, ?)";
        
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, preco.getDescricao());
            stmt.setDouble(2, preco.getValor());
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;  // Returns true if insertion was successful
        }
    }

    // Method to update an existing price record in the database
    public boolean updatePreco(Preco preco) throws SQLException {
        String query = "UPDATE preco SET descricao = ?, valor = ? WHERE id = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, preco.getDescricao());
            stmt.setDouble(2, preco.getValor());
            stmt.setInt(3, preco.getId());
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;  // Returns true if update was successful
        }
    }

    // Method to delete a price record by ID
    public boolean deletePreco(int id) throws SQLException {
        String query = "DELETE FROM preco WHERE id = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;  // Returns true if deletion was successful
        }
    }

    // Close the database connection
    public void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
}
