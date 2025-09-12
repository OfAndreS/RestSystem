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
                        System.out.println("| Cliente cadastrado com sucesso!");
                        break;

                    case "2":
                        myConsole.listarClientes(clientList);
                        break;
                    
                    case "3":
                        criarNovoPedido(userInput);
                        break;

                    case "4":
                        myConsole.listarPedidos(pedidoList);
                        break;

                    case "5":
                        myConsole.mostrarCardapio(cardapio);
                        break;
                    
                    case "0":
                        System.out.print("| Desligando...");
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

        int indexCliente = myConsole.inputAnInt(userInput, "| Digite o ID do cliente para o pedido: ", 3);
        
        if (indexCliente == -1 || indexCliente >= clientList.size())
        {
            System.err.println("| ID do cliente inválido!");
            return;
        }

        Cliente clienteSelecionado = clientList.get(indexCliente);
        Pedido novoPedido = new Pedido(clienteSelecionado);
        
        while(true)
        {
            myConsole.mostrarCardapio(cardapio);
            System.out.println("| Digite o ID do produto para adicionar ou [-1] para finalizar o pedido.");
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
            System.out.printf("| Pedido para %s finalizado com sucesso! Total: R$ %.2f\n", clienteSelecionado.getNome(), novoPedido.getTotal());
        }
        else
        {
            System.out.println("| O pedido foi cancelado pois nenhum item foi adicionado.");
        }
    }
}