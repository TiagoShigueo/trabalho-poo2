package model;

import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteModel {
    public void adicionarCliente(Cliente cliente) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO clientes (nome, sobrenome, rg, cpf, endereco) VALUES (?, ?, ?, ?, ?)")) {

            statement.setString(1, cliente.getNome());
            statement.setString(2, cliente.getSobrenome());
            statement.setString(3, cliente.getRg());
            statement.setString(4, cliente.getCpf());
            statement.setString(5, cliente.getEndereco());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editarCliente(Cliente cliente) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE clientes SET nome=?, sobrenome=?, rg=?, cpf=?, endereco=? WHERE id=?")) {

            statement.setString(1, cliente.getNome());
            statement.setString(2, cliente.getSobrenome());
            statement.setString(3, cliente.getRg());
            statement.setString(4, cliente.getCpf());
            statement.setString(5, cliente.getEndereco());
            statement.setInt(6, cliente.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removerCliente(int id) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM clientes WHERE id=?")) {

            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Cliente> obterTodosClientes() {
        List<Cliente> clientes = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM clientes")) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                String sobrenome = resultSet.getString("sobrenome");
                String rg = resultSet.getString("rg");
                String cpf = resultSet.getString("cpf");
                String endereco = resultSet.getString("endereco");

                Cliente cliente = new Cliente(id, nome, sobrenome, rg, cpf, endereco);
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clientes;
    }
}
