package org.example.finance;

public class PagamentoEspecie implements Pagamento{
    @Override
    public double calcular(double valor) {
        return valor * 0.92;
    }
}
