package br.com.bluesoft.desafio.util;

import br.com.bluesoft.desafio.model.Fornecedor;

public class CriadorFornecedor {

    public static Fornecedor criaFornecedor()
    {
        return Fornecedor.FornecedorBuilder.newFornecedor()
                .cnpj("56.918.868/0001-20")
                .nome("Fornecedor 1")
                .build();
    }

}
