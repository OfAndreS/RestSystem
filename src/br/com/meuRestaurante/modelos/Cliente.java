// Local: br/com/meuRestaurante/modelos/Cliente.java
package br.com.meuRestaurante.modelos;

public class Cliente {
    String nomeDoCliente;
    int quantidadeDePedidos;
    int idadeDoCliente;

    public Cliente(String nomeDoCliente, int idadeDoCliente)
    {
        this.nomeDoCliente = nomeDoCliente;
        this.idadeDoCliente = idadeDoCliente;
        this.quantidadeDePedidos = 0; 
    }

    public void incrementarQuantidadeDePedidos()
    {
        this.quantidadeDePedidos++;
    }

    public String getNome()
    {
        return this.nomeDoCliente;
    }

    public int getQuantidadeDePedidos()
    {
        return this.quantidadeDePedidos;
    }
    
    public int getIdade()
    {
        return this.idadeDoCliente;
    }
}