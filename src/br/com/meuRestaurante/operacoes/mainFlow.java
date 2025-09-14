// Local: br/com/meuRestaurante/operacoes/mainFlow.java
package br.com.meuRestaurante.operacoes;

import java.util.Scanner;
import java.util.ArrayList; 

import br.com.meuRestaurante.factory.ClienteFactory;
import br.com.meuRestaurante.modelos.Cliente;
import br.com.meuRestaurante.modelos.Pedido;
import br.com.meuRestaurante.modelos.Produto;

public class mainFlow {

    private ClienteFactory myClienteFac = new ClienteFactory();
    private ConsoleUI myConsole = new ConsoleUI();
    private ArrayList<Cliente> clientList = new ArrayList<Cliente>();
    private ArrayList<Produto> cardapio = new ArrayList<Produto>();
    private ArrayList<Pedido> pedidoList = new ArrayList<Pedido>();

    private void preencherCardapio()
    {
        cardapio.add(new Produto("Pizza Mussarela", 45.50));
        cardapio.add(new Produto("Hamburguer Duplo", 35.75));
        cardapio.add(new Produto("Porção de Fritas", 25.00));
        cardapio.add(new Produto("Refrigerante Lata", 8.00));
        cardapio.add(new Produto("Suco Natural", 12.00));
    }

    public void startMenu()
    {
        myConsole.printHead();
        myConsole.printLogo();
        preencherCardapio();
        try (Scanner userInput = new Scanner(System.in))
        {
            while (true) 
            {
                myConsole.printHead();
                myConsole.printMenu();
                String myUserInput = myConsole.inputAnString(userInput, "| Escolha uma opção: ", 1);

                if (myUserInput == null) continue; 

                switch (myUserInput) 
                {
                    case "1":
                        clientList.add(myClienteFac.newClienteFac(userInput, myConsole));
                        System.out.println("\n| Cliente cadastrado com sucesso!");
                        System.err.println("\n| Retornando ao Menu...");
                        myConsole.sleepOneSecond();
                        break;

                    case "2":
                        myConsole.listarClientes(clientList);
                        break;
                    
                    case "3":
                        criarNovoPedido(userInput);
                        System.err.println("\n| Retornando ao Menu...");
                        myConsole.sleepOneSecond();
                        break;

                    case "4":
                        myConsole.listarPedidos(pedidoList);
                        break;

                    case "5":
                        removerPedido(pedidoList, userInput);
                        break;

                    case "6":
                        myConsole.mostrarCardapio(cardapio);
                        break;
                    
                    case "0":
                        System.out.print("\n| Desligando...");
                        myConsole.sleepOneSecond();
                        myConsole.printHead();
                        System.exit(0); 
                        break;
                
                    default:
                        System.err.println("| Opção inválida!");
                        break;
                }
            }
        }
    }

    private void criarNovoPedido(Scanner userInput)
    {
        myConsole.listarClientes(clientList);
        if (clientList.isEmpty()) {
            System.out.println("| Cadastre um cliente primeiro!");
            return;
        }

        int userIndex = myConsole.inputAnInt(userInput, "| Digite o ID do cliente para o pedido: ", 3);
        
        if (userIndex < 0 || userIndex >= clientList.size())
        {
            System.err.println("\n| ID do cliente inválido!");
            System.err.println("\n| Retornando ao Menu...");
            myConsole.sleepOneSecond();
            return;
        }

        Cliente clienteSelecionado = clientList.get(userIndex);
        Pedido novoPedido = new Pedido(clienteSelecionado);
        
        while(true)
        {
            myConsole.mostrarCardapio(cardapio);
            System.out.println("| Digite o ID do produto para adicionar ou ( -1 ) para finalizar o pedido.");
            int indexProduto = myConsole.inputAnInt(userInput, "| Escolha: ", 3);

            if (indexProduto == -1)
            {
                break; 
            }

            if (indexProduto >= 0 && indexProduto < cardapio.size())
            {
                Produto produtoSelecionado = cardapio.get(indexProduto);
                novoPedido.adicionarItem(produtoSelecionado);
                System.out.printf("| Item '%s' adicionado ao pedido!\n", produtoSelecionado.getNome());
            }
            else
            {
                System.err.println("| ID do produto inválido!");
            }
        }
        
        if (!novoPedido.getItens().isEmpty())
        {
            pedidoList.add(novoPedido);
            clienteSelecionado.incrementarQuantidadeDePedidos();
            System.out.printf("\n| Pedido para %s finalizado com sucesso! Total: R$ %.2f\n", clienteSelecionado.getNome(), novoPedido.getTotal());
        }
        else
        {
            System.out.println("\n| O pedido foi cancelado pois nenhum item foi adicionado.");
        }
    }

    public void removerPedido(ArrayList<Pedido> pedidoList, Scanner userInput)
    {
        if(!myConsole.listarPedidos(pedidoList))
        {
            return;
        }

        int userIndex = myConsole.inputAnInt(userInput, "\n| Digite o ID do pedido para remoção: ", 3);
        
        if (userIndex < 0 || userIndex > clientList.size())
        {
            System.err.println("\n| ID do pedido inválido!");
            System.err.println("\n| Retornando ao Menu...");
            myConsole.sleepOneSecond();
            return;
        }
        
        pedidoList.remove(0);


    }
}