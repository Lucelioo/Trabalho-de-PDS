package views;
import javax.swing.*;
import java.awt.*;
import controllers.ClienteController;
import models.ClienteModel;

public class ClienteDialog extends JDialog {
    private JTextField txtNome;
    private JTextField txtTelefone;
    private JTextField txtEndereco;
    private JTextField txtCPF;
    private boolean sucesso = false;
    private Integer idCliente;

    public ClienteDialog(Component owner, String title, Integer id) {
        super(getParentWindow(owner), title, ModalityType.APPLICATION_MODAL);
        this.idCliente = id;
        initializeUI();
        if (id != null) carregarDados();
    }

    // Helper method to get parent window
    private static Window getParentWindow(Component parent) {
        return SwingUtilities.getWindowAncestor(parent);
    }

    private void initializeUI() {
        setSize(400, 300);
        setLocationRelativeTo(getOwner());

        JPanel panel = new JPanel(new GridLayout(5, 2, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panel.add(new JLabel("Nome:"));
        txtNome = new JTextField();
        panel.add(txtNome);

        panel.add(new JLabel("Telefone:"));
        txtTelefone = new JTextField();
        panel.add(txtTelefone);

        panel.add(new JLabel("Endereço:"));
        txtEndereco = new JTextField();
        panel.add(txtEndereco);

        panel.add(new JLabel("CPF:"));
        txtCPF = new JTextField();
        panel.add(txtCPF);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e -> salvar());
        panel.add(btnSalvar);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> dispose());
        panel.add(btnCancelar);

        add(panel);
    }

    private void carregarDados() {
        ClienteModel.Cliente c = ClienteModel.listarClientes().stream()
                .filter(cli -> cli.getId() == idCliente)
                .findFirst().orElse(null);
        
        if (c != null) {
            txtNome.setText(c.getNome());
            txtTelefone.setText(c.getTelefone());
            txtEndereco.setText(c.getEndereco());
            txtCPF.setText(c.getCpf());
        }
    }

    private void salvar() {
        String nome = txtNome.getText();
        String telefone = txtTelefone.getText();
        String endereco = txtEndereco.getText();
        String cpf = txtCPF.getText();

        if (idCliente == null) {
            sucesso = ClienteController.cadastrarCliente(nome, telefone, endereco, cpf);
        } else {
            sucesso = ClienteController.alterarCliente(idCliente, nome, telefone, endereco, cpf);
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