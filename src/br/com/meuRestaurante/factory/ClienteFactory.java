// Local: br/com/factory/ClienteFactory.java
package br.com.meuRestaurante.factory;

import java.util.Scanner;
import br.com.meuRestaurante.modelos.Cliente;
import br.com.meuRestaurante.operacoes.ConsoleUI;


public class ClienteFactory 
{
    public Cliente newClienteFac(Scanner userInput, ConsoleUI myConsole)
    {
        String nome = myConsole.inputAnString(userInput, "| Digite o nome do cliente: ", 3);
        int idade = myConsole.inputAnInt(userInput, "| Digite a idade do cliente: ", 3);

        if (nome != null && idade != -1)
        {
            return new Cliente(nome, idade);
        }
        return null; 
    }
}