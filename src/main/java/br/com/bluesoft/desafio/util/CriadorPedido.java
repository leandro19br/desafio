package br.com.bluesoft.desafio.util;

import br.com.bluesoft.desafio.model.Pedido;

import java.math.BigDecimal;
import java.util.Arrays;

public class CriadorPedido {


    public static Pedido criaPedidoAntesDeSalvar()
    {
        return  Pedido.PedidoBuilder.newPedido()
                .fornecedor(Arrays.asList(CriadorFornecedor.criaFornecedor()))
                .produtos(Arrays.asList(CriadorProduto.criaProduto()))
                .quantidade(10)
                .preco(new BigDecimal(2.0))
                .total(new BigDecimal(20))
                .build();
    }

    public static Pedido criaPedidoSalvo() {
        return  Pedido.PedidoBuilder.newPedido()
                .idPedido(1L)
                .fornecedor(Arrays.asList(CriadorFornecedor.criaFornecedor()))
                .produtos(Arrays.asList(CriadorProduto.criaProduto()))
                .quantidade(10)
                .preco(new BigDecimal(2.0))
                .total(new BigDecimal(20))
                .build();
    }
}
