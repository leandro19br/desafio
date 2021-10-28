package br.com.bluesoft.desafio.service;

import br.com.bluesoft.desafio.client.ClientService;
import br.com.bluesoft.desafio.exception.BadRequestExeception;
import br.com.bluesoft.desafio.model.Fornecedor;
import br.com.bluesoft.desafio.model.ItemPedido;
import br.com.bluesoft.desafio.model.Pedido;
import br.com.bluesoft.desafio.model.Produto;
import br.com.bluesoft.desafio.repository.PedidoRepository;
import br.com.bluesoft.desafio.repository.ProdutoRepository;
import br.com.bluesoft.desafio.requests.FornecedorDTO;
import br.com.bluesoft.desafio.requests.PrecoDTO;
import br.com.bluesoft.desafio.requests.ProdutoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Supplier;

/**
 * @project desafio
 * Created by Leandro Saniago on 15/10/2021 - 09:02.
 */

@Service
public class PedidoService {

    private ClientService clientService;
    private PedidoRepository pedidoRepository;
    private ProdutoRepository produtoRepository;

    @Autowired
    public PedidoService(ClientService clientService, PedidoRepository pedidoRepository, ProdutoRepository produtoRepository) {
        this.clientService = clientService;
        this.pedidoRepository = pedidoRepository;
        this.produtoRepository = produtoRepository;
    }

    public Iterable<Pedido> liatAll() {
        return pedidoRepository.findAll();
    }


    public Pedido novo(List<ProdutoDTO> produtosDTO) {
        //List<ProdutoDTO> listaFiltrada = filtraProdutosSemQuantidade(produtosDTO);
        filtraProdutosSemQuantidade(produtosDTO);
        //Optional<Pedido> pedido = buscaMelhorValorFornecedor(produtosDTO);
        //pedido.orElseThrow(new BadRequestExeception("teste"));
        return buscaMelhorValorFornecedor(produtosDTO).orElseThrow(() -> new BadRequestExeception("Pesiso está null"));
    }

    private void filtraProdutosSemQuantidade(List<ProdutoDTO> produtosDTO) {
        produtosDTO.removeIf(produtoDTO -> produtoDTO.getQuantidade() == 0);
        boolean empty = produtosDTO.stream().findAny().isEmpty();
        if (empty) {
            throw new BadRequestExeception("Produto com quantidade menor ou igual a zero");
        }

    }

    private Optional<Pedido> buscaMelhorValorFornecedor(List<ProdutoDTO> produtosDTO) {
        //List<PrecoDTO> listaPrecoFornecedores = new ArrayList<>();
        Produto produto = new Produto();
        for (ProdutoDTO produtoDTO : produtosDTO) {
            List<FornecedorDTO> listaFornecedor = buscaFornecedorDoProduto(produtoDTO);
            extraiListaPrecosOrdenada(listaFornecedor, produtoDTO.getQuantidade());
            if (produtoDTO.getNome() == null) {
                produto = produtoRepository.findBygtin(produtoDTO.getGtin());
                produtoDTO.setNome(produto.getNome());
            }
            return Optional.of(geraPedido(produtoDTO, listaFornecedor));
        }
        return Optional.empty();
    }

    private Pedido geraPedido(ProdutoDTO produto, List<FornecedorDTO> listaFornecedores) {
        Pedido pedido = new Pedido();
        for (FornecedorDTO forn : listaFornecedores) {
            List<PrecoDTO> listaPrecoFornecedores = forn.getPrecos();
            System.out.println(" Fornecedor " + forn.getNome() + " do produto " + produto.getNome() + ", a quantidade mínima de pedido é " + listaPrecoFornecedores.get(0).getQuantidadeMinima() + " e o menor Valor é " + listaPrecoFornecedores.get(0).getPreco());
            Fornecedor fornecedor = criaFornecedor(forn);
            ItemPedido itemPedido = criaProduto(produto);
            pedido = Pedido.PedidoBuilder.newPedido()
                    .fornecedor(Arrays.asList(fornecedor))
                    .produtos(Arrays.asList(itemPedido))
                    .quantidade(produto.getQuantidade())
                    .preco(listaPrecoFornecedores.get(0).getPreco())
                    .total(listaPrecoFornecedores.get(0).getPreco().multiply(new BigDecimal(produto.getQuantidade())))
                    .build();
            pedidoRepository.save(pedido);
        }
        return pedido;
    }

    private List<FornecedorDTO> buscaFornecedorDoProduto(ProdutoDTO produto) {
        return clientService.buscaListaFornecedor(produto.getGtin());
    }

    private void extraiListaPrecosOrdenada(List<FornecedorDTO> listaFornecedor, int quantidade) {

        for (FornecedorDTO f : listaFornecedor) {
            List<PrecoDTO> precos = f.getPrecos();
            for (Iterator<PrecoDTO> iterator = precos.iterator(); iterator.hasNext(); ) {
                PrecoDTO next = iterator.next();
                if (next.getQuantidadeMinima() > quantidade) {
                    iterator.remove();
                }
            }
            ordenaListaPreco(precos);
        }
    }

    private ItemPedido criaProduto(ProdutoDTO produto) {
        return ItemPedido.ItemPedidoBuilder.newItemPedido()
                .gtin(produto.getGtin())
                .nome(produto.getNome())
                .build();
    }

    private Fornecedor criaFornecedor(FornecedorDTO fornecedorDTO) {
        return Fornecedor.FornecedorBuilder.newFornecedor()
                .cnpj(fornecedorDTO.getCnpj())
                .nome(fornecedorDTO.getNome())
                .build();
    }

    private void ordenaListaPreco(List<PrecoDTO> listaPrecoFornecedores) {
        listaPrecoFornecedores.sort(Comparator.comparing(PrecoDTO::getPreco));
    }
}
