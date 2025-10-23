/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.lanchonete;

/**
 *
 * @author GeoDantas
 */
import java.util.ArrayList;
import java.util.Scanner;

class Pedido {
    int id;
    String nomeCliente;
    String item;
    int quantidade;
    double valor;
    String tipo;

    public Pedido(int id, String nomeCliente, String item, int quantidade, double valor, String tipo) {
        this.id = id;
        this.nomeCliente = nomeCliente;
        this.item = item;
        this.quantidade = quantidade;
        this.valor = valor;
        this.tipo = tipo;
    }

    public String toString() {
        return "ID: " + id + " | " + nomeCliente + " | " + item + " x" + quantidade + 
               " | R$ " + String.format("%.2f", valor) + " | " + tipo;
    }
}

public class Lanchonete {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Pedido> pedidos = new ArrayList<>();

        while (true) {
            System.out.println("\n--menu--");
            System.out.println("1-novo pedido");
            System.out.println("2-mostrar pedidos");
            System.out.println("3-atender (remover primeiro)");
            System.out.println("4-cancelar último pedido");
            System.out.println("0-sair");
            System.out.print("escolha: ");
            int op = sc.nextInt();
            sc.nextLine();

            if (op == 0) break;

            switch (op) {
                case 1:
                    System.out.print("id do pedido: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    boolean existe = false;
                    for (Pedido p : pedidos) {
                        if (p.id == id) existe = true;
                    }
                    if (existe) {
                        System.out.println("id já existe!");
                        break;
                    }

                    System.out.print("nome do cliente: ");
                    String nome = sc.nextLine();
                    System.out.print("item: ");
                    String item = sc.nextLine();
                    System.out.print("quantidade: ");
                    int qtd = sc.nextInt();
                    System.out.print("valor unitário (R$): ");
                    double valorUnitario = sc.nextDouble();
                    sc.nextLine();

                    // calcula o valor total antes do acréscimo
                    double valorTotal = qtd * valorUnitario;

                    System.out.print("tipo (urgente/prioritario/normal): ");
                    String tipo = sc.nextLine();

                    // aplica o acréscimo no valor total
                    if (tipo.equalsIgnoreCase("urgente")) {
                        valorTotal *= 1.2;
                        pedidos.add(0, new Pedido(id, nome, item, qtd, valorTotal, "urgente"));
                    } else if (tipo.equalsIgnoreCase("prioritário")) {
                        valorTotal *= 1.1;
                        int meio = pedidos.size() / 2;
                        pedidos.add(meio, new Pedido(id, nome, item, qtd, valorTotal, "prioritário"));
                    } else {
                        pedidos.add(new Pedido(id, nome, item, qtd, valorTotal, "normal"));
                    }

                    System.out.println("pedido adicionado com sucesso!");
                    break;

                case 2:
                    if (pedidos.isEmpty()) {
                        System.out.println("nenhum pedido.");
                    } else {
                        System.out.println("\n📋 lista de pedidos:");
                        for (Pedido p : pedidos) {
                            System.out.println(p);
                        }
                    }
                    break;

                case 3:
                    if (!pedidos.isEmpty()) {
                        System.out.println("atendido: " + pedidos.remove(0));
                    } else {
                        System.out.println("nenhum pedido para atender!");
                    }
                    break;

                case 4:
                    if (!pedidos.isEmpty()) {
                        System.out.println("cancelado: " + pedidos.remove(pedidos.size() - 1));
                    } else {
                        System.out.println("nenhum pedido para cancelar!");
                    }
                    break;

                default:
                    System.out.println("opção inválida!");
            }
        }

        sc.close();
    }
}
