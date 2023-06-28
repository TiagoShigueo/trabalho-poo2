
package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Cliente;
import model.Conta;
import model.ContaCorrente;
import model.ContaInvestimento;
import model.ContaTipo;

public class ContaDao {
    
    String sql ="";
    
    public ContaCorrente getContaCorrente(int numero) throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;              
        try {
            ContaCorrente contaCorrente = new ContaCorrente();
            
            con = new ConnectionFactory().getConnection();
            sql = "select * from tb_conta inner join tb_cliente on tb_conta.idcliente = tb_cliente.idcliente where num_conta = ?";
            
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, numero);
            
            ResultSet rst = stmt.executeQuery();    
            
            if (rst.next()) {
                contaCorrente.setDepositoInicial(rst.getDouble("deposito_inicial"));
                contaCorrente.setLimite(rst.getDouble("limite"));
                contaCorrente.setDono( new Cliente(rst.getInt("id"),rst.getString("nome"), rst.getString("sobrenome"), rst.getString("rg"), rst.getString("cpf"), rst.getString("endereco")));
                contaCorrente.setNumero(rst.getInt("num_conta"));
                try {
					contaCorrente.setTipo(ContaTipo.valueOf(rst.getString("tipo")));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                contaCorrente.setSaldo(rst.getDouble("saldo"));
            }
            
            return contaCorrente;
        } catch(SQLException e) {
             throw new RuntimeException(e);
        } finally {
            stmt.close(); 
            con.close();
        }
    }

    public void insertContaCorrente(ContaCorrente contaCorrente) throws SQLException{
        Connection con = null;
        PreparedStatement stmt = null;
        try{
            con = new ConnectionFactory().getConnection();
            sql = "insert into contas (id_cliente , deposito_inicial, saldo , tipo, limite) values (?,?,?,?,?)";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, contaCorrente.getDono().getId());
            stmt.setDouble(2, contaCorrente.getDepositoInicial());
            stmt.setDouble(3, contaCorrente.getSaldo());
            stmt.setString(4, contaCorrente.getTipo().name());
            stmt.setDouble(5, contaCorrente.getLimite());
            stmt.execute();
        } finally {
            stmt.close();
            con.close();
        }
    }
    
    public void insertContaInvestimento(ContaInvestimento contaInvestimento) throws SQLException{
        Connection con = null;
        PreparedStatement stmt = null;
        try{
            con = new ConnectionFactory().getConnection();
            sql = "insert into contas (id_cliente, deposito_inicial, saldo, tipo, montante_minimo, deposito_minimo) values (?,?,?,?,?,?)";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, contaInvestimento.getDono().getId());
            stmt.setDouble(2, contaInvestimento.getDepositoInicial());
            stmt.setDouble(3, contaInvestimento.getSaldo());
            stmt.setString(4, contaInvestimento.getTipo().name());
            stmt.setDouble(5, contaInvestimento.getMontanteMinimo());
            stmt.setDouble(6, contaInvestimento.getDepositoMinimo());
            stmt.execute();
        }finally {
            stmt.close();
            con.close();
        }
    }
    
    public void update(Conta conta) throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        try{
            con = new ConnectionFactory().getConnection();
            sql = "update contas set saldo = ? where numero = ?";
            stmt = con.prepareStatement(sql);
            stmt.setDouble(1, conta.getSaldo());
            stmt.setInt(2, conta.getNumero());
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            stmt.close();
            con.close();
        }
    }
    
    public void delete(Conta conta) throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = new ConnectionFactory().getConnection();
            int numConta = conta.getNumero();
            sql = "delete from contas where numero  = ?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, numConta);
            stmt.executeUpdate();

        } catch(SQLException e) {
             throw new RuntimeException(e);
        } finally {
            stmt.close();
            con.close();
        }
    }
    
    public int getNumConta(int idCliente) throws SQLException{
        Connection con = null;
        PreparedStatement stmt = null;
        int numConta = 0;
        try{
            con = new ConnectionFactory().getConnection();
            sql= "SELECT numero FROM contas WHERE tipo = ? AND id_cliente = ?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, ContaTipo.CORRENTE.name());
            stmt.setInt(2, idCliente);
            ResultSet rs = stmt.executeQuery();
            rs = stmt.executeQuery();
            while(rs.next()){
                numConta = rs.getInt("numero");
            }
            return numConta;
        } catch(SQLException e) {
             throw new RuntimeException(e);
        } finally {
            stmt.close();
            con.close();
        }
    }
    
    public Conta getContaPorCpf(String cpf) throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;              
        try {
            Conta conta = null;
            con = new ConnectionFactory().getConnection();
            sql = "select * from contas inner join clientes on contas.id_cliente = clientes.id where cpf = ?";
            
            stmt = con.prepareStatement(sql);
            stmt.setString(1, cpf);
            
            ResultSet rst = stmt.executeQuery();    
            
            if (rst.next()) {
                if (rst.getString("tipo").equals(ContaTipo.CORRENTE.name())) {
                    ContaCorrente contaCorrente = new ContaCorrente();
                    contaCorrente.setNumero(rst.getInt("numero"));
                    contaCorrente.setSaldo(rst.getDouble("saldo"));
                    try {
                    	contaCorrente.setTipo(ContaTipo.valueOf(rst.getString("tipo")));
					} catch (Exception e) {
						e.printStackTrace();
					}
                    contaCorrente.setLimite(rst.getDouble("limite"));
                    conta = contaCorrente;
                } else {
                    ContaInvestimento contaInvestimento = new ContaInvestimento();
                    contaInvestimento.setNumero(rst.getInt("num_conta"));
                    contaInvestimento.setSaldo(rst.getDouble("saldo"));
                    try {
                    	contaInvestimento.setTipo(ContaTipo.valueOf(rst.getString("tipo")));
					} catch (Exception e) {
						e.printStackTrace();
					}
                    contaInvestimento.setDepositoMinimo(rst.getDouble("deposito_minimo"));
                    contaInvestimento.setMontanteMinimo(rst.getDouble("montante_minimo"));
                    conta = contaInvestimento;
                }
            }
            
            return conta;

        } catch(SQLException e) {
             throw new RuntimeException(e);
        } finally {
            stmt.close(); 
            con.close();
        }

    }
    
    public void updateSaldo(Conta conta) throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        try{
            con = new ConnectionFactory().getConnection();
            sql = "update contas set saldo = ? where numero = ?";
            stmt = con.prepareStatement(sql);
            stmt.setDouble(1, conta.getSaldo());
            stmt.setInt(2, conta.getNumero());
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            stmt.close();
            con.close();
        }
    }

}

