package org.example.model;

public class Prato {
    private String nome;
    private double preco;
    private int estoque;

    public Prato(String nome, double preco, int estoque){
        this.nome = nome;
        this.preco = preco;
        this.estoque = estoque;
    }

    public String getNome() {return nome;}
    public double getPreco() {return preco;}
    public int getEstoque() {return estoque;}

    public void setEstoque(int estoque){
        this.estoque = estoque;
    }
}
