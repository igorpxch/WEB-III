package database;

import model.Dermatologia;
import java.sql.*;
import java.util.ArrayList;

public class DermatologiaDAO {
    private Connection connection;

    // Constructor to initialize the database connection
    public DermatologiaDAO() throws SQLException, ClassNotFoundException {
        // Make sure to load the JDBC driver before using it (if necessary)
        Class.forName("com.mysql.cj.jdbc.Driver");
        // Adjust the URL, username, and password for your database
        this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dermatologia_db", "root", "password");
    }

    // Method to get all dermatology services
    public ArrayList<Dermatologia> getAllServicos() throws SQLException {
        ArrayList<Dermatologia> servicos = new ArrayList<>();
        String query = "SELECT * FROM dermatologia"; // Adjust the table name to match your DB schema
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                double preco = rs.getDouble("preco");
                Dermatologia servico = new Dermatologia(id, nome, preco);
                servicos.add(servico);
            }
        }
        return servicos;
    }

    // Method to add a new dermatology service
    public boolean addServico(Dermatologia servico) throws SQLException {
        String query = "INSERT INTO dermatologia (nome, preco) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, servico.getNome());
            stmt.setDouble(2, servico.getPreco());
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; // Returns true if the insert was successful
        }
    }

    // Method to update an existing dermatology service
    public boolean updateServico(Dermatologia servico) throws SQLException {
        String query = "UPDATE dermatologia SET nome = ?, preco = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, servico.getNome());
            stmt.setDouble(2, servico.getPreco());
            stmt.setInt(3, servico.getId());
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; // Returns true if the update was successful
        }
    }

    // Method to delete a dermatology service by ID
    public boolean deleteServico(int id) throws SQLException {
        String query = "DELETE FROM dermatologia WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; // Returns true if the delete was successful
        }
    }

    // Close the connection
    public void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
}
