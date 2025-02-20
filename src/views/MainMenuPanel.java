package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuPanel extends JPanel {
    public MainMenuPanel(JFrame parentFrame) {
        setLayout(new GridLayout(4, 1, 10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton btnClientes = new JButton("Gerenciar Clientes");
        JButton btnProdutos = new JButton("Gerenciar Produtos");
        JButton btnFornecedores = new JButton("Gerenciar Fornecedores");
        JButton btnSair = new JButton("Sair do Sistema");

        // Style buttons
        Font buttonFont = new Font("Arial", Font.PLAIN, 18);
        Dimension buttonSize = new Dimension(300, 80);
        for (JButton btn : new JButton[]{btnClientes, btnProdutos, btnFornecedores, btnSair}) {
            btn.setFont(buttonFont);
            btn.setPreferredSize(buttonSize);
        }

        add(btnClientes);
        add(btnProdutos);
        add(btnFornecedores);
        add(btnSair);

        // Add action listeners
        btnClientes.addActionListener(e -> {
            parentFrame.dispose();
            new ManagementFrame("Clientes", ClientePanel.class).setVisible(true);
        });

        btnProdutos.addActionListener(e -> {
            parentFrame.dispose();
            new ManagementFrame("Produtos", ProdutoPanel.class).setVisible(true);
        });

        btnFornecedores.addActionListener(e -> {
            parentFrame.dispose();
            new ManagementFrame("Fornecedores", FornecedorPanel.class).setVisible(true);
        });

        btnSair.addActionListener(e -> System.exit(0));
    }

    private JButton createMenuButton(String text, ActionListener listener) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Arial", Font.BOLD, 16));
        btn.setPreferredSize(new Dimension(250, 60));
        btn.addActionListener(listener);
        return btn;
    }

    private void showManagementPanel(JFrame parentFrame, JPanel panel) {
        parentFrame.getContentPane().removeAll();
        parentFrame.add(panel);
        parentFrame.revalidate();
        parentFrame.repaint();
    }
}