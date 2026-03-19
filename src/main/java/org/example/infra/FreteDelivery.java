package org.example.infra;

public class FreteDelivery implements Frete{
    @Override
    public double calcular(double valor) {
        return valor + 15;
    }
}
