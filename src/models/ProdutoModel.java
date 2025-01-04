package models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM produtos WHERE id_produto = ?")) {
            stmt.setInt(1, id);
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0; // Retorna true se alguma linha foi excluída
        } catch (SQLException e) {
            System.out.println("Erro ao excluir o produto: " + e.getMessage());
            return false; // Retorna false em caso de erro
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
}



