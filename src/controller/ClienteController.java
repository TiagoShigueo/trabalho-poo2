package controller;

import model.Cliente;
import model.dao.ClienteDao;

import java.util.List;

public class ClienteController {
    private ClienteDao clienteModel;

    public ClienteController() {
        clienteModel = new ClienteDao();
    }

    public void adicionarCliente(String nome, String sobrenome, String rg, String cpf, String endereco) {
        Cliente cliente = new Cliente(0, nome, sobrenome, rg, cpf, endereco);
        clienteModel.insere(cliente);
        
    }

    public void editarCliente(Cliente cliente) {
        clienteModel.altera(cliente);
    }

    public void removerCliente(int id) {
        clienteModel.remove(id);
    }

    public List<Cliente> obterTodosClientes() {
        return clienteModel.obterTodosClientes();
    }
}
