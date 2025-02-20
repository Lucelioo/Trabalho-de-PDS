package models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import utils.InventoryIterator;
import utils.ListInventoryIterator;

public class ProdutoModel {

    // Método corrigido
    public static boolean cadastrarProduto(String nome) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO produtos (nome_produto) VALUES (?)")) {
            stmt.setString(1, nome);
            stmt.executeUpdate();
            return true; // Retorna true se o cadastro for bem-sucedido
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar o produto: " + e.getMessage());
            return false; // Retorna false em caso de erro
        }
    }

    public static boolean alterarProduto(int id, String nome) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("UPDATE produtos SET nome_produto = ? WHERE id_produto = ?")) {
            stmt.setString(1, nome);
            stmt.setInt(2, id);
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0; // Retorna true se alguma linha foi atualizada
        } catch (SQLException e) {
            System.out.println("Erro ao alterar o produto: " + e.getMessage());
            return false; // Retorna false em caso de erro
        }
    }

    public static boolean excluirProduto(int id) {
        String query = "DELETE FROM produtos WHERE id_produto = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir produto: " + e.getMessage());
            return false;
        }
    }
    

    public static List<Produto> listarProdutos() {
        List<Produto> produtos = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT id_produto, nome_produto FROM produtos")) {

            while (rs.next()) {
                // Cria um objeto Produto para cada registro no banco
                Produto produto = new Produto(rs.getInt("id_produto"), rs.getString("nome_produto"));
                produtos.add(produto);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar produtos: " + e.getMessage());
        }

        return produtos; // Retorna a lista de produtos
    }

    public static InventoryIterator<Produto> getIterator() {
    return new ListInventoryIterator<>(listarProdutos());
    }

    public static class Produto {
        private int id;
        private String nome;

        public Produto(int id, String nome) {
            this.id = id;
            this.nome = nome;
        }

        public int getId() {
            return id;
        }

        public String getNome() {
            return nome;
        }

        @Override
        public String toString() {
            return id + " - " + nome;
        }
    }

    public static String getNomeProduto(int id) {
        String query = "SELECT nome_produto FROM produtos WHERE id_produto = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getString("nome_produto");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar nome do produto: " + e.getMessage());
        }
        return null;
    }
}

