package views;

import javax.swing.*;
import java.awt.*;
import controllers.AuthController;

public class LoginFrame extends JFrame {
    private JTextField txtUsuario = new JTextField(20);
    private JPasswordField txtSenha = new JPasswordField(20);

    public LoginFrame() {
        setTitle("Login do Sistema");
        setSize(350, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panel.add(new JLabel("Usuário:"));
        panel.add(txtUsuario);
        panel.add(new JLabel("Senha:"));
        panel.add(txtSenha);

        JButton btnLogin = new JButton("Login");
        btnLogin.addActionListener(e -> realizarLogin());
        panel.add(btnLogin);

        add(panel);
    }

    private void realizarLogin() {
        String usuario = txtUsuario.getText();
        String senha = new String(txtSenha.getPassword());

        if (AuthController.autenticarUsuario(usuario, senha)) {
            new MainFrame().setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, 
                "Credenciais inválidas!", "Erro", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
}
