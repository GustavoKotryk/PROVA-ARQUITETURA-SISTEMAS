package org.example.service;

import org.example.finance.Pagamento;
import org.example.infra.Frete;
import org.example.model.Pedido;
import org.example.model.Prato;

import java.util.List;

public class VendasService {
    private List<Prato> pratos;
    private Pagamento pagamento;
    private Frete frete;

    public VendasService(List<Prato> pratos, Pagamento pagamento, Frete frete) {
        this.pratos = pratos;
        this.pagamento = pagamento;
        this.frete = frete;
    }

    public double vender(Pedido pedido) {
        Prato p = pedido.getPrato();
        int qtd = pedido.getQuantidade();

        if(p.getEstoque() < qtd){
            System.out.println("Estoque insuficiente");
            return 0;
        }
        double valor = p.getPreco() * qtd;
        valor = frete.calcular(valor);
        valor = pagamento.calcular(valor);

        p.setEstoque(p.getEstoque() - qtd);
        return valor;
    }
}
