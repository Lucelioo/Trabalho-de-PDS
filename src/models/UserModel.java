package models;

import java.sql.*;

public class UserModel {
    public static boolean autenticarUsuario(String usuario, String senha) throws SQLException {
        String query = "SELECT senha_acesso FROM acesso WHERE usuario_acesso = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, usuario);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String senhaHash = rs.getString("senha_acesso");
                    return senha.equals(senhaHash); // Trocar para hash comparável
                }
            }
        }
        return false;
    }
}
