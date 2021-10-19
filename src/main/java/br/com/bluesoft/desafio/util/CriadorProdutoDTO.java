package br.com.bluesoft.desafio.util;

import br.com.bluesoft.desafio.model.Pedido;
import br.com.bluesoft.desafio.requests.ProdutoDTO;

import java.math.BigDecimal;
import java.util.Arrays;

public class CriadorProdutoDTO {


    public static ProdutoDTO criaProdutoDTO() {
        return ProdutoDTO.ProdutoDTOBuilder.newProdutoDTO()
                .gtin("7891910000197")
                .nome("AÇÚCAR REFINADO UNIÃO 1KG")
                .quantidade(10)
                .build();
    }

}
