package org.example.finance;

public class PagamentoCredito implements Pagamento{
    @Override
    public double calcular(double valor) {
        return valor * 1.05;
    }
}
