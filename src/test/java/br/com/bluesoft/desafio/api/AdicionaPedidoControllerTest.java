package br.com.bluesoft.desafio.api;

import br.com.bluesoft.desafio.model.Pedido;
import br.com.bluesoft.desafio.requests.ProdutoDTO;
import br.com.bluesoft.desafio.service.PedidoService;
import br.com.bluesoft.desafio.util.CriadorPedido;
import br.com.bluesoft.desafio.util.CriadorProdutoDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * @project desafio
 * Created by Leandro Saniago on 19/10/2021 - 09:14.
 */

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class AdicionaPedidoControllerTest {

    @InjectMocks
    AdicionaPedidoController pedidoControllerMock;

    @Mock
    PedidoService pedidoServiceMock;

    @BeforeEach
    void setup() {
        PageImpl<Pedido> pedidoPage = new PageImpl<Pedido>(Arrays.asList(CriadorPedido.criaPedidoAntesDeSalvar()));
        BDDMockito.when(pedidoServiceMock.liatAll()).thenReturn(pedidoPage);
        BDDMockito.when(pedidoServiceMock.novo(ArgumentMatchers.anyList())).thenReturn(CriadorPedido.criaPedidoSalvo());
    }

    @Test
    @DisplayName("deve retornar lista de pedido quando sucesso")
    public void listAllDeveRetornarListaQuandoSucesso() {

        Iterable<Pedido> pedidoIterable = pedidoControllerMock.listAll().getBody();
        Assertions.assertThat(pedidoIterable)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);
    }

    @Test
    @DisplayName("deve salvar Pedido quando sucesso")
    public void deveSalvarPedidoQuandoSucesso() {

        Pedido pedido = pedidoControllerMock.novo(Arrays.asList(CriadorProdutoDTO.criaProdutoDTO())).getBody();
        Assertions.assertThat(pedido)
                .isNotNull()
        .hasFieldOrProperty("preco");

    }


}