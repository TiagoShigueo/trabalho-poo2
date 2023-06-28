package model;

public class ContaInvestimento extends Conta {
	
    private double montanteMinimo; // conta investimento
    private double depositoMinimo; // conta investimento
    
    public ContaInvestimento(int numero, double depositoInicial, ContaTipo tipo, Cliente dono, double montanteMinimo, double depositoMinimo) {
    	super(numero, depositoInicial, ContaTipo.INVESTIMENTO, dono);
    	this.depositoMinimo = depositoMinimo;
    	this.montanteMinimo = montanteMinimo;
    }

    public ContaInvestimento() {
		// TODO Auto-generated constructor stub
	}

	@Override
    public boolean deposita(double valor) {
    	if(valor >= this.depositoMinimo) {
    		super.deposita(valor);
    		return true;
    	}else
    		return false;
    }

    @Override
    public boolean saca(double valor) {
    	if((valor + getSaldo()) >= montanteMinimo) {
    		super.saca(valor);
    		return true;
    	}else
    		return false;
    }

    @Override
    public void remunera() {
    	double saldo = getSaldo();
    	double remuneracao = saldo*0.2;
    	setSaldo(saldo + remuneracao);
    }

    public double getMontanteMinimo() {
        return montanteMinimo;
    }

    public void setMontanteMinimo(double montanteMinimo) {
        this.montanteMinimo = montanteMinimo;
    }

    public double getDepositoMinimo() {
        return depositoMinimo;
    }

    public void setDepositoMinimo(double depositoMinimo) {
        this.depositoMinimo = depositoMinimo;
    }

    @Override
    public void setTipo(ContaTipo tipo) throws Exception {
        if (tipo != ContaTipo.INVESTIMENTO) throw new Exception("Tipo inválido");
        this.setTipo(ContaTipo.INVESTIMENTO);
    }
}
