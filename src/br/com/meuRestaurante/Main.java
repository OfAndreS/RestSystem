package br.com.meuRestaurante;

import br.com.meuRestaurante.operacoes.mainFlow;

public class Main 
{

    public static void main(String[] args) throws Exception 
    {
        mainFlow myFlow = new mainFlow();
        myFlow.startMenu();
    }
}
