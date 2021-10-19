package br.com.bluesoft.desafio.client;

import br.com.bluesoft.desafio.model.Fornecedor;
import br.com.bluesoft.desafio.model.Preco;
import br.com.bluesoft.desafio.model.Produto;
import br.com.bluesoft.desafio.requests.FornecedorDTO;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @project desafio
 * Created by Leandro Saniago on 13/10/2021 - 18:14.
 * classe criada para chamada GET ao serviço externo
 */

public class SpringClientTest {

    public static void main(String[] args) {

        List<Produto> listaProduto = new ArrayList<>();
        BuscaFornecedor fornecedor = new BuscaFornecedor();

       /* Produto p1 = new Produto("7894900011517", "REFRIGERANTE COCA-COLA 2LT");
        Produto p2 = new Produto("7891910000197", "AÇÚCAR REFINADO UNIÃO 1KG");

        listaProduto.add(p1);
        listaProduto.add(p2);*/

        //listaProduto.stream().forEach(produto -> fornecedor.buscaListaFornecedor(produto.getGtin()));
        //listaProduto.stream().forEach(produto -> System.out.println("Cod de barras do produto " + produto.getGtin()));

        //for (Produto produto : listaProduto) {
            List<FornecedorDTO> listaFornecedor = fornecedor.buscaListaFornecedor("7894900011517");
            //listaFornecedor.stream().forEach(f ->  System.out.println("Nome do Fornecedor " + f.getNome() ));

        //}


    }

}

class BuscaFornecedor{
    private static String URI = "https://egf1amcv33.execute-api.us-east-1.amazonaws.com/dev/produto";

    public List<FornecedorDTO> buscaListaFornecedor(String gtin) {

        RestTemplate restTemplate = new RestTemplateBuilder()
                    .rootUri(URI).build();
            FornecedorDTO[] forObject = restTemplate.getForObject("/{gtin}", FornecedorDTO[].class, gtin);
            //lista de fornecedor para o produto específico
            List<FornecedorDTO> listafornecedor = Arrays.asList(forObject);

        return listafornecedor;

    }

}
