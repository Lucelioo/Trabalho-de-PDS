package views;

import controllers.HistoricoSaidaController;
import models.HistoricoSaidaModel;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class HistoricoSaidaPanel extends JPanel {
    private final JTable tabelaHistorico;
    private final DefaultTableModel tableModel;

    public HistoricoSaidaPanel() {
        setLayout(new BorderLayout());
        
        // Table setup
        tableModel = new DefaultTableModel(
            new Object[]{"ID Histórico", "ID Produto", "Nome Produto", "Data Saída"}, 
            0
        );
        tabelaHistorico = new JTable(tableModel);
        
        // Toolbar
        JToolBar toolBar = new JToolBar();
        JButton btnAtualizar = new JButton("Atualizar");
        btnAtualizar.addActionListener(e -> atualizarTabela());
        
        toolBar.add(btnAtualizar);
        
        add(toolBar, BorderLayout.NORTH);
        add(new JScrollPane(tabelaHistorico), BorderLayout.CENTER);
        
        atualizarTabela();
    }

    private void atualizarTabela() {
        tableModel.setRowCount(0);
        HistoricoSaidaController.listarHistorico().forEach(registro -> 
            tableModel.addRow(new Object[]{
                registro.getIdHistorico(),
                registro.getIdProduto(),
                registro.getNomeProduto(),
                registro.getDataSaida()
            })
        );
    }
}