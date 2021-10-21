package br.com.bluesoft.desafio.util;

import br.com.bluesoft.desafio.model.ItemPedido;
import br.com.bluesoft.desafio.model.Produto;

public class CriadorProduto {


    public static ItemPedido criaProduto() {
        return ItemPedido.ItemPedidoBuilder.newItemPedido()
                .gtin("7891910000197")
                .nome("AÇÚCAR REFINADO UNIÃO 1KG")
                .build();
    }

}
