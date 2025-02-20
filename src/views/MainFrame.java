package views;

import views.HistoricoSaidaPanel;
import javax.swing.*;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {
    public MainFrame() {
        setTitle("Sistema de Gerenciamento");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Add main menu panel (unchanged)
        add(new MainMenuPanel(this));
        
        // Configure window
        setJMenuBar(createMenuBar());
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        
        // New Reports menu
        JMenu mnRelatorios = new JMenu("Relatórios");
        JMenuItem miHistorico = new JMenuItem("Histórico de Saída");
        miHistorico.addActionListener(this::showHistorico);
        mnRelatorios.add(miHistorico);
        
        // Existing Help menu
        JMenu mnAjuda = new JMenu("Ajuda");
        JMenuItem miSobre = new JMenuItem("Sobre");
        miSobre.addActionListener(e -> showAboutDialog());
        
        menuBar.add(mnRelatorios);
        mnAjuda.add(miSobre);
        menuBar.add(mnAjuda);
        
        return menuBar;
    }

    private void showHistorico(ActionEvent e) {
        JDialog historicoDialog = new JDialog(this, "Histórico de Saída", false);
        historicoDialog.setContentPane(new HistoricoSaidaPanel());
        historicoDialog.setSize(600, 400);
        historicoDialog.setLocationRelativeTo(this);
        historicoDialog.setVisible(true);
    }

    private void showAboutDialog() {
        JOptionPane.showMessageDialog(this,
            "Sistema de Gerenciamento v1.0\nDesenvolvido por Sua Empresa",
            "Sobre",
            JOptionPane.INFORMATION_MESSAGE);
    }
}