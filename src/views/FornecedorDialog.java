package views;

import javax.swing.*;
import java.awt.*;
import controllers.FornecedorController;
import models.FornecedorModel;

public class FornecedorDialog extends JDialog {
    private JTextField txtNome;
    private JTextField txtContato;
    private JTextField txtProdutos;
    private boolean sucesso = false;
    private Integer idFornecedor;

    public FornecedorDialog(Component owner, String title, Integer id) {
        super(SwingUtilities.getWindowAncestor(owner), title, ModalityType.APPLICATION_MODAL);
        this.idFornecedor = id;
        initializeUI();
        if (id != null) carregarDados();
    }

    private void initializeUI() {
        setSize(400, 200);
        setLocationRelativeTo(getOwner());

        JPanel panel = new JPanel(new GridLayout(4, 2, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panel.add(new JLabel("Nome:"));
        txtNome = new JTextField();
        panel.add(txtNome);

        panel.add(new JLabel("Contato:"));
        txtContato = new JTextField();
        panel.add(txtContato);

        panel.add(new JLabel("Produtos Fornecidos:"));
        txtProdutos = new JTextField();
        panel.add(txtProdutos);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e -> salvar());
        panel.add(btnSalvar);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> dispose());
        panel.add(btnCancelar);

        add(panel);
    }

    private void carregarDados() {
        FornecedorModel.Fornecedor f = FornecedorModel.listarFornecedores().stream()
                .filter(forn -> forn.getId() == idFornecedor)
                .findFirst().orElse(null);
        
        if (f != null) {
            txtNome.setText(f.getNome());
            txtContato.setText(f.getContato());
            txtProdutos.setText(f.getProdutosFornecidos());
        }
    }

    private void salvar() {
        if (idFornecedor == null) {
            sucesso = FornecedorController.cadastrarFornecedor(
                txtNome.getText(),
                txtContato.getText(),
                txtProdutos.getText()
            );
        } else {
            sucesso = FornecedorController.alterarFornecedor(
                idFornecedor,
                txtNome.getText(),
                txtContato.getText(),
                txtProdutos.getText()
            );
        }

        if (sucesso) {
            JOptionPane.showMessageDialog(this, "Operação realizada com sucesso!");
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Erro na operação!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean showDialog() {
        setVisible(true);
        return sucesso;
    }
}