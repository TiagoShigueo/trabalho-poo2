package model;


public abstract class Conta implements ContaI {
    private int numero;
    private double depositoInicial;
    private double saldo;
    private ContaTipo tipo;
    private Cliente dono;


    public Conta(int numero, double depositoInicial, ContaTipo tipo, Cliente dono) {
        this.numero = numero;
        this.depositoInicial = depositoInicial;
        this.tipo = tipo;
        this.dono = dono;
    }

    public Conta() { }

    @Override
    public boolean deposita(double valor) {
    	if(valor > 0) {
    		this.saldo += valor;
    		return true;
    	}else {
    		return false;
    		
    	}
    }

    @Override
    public boolean saca(double valor) {
    	if(valor > 0) {
    		this.saldo -= valor;
    		return true;
    	}else {
    		//
    		return false;
    	}
    }

    @Override
    public Cliente getDono() {
        return dono;
    }

    public void setDono(Cliente dono) {
        this.dono = dono;
    }

    @Override
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    @Override
    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }


    public ContaTipo getTipo() {
        return tipo;
    }
    
    public double getDepositoInicial() {
        return depositoInicial;
    }

    public void setDepositoInicial(double depositoInicial) {
        this.depositoInicial = depositoInicial;
    }

    public void setTipo(ContaTipo tipo) throws Exception {
        this.tipo = tipo;
    }


}
