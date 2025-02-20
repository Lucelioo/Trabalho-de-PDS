package models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HistoricoSaidaModel {
    public static class HistoricoSaida {
        private final int idHistorico;
        private final int idProduto;
        private final String nomeProduto;
        private final Timestamp dataSaida;

        public HistoricoSaida(int idHistorico, int idProduto, String nomeProduto, Timestamp dataSaida) {
            this.idHistorico = idHistorico;
            this.idProduto = idProduto;
            this.nomeProduto = nomeProduto;
            this.dataSaida = dataSaida;
        }

        // Getters
        public int getIdHistorico() { return idHistorico; }
        public int getIdProduto() { return idProduto; }
        public String getNomeProduto() { return nomeProduto; }
        public Timestamp getDataSaida() { return dataSaida; }
    }

    public static boolean registrarSaida(int idProduto, String nomeProduto) {
        String query = "INSERT INTO historico_saida (id_produto, nome_produto, data_saida) VALUES (?, ?, CURRENT_TIMESTAMP)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, idProduto);
            stmt.setString(2, nomeProduto);
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao registrar saída: " + e.getMessage());
            return false;
        }
    }

    public static List<HistoricoSaida> listarHistorico() {
        List<HistoricoSaida> historico = new ArrayList<>();
        String query = "SELECT id_historico, id_produto, nome_produto, data_saida FROM historico_saida";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            while (rs.next()) {
                historico.add(new HistoricoSaida(
                    rs.getInt("id_historico"),
                    rs.getInt("id_produto"),
                    rs.getString("nome_produto"),
                    rs.getTimestamp("data_saida")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar histórico: " + e.getMessage());
        }
        return historico;
    }
}