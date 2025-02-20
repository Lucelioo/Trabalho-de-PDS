package views;

import javax.swing.*;
import java.awt.*;
import controllers.ProdutoController;
import models.ProdutoModel;

public class ProdutoDialog extends JDialog {
    private JTextField txtNome;
    private boolean sucesso = false;
    private Integer idProduto;

    public ProdutoDialog(Component owner, String title, Integer id) {
        super(SwingUtilities.getWindowAncestor(owner), title, ModalityType.APPLICATION_MODAL);
        this.idProduto = id;
        initializeUI();
        if (id != null) carregarDados();
    }

    private void initializeUI() {
        setSize(300, 150);
        setLocationRelativeTo(getOwner());

        JPanel panel = new JPanel(new GridLayout(2, 2, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panel.add(new JLabel("Nome:"));
        txtNome = new JTextField();
        panel.add(txtNome);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e -> salvar());
        panel.add(btnSalvar);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> dispose());
        panel.add(btnCancelar);

        add(panel);
    }

    private void carregarDados() {
        ProdutoModel.Produto p = ProdutoModel.listarProdutos().stream()
                .filter(prod -> prod.getId() == idProduto)
                .findFirst().orElse(null);
        
        if (p != null) {
            txtNome.setText(p.getNome());
        }
    }

    private void salvar() {
        String nome = txtNome.getText();
        
        if (idProduto == null) {
            // Pass single parameter to controller
            sucesso = ProdutoController.cadastrarProduto(nome);
        } else {
            // Pass two parameters to controller
            sucesso = ProdutoController.alterarProduto(idProduto, nome);
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