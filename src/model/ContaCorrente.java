package model;

public class ContaCorrente extends Conta {
	
    private double limite;
    public ContaCorrente(int numero, double depositoInicial, ContaTipo tipo, Cliente dono, double limite) {
    	super(numero, depositoInicial, ContaTipo.CORRENTE, dono);
    	this.limite = limite;
    }

    public ContaCorrente() {
	}

	@Override
    public boolean saca(double valor) {
    	double saldo = getSaldo();
    	if(valor > (saldo + limite)) {
    		setSaldo(saldo - valor);
    		return true;
    	}else {
    		return false;
    	}
    }

    @Override
    public void remunera() {
    	double saldo = getSaldo();
    	double remuneracao = saldo*0.1;
    	setSaldo(saldo + remuneracao);
    	
    }
    
    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    @Override
    public void setTipo(ContaTipo tipo) throws Exception {
        if (tipo != ContaTipo.CORRENTE) throw new Exception("Tipo inválido");
    }

}
