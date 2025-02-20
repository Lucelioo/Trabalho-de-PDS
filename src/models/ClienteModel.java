package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import utils.InventoryIterator;
import utils.ListInventoryIterator;

public class ClienteModel {

    public static boolean cadastrarCliente(String nome, String telefone, String endereco, String cpf) {
        String query = "INSERT INTO clientes (nome_cliente, telefone_cliente, endereco_cliente, cpf_cliente) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, nome);
            stmt.setString(2, telefone);
            stmt.setString(3, endereco);
            stmt.setString(4, cpf);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar cliente: " + e.getMessage());
            return false;
        }
    }

    public static boolean alterarCliente(int id, String nome, String telefone, String endereco, String cpf) {
        String query = "UPDATE clientes SET nome_cliente = ?, telefone_cliente = ?, endereco_cliente = ?, cpf_cliente = ? WHERE id_cliente = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, nome);
            stmt.setString(2, telefone);
            stmt.setString(3, endereco);
            stmt.setString(4, cpf);
            stmt.setInt(5, id);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao alterar cliente: " + e.getMessage());
            return false;
        }
    }

    public static boolean excluirCliente(int id) {
        String query = "DELETE FROM clientes WHERE id_cliente = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao excluir cliente: " + e.getMessage());
            return false;
        }
    }

    public static List<Cliente> listarClientes() {
        List<Cliente> clientes = new ArrayList<>();
        String query = "SELECT id_cliente, nome_cliente, telefone_cliente, endereco_cliente, cpf_cliente FROM clientes";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                clientes.add(new Cliente(
                    rs.getInt("id_cliente"),
                    rs.getString("nome_cliente"),
                    rs.getString("telefone_cliente"),
                    rs.getString("endereco_cliente"),
                    rs.getString("cpf_cliente")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar clientes: " + e.getMessage());
        }
        return clientes;
    }

    public static InventoryIterator<Cliente> getIterator() {
    return new ListInventoryIterator<>(listarClientes());
    }

    public static class Cliente {
        private final int id;
        private final String nome;
        private final String telefone;
        private final String endereco;
        private final String cpf;
    
        public Cliente(int id, String nome, String telefone, String endereco, String cpf) {
            this.id = id;
            this.nome = nome;
            this.telefone = telefone;
            this.endereco = endereco;
            this.cpf = cpf;
        }
    
        // Add these getters
        public int getId() { return id; }
        public String getNome() { return nome; }
        public String getTelefone() { return telefone; }
        public String getEndereco() { return endereco; }
        public String getCpf() { return cpf; }
    
        @Override
        public String toString() {
            return String.format("%d | %s | %s | %s | %s", id, nome, telefone, endereco, cpf);
        }
    }
}