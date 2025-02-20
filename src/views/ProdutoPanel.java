package views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import controllers.ProdutoController;
import models.HistoricoSaidaModel;
import models.ProdutoModel;
import models.ProdutoModel.Produto;
import utils.InventoryIterator;

public class ProdutoPanel extends JPanel {
    private JTable tabelaProdutos;
    private DefaultTableModel tableModel;

    public ProdutoPanel() {
        setLayout(new BorderLayout());

        
        // Toolbar
        JToolBar toolBar = new JToolBar();
        JButton btnNovo = new JButton("Novo");
        JButton btnEditar = new JButton("Editar");
        JButton btnExcluir = new JButton("Excluir");
        JButton btnAtualizar = new JButton("Atualizar");
        JButton btnRegistrarSaida = new JButton("Registrar Saída");

        toolBar.add(btnNovo);
        toolBar.add(btnEditar);
        toolBar.add(btnExcluir);
        toolBar.addSeparator();
        toolBar.add(btnAtualizar);
        toolBar.add(btnRegistrarSaida);

        add(toolBar, BorderLayout.NORTH);

        // Table
        tableModel = new DefaultTableModel(
            new Object[]{"ID", "Nome do Produto"}, 0
        );
        tabelaProdutos = new JTable(tableModel);
        add(new JScrollPane(tabelaProdutos), BorderLayout.CENTER);

        // Load data
        atualizarTabela();

        // Event handlers
        btnNovo.addActionListener(e -> novoProduto());
        btnEditar.addActionListener(e -> editarProduto());
        btnExcluir.addActionListener(e -> excluirProduto());
        btnAtualizar.addActionListener(e -> atualizarTabela());
        btnRegistrarSaida.addActionListener(e -> registrarSaida());
    }

    private void atualizarTabela() {
    tableModel.setRowCount(0);
    InventoryIterator<Produto> iterator = ProdutoModel.getIterator();
    
    while(iterator.hasNext()) {
        Produto produto = iterator.next();
        tableModel.addRow(new Object[]{
            produto.getId(), 
            produto.getNome()
        });
    }
    }

    private void novoProduto() {
        ProdutoDialog dialog = new ProdutoDialog(this, "Novo Produto", null);
        if (dialog.showDialog()) {
            atualizarTabela();
        }
    }

    private void editarProduto() {
        int row = tabelaProdutos.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um produto!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int id = (int) tableModel.getValueAt(row, 0);
        ProdutoDialog dialog = new ProdutoDialog(this, "Editar Produto", id);
        if (dialog.showDialog()) {
            atualizarTabela();
        }
    }

    private void excluirProduto() {
        int row = tabelaProdutos.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um produto!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int id = (int) tableModel.getValueAt(row, 0);
        if (ProdutoController.excluirProduto(id)) {
            JOptionPane.showMessageDialog(this, "Produto excluído com sucesso!");
            atualizarTabela();
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao excluir produto!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void registrarSaida() {
        int selectedRow = tabelaProdutos.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, 
                "Selecione um produto para registrar saída!", 
                "Aviso", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int id = (int) tableModel.getValueAt(selectedRow, 0);
        String nome = (String) tableModel.getValueAt(selectedRow, 1);
        
        if (ProdutoController.registrarSaida(id, nome)) {
            atualizarTabela();
            JOptionPane.showMessageDialog(this, 
                "Saída registrada com sucesso!", 
                "Sucesso", 
                JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, 
                "Erro ao registrar saída!", 
                "Erro", 
                JOptionPane.ERROR_MESSAGE);
        }
}

    public static boolean registrarSaida(int idProduto, String nomeProduto) {
        return HistoricoSaidaModel.registrarSaida(idProduto, nomeProduto);
}
}