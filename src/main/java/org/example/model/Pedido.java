package org.example.model;

public class Pedido {
    private Prato prato;
    private int quantidade;

    public Pedido(Prato prato, int quantidade){
        this.prato = prato;
        this.quantidade = quantidade;
    }
    public Prato getPrato(){
        return prato;
    }
    public int getQuantidade(){
        return quantidade;
    }
}
