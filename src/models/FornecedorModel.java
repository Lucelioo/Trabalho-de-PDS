package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FornecedorModel {

    public static boolean cadastrarFornecedor(String nome, String contato, String produtos) {
        String query = "INSERT INTO fornecedor (nome_fornecedor, contato_fornecedor, produtos_fornecidos) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, nome);
            stmt.setString(2, contato);
            stmt.setString(3, produtos);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar fornecedor: " + e.getMessage());
            return false;
        }
    }

    public static boolean alterarFornecedor(int id, String nome, String contato, String produtos) {
        String query = "UPDATE fornecedor SET nome_fornecedor = ?, contato_fornecedor = ?, produtos_fornecidos = ? WHERE id_fornecedor = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, nome);
            stmt.setString(2, contato);
            stmt.setString(3, produtos);
            stmt.setInt(4, id);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao alterar fornecedor: " + e.getMessage());
            return false;
        }
    }

    public static boolean excluirFornecedor(int id) {
        String query = "DELETE FROM fornecedor WHERE id_fornecedor = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao excluir fornecedor: " + e.getMessage());
            return false;
        }
    }

    public static List<Fornecedor> listarFornecedores() {
        List<Fornecedor> fornecedores = new ArrayList<>();
        String query = "SELECT id_fornecedor, nome_fornecedor, contato_fornecedor, produtos_fornecidos FROM fornecedor";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Fornecedor fornecedor = new Fornecedor(
                    rs.getInt("id_fornecedor"),
                    rs.getString("nome_fornecedor"),
                    rs.getString("contato_fornecedor"),
                    rs.getString("produtos_fornecidos")
                );
                fornecedores.add(fornecedor);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar fornecedores: " + e.getMessage());
        }

        return fornecedores;
    }

    // Classe Fornecedor
    public static class Fornecedor {
        private int id;
        private String nome;
        private String contato;
        private String produtosFornecidos;

        public Fornecedor(int id, String nome, String contato, String produtosFornecidos) {
            this.id = id;
            this.nome = nome;
            this.contato = contato;
            this.produtosFornecidos = produtosFornecidos;
        }

        public int getId() {
            return id;
        }

        public String getNome() {
            return nome;
        }

        public String getContato() {
            return contato;
        }

        public String getProdutosFornecidos() {
            return produtosFornecidos;
        }

        @Override
        public String toString() {
            return id + " | " + nome + " | " + contato + " | " + produtosFornecidos;
        }
    }
}
