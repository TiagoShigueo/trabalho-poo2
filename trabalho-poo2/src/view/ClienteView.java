package view;

import controller.ClienteController;
import model.Cliente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ClienteView {
    private JFrame frame;
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField txtNome;
    private JTextField txtSobrenome;
    private JTextField txtRg;
    private JTextField txtCpf;
    private JTextField txtEndereco;
    private ClienteController clienteController;

    public ClienteView() {
        clienteController = new ClienteController();

        frame = new JFrame();
        frame.setTitle("Cadastro de Clientes");
        frame.setBounds(100, 100, 600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(10, 20, 30, 20);
        frame.getContentPane().add(lblNome);

        JLabel lblSobrenome = new JLabel("Sobrenome:");
        lblSobrenome.setBounds(10, 50, 60, 20);
        frame.getContentPane().add(lblSobrenome);

        JLabel lblRg = new JLabel("RG:");
        lblRg.setBounds(10, 80, 30, 20);
        frame.getContentPane().add(lblRg);

        JLabel lblCpf = new JLabel("CPF:");
        lblCpf.setBounds(10, 110, 30, 20);
        frame.getContentPane().add(lblCpf);

        JLabel lblEndereco = new JLabel("Endereço:");
        lblEndereco.setBounds(10, 140, 60, 20);
        frame.getContentPane().add(lblEndereco);

        txtNome = new JTextField();
        txtNome.setBounds(90, 20, 200, 20);
        frame.getContentPane().add(txtNome);
        txtNome.setColumns(10);

        txtSobrenome = new JTextField();
        txtSobrenome.setBounds(90, 50, 200, 20);
        frame.getContentPane().add(txtSobrenome);
        txtSobrenome.setColumns(10);

        txtRg = new JTextField();
        txtRg.setBounds(90, 80, 100, 20);
        frame.getContentPane().add(txtRg);
        txtRg.setColumns(10);

        txtCpf = new JTextField();
        txtCpf.setBounds(90, 110, 100, 20);
        frame.getContentPane().add(txtCpf);
        txtCpf.setColumns(10);

        txtEndereco = new JTextField();
        txtEndereco.setBounds(90, 140, 200, 20);
        frame.getContentPane().add(txtEndereco);
        txtEndereco.setColumns(10);

        JButton btnAdicionar = new JButton("Adicionar");
        btnAdicionar.setBounds(10, 180, 100, 23);
        frame.getContentPane().add(btnAdicionar);
        btnAdicionar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                adicionarCliente();
            }
        });

        JButton btnEditar = new JButton("Editar");
        btnEditar.setBounds(110, 180, 80, 23);
        frame.getContentPane().add(btnEditar);
        btnEditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editarCliente();
            }
        });

        JButton btnRemover = new JButton("Remover");
        btnRemover.setBounds(200, 180, 100, 23);
        frame.getContentPane().add(btnRemover);
        btnRemover.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removerCliente();
            }
        });

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 220, 560, 130);
        frame.getContentPane().add(scrollPane);

        table = new JTable();
        scrollPane.setViewportView(table);
        tableModel = new DefaultTableModel(
                new Object[][]{},
                new String[]{"Nome", "Sobrenome", "RG", "CPF", "Endereço"}
        );
        table.setModel(tableModel);

        preencherTabela();

        frame.setVisible(true);
    }

    // Método para adicionar um cliente
    private void adicionarCliente() {
        String nome = txtNome.getText();
        String sobrenome = txtSobrenome.getText();
        String rg = txtRg.getText();
        String cpf = txtCpf.getText();
        String endereco = txtEndereco.getText();

        clienteController.adicionarCliente(nome, sobrenome, rg, cpf, endereco);

        // Limpa os campos de texto
        limparCampos();

        // Atualiza a tabela com os clientes atualizados
        preencherTabela();
    }

    // Método para editar um cliente
    private void editarCliente() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(frame, "Selecione um cliente para editar.");
            return;
        }

        String nome = txtNome.getText();
        String sobrenome = txtSobrenome.getText();
        String rg = txtRg.getText();
        String cpf = txtCpf.getText();
        String endereco = txtEndereco.getText();

        int id = Integer.parseInt(tableModel.getValueAt(selectedRow, 0).toString());
        Cliente cliente = new Cliente(id, nome, sobrenome, rg, cpf, endereco);
        clienteController.editarCliente(cliente);

        // Limpa os campos de texto
        limparCampos();

        // Atualiza a tabela com os clientes atualizados
        preencherTabela();
    }

    // Método para remover um cliente
    private void removerCliente() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(frame, "Selecione um cliente para remover.");
            return;
        }

        int id = Integer.parseInt(tableModel.getValueAt(selectedRow, 0).toString());
        clienteController.removerCliente(id);

        // Limpa os campos de texto
        limparCampos();

        // Atualiza a tabela com os clientes atualizados
        preencherTabela();
    }

    // Método para preencher a tabela com os clientes existentes
    private void preencherTabela() {
        // Limpa a tabela
        tableModel.setRowCount(0);

        // Obtém todos os clientes
        List<Cliente> clientes = clienteController.obterTodosClientes();

        // Preenche a tabela com os clientes
        for (Cliente cliente : clientes) {
            Object[] rowData = {cliente.getId(), cliente.getNome(), cliente.getSobrenome(), cliente.getRg(), cliente.getCpf(), cliente.getEndereco()};
            tableModel.addRow(rowData);
        }
    }

    // Método para limpar os campos de texto
    private void limparCampos() {
        txtNome.setText("");
        txtSobrenome.setText("");
        txtRg.setText("");
        txtCpf.setText("");
        txtEndereco.setText("");
    }
}
