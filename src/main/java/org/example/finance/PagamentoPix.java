package org.example.finance;

public class PagamentoPix implements Pagamento {
    @Override
    public double calcular(double valor) {
        return valor * 0.90;
    }
}
