package org.example.view;

import org.example.finance.Pagamento;
import org.example.finance.PagamentoCredito;
import org.example.finance.PagamentoEspecie;
import org.example.finance.PagamentoPix;
import org.example.infra.Frete;
import org.example.infra.FreteDelivery;
import org.example.infra.FretePresencial;
import org.example.model.Pedido;
import org.example.model.Prato;
import org.example.service.FuncionarioService;
import org.example.service.VendasService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ViewMenu {
    public void iniciar() {
        Scanner sc = new Scanner(System.in);

        List<Prato> pratos = new ArrayList<>();
        pratos.add(new Prato("Feijoada", 45, 10));
        pratos.add(new Prato("Moqueca", 65, 5));

        FuncionarioService funcionarioService = new FuncionarioService();
        funcionarioService.contratar("Ana", "Caixa", 2000);

        int op = -1;

        while (op != 0){
            System.out.println("\n==========MENU=============");
            System.out.println("\n" +
                    "1- Gerenciar Funcionarios\n" +
                    "2- Realizar Venda\n" +
                    "3- Gerenciar Pratos\n" +
                    "0- Sair");
            op = sc.nextInt();

            if (op == 1){
                System.out.println("===Funcionários===");
                System.out.println("1 - Listar");
                System.out.println("2 - Contratar");
                System.out.println("3 - Alterar salário");
                System.out.println("Escolha: ");
                int opc = sc.nextInt();
                sc.nextLine();

                if(opc == 1){
                    funcionarioService.listar();
                }else if(opc == 2){
                    System.out.println("Nome: ");
                    String nome = sc.nextLine();

                    System.out.println("Cargo: ");
                    String cargo = sc.nextLine();

                    System.out.println("Salário: ");
                    double salario = sc.nextDouble();

                    funcionarioService.contratar(nome, cargo, salario);

                    System.out.println("Novo funcionário registrado");
                } else if(opc == 3){
                    System.out.println("Nome");
                    String nome = sc.nextLine();

                    System.out.println("Porecentagem de aumento: ");
                    double porcentagem = sc.nextDouble();

                    funcionarioService.aumentarSalarioPorNome(nome, porcentagem);
                }

            }
            if (op == 2){
                System.out.println("Escolha o prato: ");
                for (int i = 0; i < pratos.size(); i++) {
                    System.out.println(i + " - " + pratos.get(i).getNome());
                }

                int p = sc.nextInt();

                System.out.println("Quantidade: ");
                int qtd = sc.nextInt();

                System.out.println("Pagamento: 1-Credito | 2-Pix | 3-Especie");
                int pg = sc.nextInt();

                Pagamento pagamento;
                if(pg == 1) pagamento = new PagamentoCredito();
                else if (pg == 2)pagamento = new PagamentoPix();
                else pagamento = new PagamentoEspecie();

                System.out.println("Entrega: 1-Presencial | 2-Delivery");
                int fr = sc.nextInt();

                Frete frete;
                if (fr == 2) frete = new FreteDelivery();
                else frete = new FretePresencial();

                VendasService venda = new VendasService(pratos, pagamento, frete);

                Prato pratoEscolhido = pratos.get(p);
                Pedido pedido = new Pedido(pratoEscolhido, qtd);
                double total = venda.vender(pedido);
            }

            if(op == 3){
                System.out.println("===Pratos===");
                System.out.println("1- Listar");
                System.out.println("2- Cadastrar");
                System.out.println("Opção: ");

                int opc = sc.nextInt();
                sc.nextLine();

                if(opc == 1){
                    for (int i = 0; i < pratos.size(); i++){
                        Prato p = pratos.get(i);
                        System.out.println(p.getNome());
                    }
                }if(opc ==2){
                    System.out.println("Nome do prato: ");
                    String nome = sc.nextLine();
                    System.out.print("Preço: ");
                    double preco = sc.nextDouble();
                    System.out.print("Estoque: ");
                    int estoque = sc.nextInt();
                    pratos.add(new Prato(nome, preco, estoque));

                    System.out.println("Prato cadastrado!");
                }

            }
        }
    }
}
