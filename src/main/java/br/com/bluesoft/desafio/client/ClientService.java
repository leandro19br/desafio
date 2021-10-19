package br.com.bluesoft.desafio.client;

import br.com.bluesoft.desafio.model.Fornecedor;
import br.com.bluesoft.desafio.model.Produto;
import br.com.bluesoft.desafio.requests.FornecedorDTO;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @project desafio
 * Created by Leandro Saniago on 13/10/2021 - 18:14.
 * classe criada para chamada GET ao serviço externo
 */

@Service
public class ClientService {

    private static final String URI = "https://egf1amcv33.execute-api.us-east-1.amazonaws.com/dev/produto";

    public List<FornecedorDTO> buscaListaFornecedor(String gtin) {

        RestTemplate restTemplate = new RestTemplateBuilder()
                .rootUri(URI).build();
        FornecedorDTO[] forObject = restTemplate.getForObject("/{gtin}", FornecedorDTO[].class, gtin);
        //lista de fornecedor para o produto específico
        List<FornecedorDTO> listafornecedor = Arrays.asList(forObject);

        return listafornecedor;

    }

}
