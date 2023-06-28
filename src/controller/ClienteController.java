package controller;

import model.Cliente;
import model.ClienteModel;

import java.util.List;

public class ClienteController {
    private ClienteModel clienteModel;

    public ClienteController() {
        clienteModel = new ClienteModel();
    }

    public void adicionarCliente(String nome, String sobrenome, String rg, String cpf, String endereco) {
        Cliente cliente = new Cliente(0, nome, sobrenome, rg, cpf, endereco);
        clienteModel.adicionarCliente(cliente);
        
    }

    public void editarCliente(Cliente cliente) {
        clienteModel.editarCliente(cliente);
    }

    public void removerCliente(int id) {
        clienteModel.removerCliente(id);
    }

    public List<Cliente> obterTodosClientes() {
        return clienteModel.obterTodosClientes();
    }
}
