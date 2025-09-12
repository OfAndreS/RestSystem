// Local: br/com/meuRestaurante/modelos/Pedido.java
package br.com.meuRestaurante.modelos;

import java.util.ArrayList;

public class Pedido 
{
    Cliente clienteDoPedido;
    ArrayList<Produto> itensDoPedido;
    double totalDoPedido;
    String statusDoPedido;

    public Pedido(Cliente clienteDoPedido)
    {
        this.clienteDoPedido = clienteDoPedido;
        this.itensDoPedido = new ArrayList<Produto>();
        this.totalDoPedido = 0.0;
        this.statusDoPedido = "Aberto";
    }

    public void adicionarItem(Produto novoProduto)
    {
        this.itensDoPedido.add(novoProduto);
        this.totalDoPedido = this.totalDoPedido + novoProduto.getPreco();
    }

    public Cliente getCliente()
    {
        return this.clienteDoPedido;
    }

    public ArrayList<Produto> getItens()
    {
        return this.itensDoPedido;
    }

    public double getTotal()
    {
        return this.totalDoPedido;
    }
}