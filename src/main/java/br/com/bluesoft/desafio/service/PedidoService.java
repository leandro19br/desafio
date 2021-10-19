package br.com.bluesoft.desafio.service;

import br.com.bluesoft.desafio.client.ClientService;
import br.com.bluesoft.desafio.exception.BadRequestExeception;
import br.com.bluesoft.desafio.model.Fornecedor;
import br.com.bluesoft.desafio.model.Pedido;
import br.com.bluesoft.desafio.model.Produto;
import br.com.bluesoft.desafio.repository.PedidoRepository;
import br.com.bluesoft.desafio.requests.FornecedorDTO;
import br.com.bluesoft.desafio.requests.PrecoDTO;
import br.com.bluesoft.desafio.requests.ProdutoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * @project desafio
 * Created by Leandro Saniago on 15/10/2021 - 09:02.
 */

@Service
public class PedidoService {

    private ClientService clientService;
    private PedidoRepository pedidoRepository;

    @Autowired
    public PedidoService(ClientService clientService, PedidoRepository pedidoRepository) {
        this.clientService = clientService;
        this.pedidoRepository = pedidoRepository;
    }

    public Iterable<Pedido> liatAll() {
        return pedidoRepository.findAll();
    }


    public Pedido novo(List<ProdutoDTO> produtosDTO) {
        Pedido pedido = new Pedido();
        List<ProdutoDTO> listaFiltrada = filtraProdutosSemQuantidade(produtosDTO);
        pedido = buscaMelhorValorFornecedor(listaFiltrada);
        return pedido;
    }

    private List<ProdutoDTO> filtraProdutosSemQuantidade(List<ProdutoDTO> produtosDTO) {
        List<ProdutoDTO> listaFiltrada = new ArrayList<>();

        for (ProdutoDTO produtoDTO : produtosDTO){
            if (produtoDTO.getQuantidade() > 0){
                listaFiltrada.add(produtoDTO);
            }
        }

        boolean empty = listaFiltrada.stream().findAny().isEmpty();

        if (empty){
            throw new BadRequestExeception("Produto com quantidade menor ou igual a zero");
        }
        return listaFiltrada;
    }

    private Pedido buscaMelhorValorFornecedor(List<ProdutoDTO> produtosDTO) {

        List<FornecedorDTO> listaFornecedor = new ArrayList<>();
        List<PrecoDTO> listaPrecoFornecedores = new ArrayList<>();
        Pedido pedido = new Pedido();

        for (ProdutoDTO produto : produtosDTO) {
            listaFornecedor = getFornecedorDTOS(produto);
            //fazendo um for em cada fornededor para o produto e pegando a lista de preço
            extrairListaPrecosOrdenada(listaFornecedor, listaPrecoFornecedores);
            pedido = getPedido(listaPrecoFornecedores, produto);
        }
        return pedido;
    }

    private Pedido getPedido(List<PrecoDTO> listaPrecoFornecedores, ProdutoDTO produto) {

        Pedido pedido = new Pedido();

        for (int i = 0; i < listaPrecoFornecedores.size(); i++) {
            if (produto.getQuantidade() >= listaPrecoFornecedores.get(i).getQuantidadeMinima()) {
                System.out.println(" Fornecedor " + listaPrecoFornecedores.get(i).getFornecedorDTO().getNome() + " do produto " + produto.getNome() + ", a quantidade mínima de pedido é " + listaPrecoFornecedores.get(i).getQuantidadeMinima() + " e o menor Valor é " + listaPrecoFornecedores.get(i).getPreco());
                Fornecedor fornecedor = getFornecedor(listaPrecoFornecedores, i);
                Produto produtoMontado = getProduto(produto);
                pedido = Pedido.PedidoBuilder.newPedido()
                        .fornecedor(Arrays.asList(fornecedor))
                        .produtos(Arrays.asList(produtoMontado))
                        .quantidade(produto.getQuantidade())
                        .preco(listaPrecoFornecedores.get(i).getPreco())
                        .total(listaPrecoFornecedores.get(i).getPreco().multiply(new BigDecimal(produto.getQuantidade())))
                        .build();
                pedidoRepository.save(pedido);
                break;
            }
        }
        return pedido;
    }

    private List<FornecedorDTO> getFornecedorDTOS(ProdutoDTO produto) {
        List<FornecedorDTO> listaFornecedor;
        //buscar o fornecedor de cada produto
        listaFornecedor = clientService.buscaListaFornecedor(produto.getGtin());
        return listaFornecedor;
    }

    private void extrairListaPrecosOrdenada(List<FornecedorDTO> listaFornecedor, List<PrecoDTO> listaPrecoFornecedores) {
        for (FornecedorDTO f : listaFornecedor) {
            List<PrecoDTO> precos = f.getPrecos();
            listaPrecoFornecedores.addAll(precos);
        }
        OrdenaListaPreco(listaPrecoFornecedores);
    }

    private Produto getProduto(ProdutoDTO produto) {
        return Produto.ProdutoBuilder.newProduto()
                .gtin(produto.getGtin())
                .nome(produto.getNome())
                .build();
    }

    private Fornecedor getFornecedor(List<PrecoDTO> listaPrecoFornecedores, int i) {
        return Fornecedor.FornecedorBuilder.newFornecedor()
                .cnpj(listaPrecoFornecedores.get(i).getFornecedorDTO().getCnpj())
                .nome(listaPrecoFornecedores.get(i).getFornecedorDTO().getNome())
                .build();
    }

    private void OrdenaListaPreco(List<PrecoDTO> listaPrecoFornecedores) {
        listaPrecoFornecedores.sort(new Comparator<PrecoDTO>() {
            public int compare(PrecoDTO o1, PrecoDTO o2) {
                return o1.getPreco().compareTo(o2.getPreco());
            }
        });
    }
}
