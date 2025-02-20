package views;

import javax.swing.*;
import java.awt.*;

public class ManagementFrame extends JFrame {
    public ManagementFrame(String title, Class<? extends JPanel> panelClass) {
        setTitle(title);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        try {
            add(panelClass.getDeclaredConstructor().newInstance());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar o painel: " + e.getMessage());
        }
    }
}