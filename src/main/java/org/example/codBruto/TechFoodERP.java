package org.example.codBruto;

import java.util.Scanner;

public class TechFoodERP {
    // VETORES SIMULANDO BANCO DE DADOS (Atributos Estáticos)
    private static String[] funcNomes = new String[10];
    private static String[] funcCargos = new String[10];
    private static double[] funcSalarios = new double[10];
    private static int funcTotal = 0;
    private static String[] pratoNomes = new String[20];
    private static double[] pratoPrecos = new double[20];
    private static int[] pratoEstoque = new int[20];
    private static int pratoTotal = 0;
    private static double faturamentoAtum = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        inicializarDados();
        int opcao = -1;
        while (opcao == 0) {
            System.out.println("\n - TECHFOOD ERP - MENU PRINCIPAL - ");
                    System.out.println("1 - Gerenciar Funcionários (Ver / Contratar / Demitir / Editar) ");
            System.out.println("2 - Pagamentos e Aumento Salarial");
            System.out.println("3 - Gerenciar Cardápio (Ver / Cadastrar / Excluir / Editar) ");
            System.out.println("4 - Gerenciar Estoque (Entrada / Visualizar) ");
            System.out.println("5 - REALIZAR VENDA (Presencial ou Delivery) ");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao) {
                case 1: //GERENCIAR FUNCIONÁRIOS (Viola SRP)
                    System.out.println("1-Listar / 2-Contratar / 3 - Demitir / 4 - Editar");
                    int subOp = scanner.nextInt();
                    scanner.nextLine();
                    if (subOp == 1) {
                        for (int i = 0; i < funcTotal; i ++)
                            System.out.println(i + ": " + funcNomes[i] + " (" + funcCargos[i] + ")");
                    } else if (subOp == 2) {
                        System.out.print("Nome: ");
                        funcNomes[funcTotal]
                                = scanner.nextLine();
                        System.out.print("Cargo: ");
                        funcCargos[funcTotal] = scanner.nextLine();
                        System.out.print("Salário: ");
                        funcSalarios[funcTotal] = scanner.nextDouble();
                        funcTotal ++;
                    } else if (subOp == 3) {
                        System.out.print("Informe o código para demitir: ");
                        int id = scanner.nextInt();
                                funcNomes[id] = "DEMITIDO";
                    }
                    break;
                case 2: //PAGAMENTOS E AUMENTO(Viola SRP e OCP)
                    System.out.print("Código do funcionário: ");
                    int Cod
                            = scanner.nextInt();
                    System.out.print("Porcentagem de aumento (ex: 10): ");
                    double aumento = scanner.nextDouble();
                            funcSalarios[Cod] += funcSalarios[Cod] * (aumento / 100);
                    System.out.println("Novo salário de " +
                            funcNomes[Cod] + ": R$ " + funcSalarios[Cod]);
                    break;
                case 3: //CARDÁPIO(Viola SRP)
                    for (int i = 0; i < pratoTotal; i ++)
                        System.out.println(i
                                + ": " + pratoNomes[i] + " - R$ " + pratoPrecos[i]);
                    System.out.println("Deseja cadastrar novo? (1 - Sim / 0 - Não) ");
                    if (scanner.nextInt() == 1) {
                        scanner.nextLine();
                        System.out.print("Nome do Prato: ");
                        pratoNomes[pratoTotal] = scanner.nextLine();
                        System.out.print("Preço: ");
                        pratoPrecos[pratoTotal] = scanner.nextDouble();
                        System.out.print("Estoque Inicial: ");
                        pratoEstoque[pratoTotal] = scanner.nextInt();
                        pratoTotal ++;
                    }
                    break;
                case 4: //ESTOQUE(Viola SRP)
                    for (int i = 0; i < pratoTotal; i ++)
                        System.out.println(i
                                + ": " + pratoNomes[i] + " | Qtd: " + pratoEstoque[i]);
                    System.out.print("Index para dar entrada: ");
                    int
                            pCod = scanner.nextInt();
                    System.out.print("Quantidade para somar: ");
                    int qtd
                            = scanner.nextInt();
                    pratoEstoque[pCod] += qtd;
                    break;
                case 5: //VENDA COMPLEXA (Viola SRP, OCP, LSP, ISP, DIP)
                    System.out.println(" - NOVA VENDA -");
                    for (int i = 0; i < pratoTotal; i ++)
                        System.out.println(i
                                + ": " + pratoNomes[i] + " [Estoque: " + pratoEstoque[i] + "]");
                    System.out.print("Escolha o prato: ");
                    int esc =
                            scanner.nextInt();
                    System.out.print("Quantidade: ");
                    int qVenda =
                            scanner.nextInt();
                    if (pratoEstoque[esc] < qVenda) {
                        System.out.println("ERRO: ESTOQUE INSUFICIENTE !");
                        break;
                    }
                    double valorBase = pratoPrecos[esc] * qVenda;
                    System.out.print("1-Presencial / 2-Delivery: ");
                    int
                            tipoVenda = scanner.nextInt();
                    if (tipoVenda == 2) valorBase += 15.0; //Frete Fixo (Fere OCP)
                    System.out.println("Pagamento: 1-Crédito / 2-Débito / 3 - PIX / 4 - Espécie");
                    int pg = scanner.nextInt();
                    double valorFinal = valorBase;
                    if (pg == 1) { //CRÉDITO
                        System.out.print("Parcelas: ");
                        int parc =
                                scanner.nextInt();
                        valorFinal = valorBase * 1.05; //Juros fixos
                    } else if (pg == 3) { //PIX
                            valorFinal = valorBase * 0.90; //10 % OFF
                    } else if (pg == 4) { //ESPÉCIE
                            valorFinal = valorBase * 0.92; //8 % OFF
                    }
                    pratoEstoque[esc] -= qVenda; //Saída de estoque
                    faturamentoAtum += valorFinal;
                    System.out.println("VENDA FINALIZADA! Total: R$ " +
                            valorFinal);
                    break;
            }
        }
    }

    private static void inicializarDados() {
//Funcionários Iniciais
        funcNomes[0] = "Lucas (Dono)";
        funcCargos[0] = "Gerente";
        funcSalarios[0] = 8000;
        funcNomes[1] = "Ana";
        funcCargos[1] = "Caixa";
        funcSalarios[1] = 2000;
        funcNomes[2] = "Beto";
        funcCargos[2] = "Cozinheiro";
        funcSalarios[2] = 3000;
        funcNomes[3] = "Zeca";
        funcCargos[3] = "Cozinheiro";
        funcSalarios[3] = 3000;
        funcNomes[4] = "Vitor";
        funcCargos[4] = "Garçom";
        funcSalarios[4] = 1800;
        funcNomes[5] = "Luan";
        funcCargos[5] = "Garçom";
        funcSalarios[5] = 1800;
        funcNomes[6] = "Dona Flor";
        funcCargos[6] = "Limpeza";
        funcSalarios[6] = 1600;
        funcNomes[7] = "Marcio";
        funcCargos[7] = "Segurança";
        funcSalarios[7] = 2500;
        funcNomes[8] = "Tiago";
        funcCargos[8] = "Motoboy";
        funcSalarios[8] = 2200;
        funcTotal = 9;
//Pratos Brasileiros
        pratoNomes[0] = "Feijoada Completa";
        pratoPrecos[0] = 45.0;
        pratoEstoque[0] = 10;
        pratoNomes[1] = "Moqueca de Peixe";
        pratoPrecos[1] = 65.0;
        pratoEstoque[1] = 5;
        pratoNomes[2] = "Baião de Dois";
        pratoPrecos[2] = 35.0;
        pratoEstoque[2] = 8;
        pratoNomes[3] = "Acarajé";
        pratoPrecos[3] = 25.0;
        pratoEstoque[3] = 15;
        pratoNomes[4] = "Pato no Tucupi";
        pratoPrecos[4] = 80.0;
        pratoEstoque[4] = 3;
        pratoNomes[5] = "Vaca Atolada";
        pratoPrecos[5] = 40.0;
        pratoEstoque[5] = 7;
        pratoNomes[6] = "Arroz Carreteiro";
        pratoPrecos[6] = 30.0;
        pratoEstoque[6] = 12;
        pratoNomes[7] = "Pão de Queijo Porção";
        pratoPrecos[7] = 15.0;
        pratoEstoque[7] = 20;
        pratoNomes[8] = "Barreado";
        pratoPrecos[8] = 50.0;
        pratoEstoque[8] = 6;
        pratoNomes[9] = "Tacacá";
        pratoPrecos[9] = 28.0;
        pratoEstoque[9] = 10;
        pratoTotal = 10;
    }
}
