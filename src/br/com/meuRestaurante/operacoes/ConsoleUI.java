// Local: br/com/meuRestaurante/operacoes/ConsoleUI.java
package br.com.meuRestaurante.operacoes;
import java.util.Scanner;
import java.util.ArrayList;

import br.com.meuRestaurante.modelos.Cliente;
import br.com.meuRestaurante.modelos.Pedido;
import br.com.meuRestaurante.modelos.Produto;

public class ConsoleUI 
{

    public void printLogo()
    {
         System.out.println(" ________  _______   ________  _________        ________       ___    ___ ________  _________  _______   _____ ______      ");
        System.out.println("|\\   __  \\|\\  ___ \\ |\\   ____\\|\\___   ___\\     |\\   ____\\     |\\  \\  /  /|\\   ____\\|\\___   ___\\\\  ___ \\ |\\   _ \\  _   \\     ");
        System.out.println("\\ \\  \\|\\  \\ \\   __/|\\ \\  \\___|\\|___ \\  \\_|     \\ \\  \\___|_    \\ \\  \\/  / | \\  \\___|\\|___ \\  \\_\\ \\   __/|\\ \\  \\\\\\__\\ \\  \\    ");
        System.out.println(" \\ \\   _  _\\ \\  \\_|/_\\ \\_____  \\   \\ \\  \\       \\ \\_____  \\    \\ \\    / / \\ \\_____  \\   \\ \\  \\ \\ \\  \\_|/_\\ \\  \\\\|__| \\  \\   ");
        System.out.println("  \\ \\  \\\\  \\\\ \\  \\_|\\ \\|____|\\  \\   \\ \\  \\       \\|____|\\  \\    \\/  /  /   \\|____|\\  \\   \\ \\  \\ \\ \\  \\_|\\ \\ \\  \\    \\ \\  \\  ");
        System.out.println("   \\ \\__\\\\ _\\\\ \\_______\\____\\_\\  \\   \\ \\__\\        ____\\_\\  \\ __/  / /        ____\\_\\  \\   \\ \\__\\ \\ \\_______\\ \\__\\    \\ \\__\\ ");
        System.out.println("    \\|__|\\|__|\\|_______|\\_________\\   \\|__|       |\\_________\\\\___/ /        |\\_________\\   \\|__|  \\|_______|\\|__|     \\|__| ");
        System.out.println("                       \\|_________|              \\|_________\\|___|/         \\|_________|                                     ");
    
        System.out.println("\n| Made by: Andre S. G. Moreira");
    }

    public void printHead()
    {
        System.out.print("\n\n|  * * * * * * * * * * * * * * * * * * *\n\n");
    }

    public void sleepOneSecond()
    {
        try {
            Thread.sleep(1000); 
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("| A pausa foi interrompida.");
        }
    }

    public void printMenu()
    {
        System.out.print("| MENU PRINCIPAL\n" +
                         "|\n" +
                         "| ( 1 ) - Cadastrar Cliente\n" +
                         "| ( 2 ) - Listar Clientes\n" +
                         "| ( 3 ) - Criar Novo Pedido\n" +
                         "| ( 4 ) - Listar Pedidos\n" +
                         "| ( 5 ) - Cancelar Pedido\n" +
                         "| ( 6 ) - Ver Cardápio\n" +
                         "|\n" +
                         "| ( 0 ) - Sair\n");
    }

    public String inputAnString(Scanner scanner, String prompt, int maxTentativas) 
    {
        for (int tentativaAtual = 1; tentativaAtual <= maxTentativas; tentativaAtual++) {
            System.out.print(prompt);
            String entrada = scanner.nextLine();

            if (entrada != null && !entrada.trim().isEmpty()) {
                return entrada; 
            }
            System.err.println("| Entrada inválida! Por favor, digite algum texto.");
        }
        System.err.println("\n| Limite de tentativas excedido!");
        return null; 
    }

    public int inputAnInt(Scanner scanner, String prompt, int maxTentativas) 
    {
        for (int tentativaAtual = 1; tentativaAtual <= maxTentativas; tentativaAtual++) {
            System.out.print(prompt);
            String linha = scanner.nextLine();
            try {
                int numero = Integer.parseInt(linha.trim());
                return numero; 
            } catch (NumberFormatException e) {
                System.err.println("| Erro: Entrada inválida. Por favor, digite apenas um número inteiro.");
            }
        }
        System.err.println("\n| Limite de tentativas excedido!");
        return -1; 
    }

    public void listarClientes(ArrayList<Cliente> clientList)
    {
        printHead();
        System.out.println("| LISTA DE CLIENTES CADASTRADOS");
        if (clientList.isEmpty())
        {
            System.out.println("\n| Nenhum cliente cadastrado.");
            return;
        }

        for (int i = 0; i < clientList.size(); i++)
        {
            Cliente cliente = clientList.get(i);
            System.out.printf("| ( %d ) Nome: %s | Idade: %d | Pedidos: %d\n", i, cliente.getNome(), cliente.getIdade(), cliente.getQuantidadeDePedidos());
        }
    }

    public void mostrarCardapio(ArrayList<Produto> cardapio)
    {
        printHead();
        System.out.println("| CARDÁPIO DO RESTAURANTE");
        for (int i = 0; i < cardapio.size(); i++)
        {
            Produto produto = cardapio.get(i);
            System.out.printf("| ( %d ) %s - R$ %.2f\n", i, produto.getNome(), produto.getPreco());
        }
    }

    public boolean listarPedidos(ArrayList<Pedido> pedidoList)
    {
        int couter = 0;
        printHead();
        System.out.println("| HISTÓRICO DE PEDIDOS");
        if (pedidoList.isEmpty())
        {
            System.out.println("\n| Nenhum pedido foi realizado.");
            return false;
        }

        for(Pedido pedido : pedidoList)
        {
            System.out.printf("\n| ID ( %d ) \n| Pedido para o cliente: %s\n", couter, pedido.getCliente().getNome());
            System.out.println("| Itens:");
            for (Produto item : pedido.getItens())
            {
                System.out.printf("|  - %s (R$ %.2f)\n", item.getNome(), item.getPreco());
            }
            System.out.printf("| Total do Pedido: R$ %.2f\n", pedido.getTotal());
            System.out.println("|");
            couter++;
        }

        return true;
    }
}