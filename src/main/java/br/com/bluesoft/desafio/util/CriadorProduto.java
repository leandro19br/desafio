package br.com.bluesoft.desafio.util;

import br.com.bluesoft.desafio.model.Produto;

public class CriadorProduto {


    public static Produto criaProduto()
    {
        return  Produto.ProdutoBuilder.newProduto()
                .gtin("7891910000197")
                .nome("AÇÚCAR REFINADO UNIÃO 1KG")
                .build();
    }

}
