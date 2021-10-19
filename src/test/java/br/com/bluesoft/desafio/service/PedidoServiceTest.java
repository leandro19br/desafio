package br.com.bluesoft.desafio.service;

import br.com.bluesoft.desafio.client.ClientService;
import br.com.bluesoft.desafio.model.Pedido;
import br.com.bluesoft.desafio.repository.PedidoRepository;
import br.com.bluesoft.desafio.requests.FornecedorDTO;
import br.com.bluesoft.desafio.util.CriadorFornecedor;
import br.com.bluesoft.desafio.util.CriadorFornecedorDTO;
import br.com.bluesoft.desafio.util.CriadorPedido;
import br.com.bluesoft.desafio.util.CriadorProdutoDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

/**
 * @project desafio
 * Created by Leandro Saniago on 19/10/2021 - 11:42.
 */
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
class PedidoServiceTest {

    @InjectMocks
    PedidoService pedidoService;

    @Mock
    PedidoRepository pedidoRepositoryMock;
    @Mock
    ClientService clientService;

    @BeforeEach
    void setup() {

        BDDMockito.when(pedidoRepositoryMock.save(ArgumentMatchers.any(Pedido.class))).thenReturn(CriadorPedido.criaPedidoSalvo());
        BDDMockito.when(clientService.buscaListaFornecedor(ArgumentMatchers.any())).thenReturn(Arrays.asList(CriadorFornecedorDTO.criaFornecedorDTO()));

    }

    @Test
    void deveSalvarPedidoQuandoSucesso() {

        Pedido pedido = pedidoService.novo(Arrays.asList(CriadorProdutoDTO.criaProdutoDTO()));
        Assertions.assertThat(pedido)
                .isNotNull();

    }
}