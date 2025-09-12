// Local: br/com/meuRestaurante/modelos/Produto.java
package br.com.meuRestaurante.modelos;

public class Produto {
    String nomeDoProduto;
    double precoDoProduto;

    public Produto(String nomeDoProduto, double precoDoProduto)
    {
        this.nomeDoProduto = nomeDoProduto;
        this.precoDoProduto = precoDoProduto;
    }

    public String getNome()
    {
        return this.nomeDoProduto;
    }

    public double getPreco()
    {
        return this.precoDoProduto;
    }
}