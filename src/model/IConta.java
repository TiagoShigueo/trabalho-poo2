package model;

public interface IConta {
    boolean deposita(double valor);
    boolean saca(double valor);
    Cliente getDono();
    int getNumero();
    double getSaldo();
    void remunera();
}
