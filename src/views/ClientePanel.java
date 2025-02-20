package views;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import controllers.ClienteController;
import models.ClienteModel;

public class ClientePanel extends JPanel {
    private JTable tabelaClientes;
    private DefaultTableModel tableModel;

    public ClientePanel() {
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
            new Object[]{"ID", "Nome", "Telefone", "Endereço", "CPF"}, 0
        );
        tabelaClientes = new JTable(tableModel);
        add(new JScrollPane(tabelaClientes), BorderLayout.CENTER);

        // Load data
        atualizarTabela();

        // Event handlers
        btnNovo.addActionListener(e -> novoCliente());
        btnEditar.addActionListener(e -> editarCliente());
        btnExcluir.addActionListener(e -> excluirCliente());
        btnAtualizar.addActionListener(e -> atualizarTabela());
    }

    private void atualizarTabela() {
        tableModel.setRowCount(0);
        List<ClienteModel.Cliente> clientes = ClienteModel.listarClientes();
        for (ClienteModel.Cliente c : clientes) {
            tableModel.addRow(new Object[]{
                c.getId(), 
                c.getNome(), 
                c.getTelefone(), 
                c.getEndereco(), 
                c.getCpf()
            });
        }
    }

    private void novoCliente() {
        ClienteDialog dialog = new ClienteDialog(this, "Novo Cliente", null);
        if (dialog.showDialog()) {
            atualizarTabela();
        }
    }

    private void editarCliente() {
        int row = tabelaClientes.getSelectedRow();
        if (row == -1) return;
    
        int id = (int) tableModel.getValueAt(row, 0);
        ClienteDialog dialog = new ClienteDialog(this, "Editar Cliente", id);
        if (dialog.showDialog()) {
            atualizarTabela();
        }
    }

    private void excluirCliente() {
        int row = tabelaClientes.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um cliente!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int id = (int) tableModel.getValueAt(row, 0);
        if (ClienteController.excluirCliente(id)) {
            JOptionPane.showMessageDialog(this, "Cliente excluído com sucesso!");
            atualizarTabela();
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao excluir cliente!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}