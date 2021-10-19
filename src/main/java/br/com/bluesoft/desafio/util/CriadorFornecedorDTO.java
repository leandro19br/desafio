package br.com.bluesoft.desafio.util;

import br.com.bluesoft.desafio.model.Preco;
import br.com.bluesoft.desafio.requests.FornecedorDTO;
import br.com.bluesoft.desafio.requests.PrecoDTO;

import java.math.BigDecimal;
import java.util.Arrays;

public class CriadorFornecedorDTO {

    public static FornecedorDTO criaFornecedorDTO()
    {
        PrecoDTO precoDTO = PrecoDTO.PrecoDTOBuilder.newPrecoDTO()
                .quantidadeMinima(1)
                .preco(new BigDecimal(2.00))
                .fornecedorDTO(FornecedorDTO.FornecedorDTOBuilder.newFornecedorDTO()
                        .cnpj("56.918.868/0001-20")
                .nome("Fornecedor 1")
                .build())
                .build();

        return FornecedorDTO.FornecedorDTOBuilder.newFornecedorDTO()
                .cnpj("56.918.868/0001-20")
                .nome("Fornecedor 1")
                .precos(Arrays.asList(precoDTO))
                 .build();
    }

}
