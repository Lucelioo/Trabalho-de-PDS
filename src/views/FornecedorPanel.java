package views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import controllers.FornecedorController;
import models.FornecedorModel;

public class FornecedorPanel extends JPanel {
    private JTable tabelaFornecedores;
    private DefaultTableModel tableModel;

    public FornecedorPanel() {
        setLayout(new BorderLayout());
        
        // Toolbar
        JToolBar toolBar = new JToolBar();
        JButton btnNovo = new JButton("Novo");
        JButton btnEditar = new JButton("Editar");
        JButton btnExcluir = new JButton("Excluir");
        JButton btnAtualizar = new JButton("Atualizar");

        toolBar.add(btnNovo);
        toolBar.add(btnEditar);
        toolBar.add(btnExcluir);
        toolBar.addSeparator();
        toolBar.add(btnAtualizar);

        add(toolBar, BorderLayout.NORTH);

        // Table
        tableModel = new DefaultTableModel(
            new Object[]{"ID", "Nome", "Contato", "Produtos Fornecidos"}, 0
        );
        tabelaFornecedores = new JTable(tableModel);
        add(new JScrollPane(tabelaFornecedores), BorderLayout.CENTER);

        // Load data
        atualizarTabela();

        // Event handlers
        btnNovo.addActionListener(e -> novoFornecedor());
        btnEditar.addActionListener(e -> editarFornecedor());
        btnExcluir.addActionListener(e -> excluirFornecedor());
        btnAtualizar.addActionListener(e -> atualizarTabela());
    }

    private void atualizarTabela() {
        tableModel.setRowCount(0);
        List<FornecedorModel.Fornecedor> fornecedores = FornecedorModel.listarFornecedores();
        for (FornecedorModel.Fornecedor f : fornecedores) {
            tableModel.addRow(new Object[]{
                f.getId(),
                f.getNome(),
                f.getContato(),
                f.getProdutosFornecidos()
            });
        }
    }

    private void novoFornecedor() {
        FornecedorDialog dialog = new FornecedorDialog(this, "Novo Fornecedor", null);
        if (dialog.showDialog()) {
            atualizarTabela();
        }
    }

    private void editarFornecedor() {
        int row = tabelaFornecedores.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um fornecedor!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int id = (int) tableModel.getValueAt(row, 0);
        FornecedorDialog dialog = new FornecedorDialog(this, "Editar Fornecedor", id);
        if (dialog.showDialog()) {
            atualizarTabela();
        }
    }

    private void excluirFornecedor() {
        int row = tabelaFornecedores.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um fornecedor!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int id = (int) tableModel.getValueAt(row, 0);
        if (FornecedorController.excluirFornecedor(id)) {
            JOptionPane.showMessageDialog(this, "Fornecedor excluído com sucesso!");
            atualizarTabela();
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao excluir fornecedor!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}