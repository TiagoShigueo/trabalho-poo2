package model.dao;

import model.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDao {
    public void insere(Cliente cliente) {
        String sql = "INSERT INTO clientes (cpf, rg, nome, sobrenome, endereco) " +
                "VALUES (?, ?, ?, ?, ?)";
        Connection connection = null;
        PreparedStatement stmt = null;
        try {
            connection = new ConnectionFactory().getConnection();
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, cliente.getCpf());
            stmt.setString(2, cliente.getRg());
            stmt.setString(3, cliente.getNome());
            stmt.setString(4, cliente.getSobrenome());
            stmt.setString(5, cliente.getEndereco());
            stmt.execute();

            stmt.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Cliente> listar() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT id, cpf, rg, nome, sobrenome, endereco FROM clientes";
        Connection connection = null;
        Statement stmt = null;
        ResultSet resultSet = null;
        try {
            connection = new ConnectionFactory().getConnection();
            stmt = connection.createStatement();
            resultSet = stmt.executeQuery(sql);
            while (resultSet.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(resultSet.getInt("id"));
                cliente.setCpf(resultSet.getString("cpf"));
                cliente.setRg(resultSet.getString("rg"));
                cliente.setNome(resultSet.getString("nome"));
                cliente.setSobrenome(resultSet.getString("sobrenome"));
                cliente.setEndereco(resultSet.getString("endereco"));

                clientes.add(cliente);
            }

            stmt.close();
            connection.close();

            return clientes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void altera(Cliente cliente) {
        String sql = "UPDATE clientes " +
                "SET cpf=?, rg=?, nome=?, sobrenome=?, endereco=? " +
                "WHERE clientes.id=?";
        Connection connection = null;
        PreparedStatement stmt = null;
        try {
            connection = new ConnectionFactory().getConnection();
            stmt = connection.prepareStatement(sql);

            stmt.setString(1, cliente.getCpf());
            stmt.setString(2, cliente.getRg());
            stmt.setString(3, cliente.getNome());
            stmt.setString(4, cliente.getSobrenome());
            stmt.setString(5, cliente.getEndereco());
            stmt.setLong(6, cliente.getId());
            stmt.execute();

            stmt.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void remove(Cliente cliente) {
        String sql = "DELETE FROM clientes " +
                "WHERE id=?";
        Connection connection = null;
        PreparedStatement stmt = null;
        try {
            connection = new ConnectionFactory().getConnection();
            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, cliente.getId());
            stmt.execute();

            stmt.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public Cliente getCliente(int idCliente) throws SQLException{
        Connection con = null;
        PreparedStatement stmt = null;
        
        try {
            Cliente cliente = new Cliente();
            con = new ConnectionFactory().getConnection();
            String sql = "select * from clientes where id=?";
            
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, idCliente);
            
            ResultSet rst = stmt.executeQuery();    
            cliente = new Cliente();

            if (rst.next()) {
                cliente.setId(idCliente);
                cliente.setNome(rst.getString("nome"));
                cliente.setSobrenome(rst.getString("sobrenome"));
                cliente.setRg(rst.getString("rg"));
                cliente.setCpf(rst.getString("cpf"));
                cliente.setEndereco(rst.getString("endereco"));
            }
            return cliente;
        } catch(SQLException e) {
             throw new RuntimeException(e);
        } finally {
            stmt.close(); 
            con.close();
        }
    }
    
    public Cliente clienteDados(String nome, String sobrenome) throws SQLException{
        int idCliente = 0;
        String cpf = null,nomeCliente = null, sobrenomeCliente = null, rg = null, endereco = null;
        Connection con = null;
        PreparedStatement stmt = null;
            try {
                con = new ConnectionFactory().getConnection();

                String query = "SELECT * FROM clientes WHERE nome = ? AND sobrenome = ?";
                stmt = con.prepareStatement(query);
                stmt.setString(1, nome);
                stmt.setString(2, sobrenome);
                
                ResultSet rs = stmt.executeQuery();
                while(rs.next()){
                    idCliente = rs.getInt("idcliente");
                    cpf = rs.getString("cpf");
                    nomeCliente = rs.getString("nome");
                    sobrenomeCliente = rs.getString("sobrenome");
                    rg = rs.getString("rg");
                    endereco = rs.getString("endereco");
                }

                Cliente cliente = new Cliente(idCliente, nomeCliente, sobrenomeCliente, rg, cpf, endereco);
                return cliente;
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            } finally {
                stmt.close(); 
                con.close();
            }
    }

}