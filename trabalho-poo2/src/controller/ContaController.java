package controller;

import java.sql.SQLException;
import javax.swing.JOptionPane;

import model.Conta;
import model.ContaCorrente;
import model.ContaInvestimento;
import model.dao.ContaDao;
import view.ContaView;


public class ContaController {
    
    public void criarContaCorrente(ContaCorrente contaCorrente, ContaView view) throws SQLException {       
         try {
             ContaDao contaDao = new ContaDao();
             contaDao.insertContaCorrente(contaCorrente);
         } catch (SQLException e) {
        	 
         }
    }
    
    public void criarContaInvestimento(ContaInvestimento contaInvestimento, ContaView view) throws SQLException{
        try{
            ContaDao contaDao = new ContaDao();
            contaDao.insertContaInvestimento(contaInvestimento);
        } catch (SQLException e) {           

        }
        
    }
    
        
    public Conta buscarContaPorCpf(String cpf) {
        try {
            ContaDao contaDao = new ContaDao();
            return contaDao.getContaPorCpf(cpf);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void remunerar(Conta conta) {
        conta.remunera();
        updateSaldo(conta);
    }
    
    public boolean sacar(Conta conta, double valor) {
        if (!conta.saca(valor))
            return false;
        conta.setSaldo(conta.getSaldo() - valor);
        updateSaldo(conta);
        return true;
    }

    public boolean depositar(Conta conta, double valor) {
        if (!conta.deposita(valor))
            return false;
        conta.setSaldo(conta.getSaldo() + valor);
        updateSaldo(conta);
        return true;
    }
    
    public void updateSaldo(Conta conta) {
        try {
        	ContaDao contaDao = new ContaDao();
        	contaDao.updateSaldo(conta);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
}