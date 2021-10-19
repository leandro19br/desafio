package br.com.bluesoft.desafio.repository;

import br.com.bluesoft.desafio.model.Pedido;
import br.com.bluesoft.desafio.util.CriadorPedido;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @project desafio
 * Created by Leandro Saniago on 19/10/2021 - 09:16.
 */
@DataJpaTest
@DisplayName("Testes para o PedidoRepository")
class PedidoRepositoryTest {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Test
    @DisplayName("deve salvar um pedido quando sucesso")
    public void salvaPedidoQuandoSucesso() {

        Pedido pedidoCriadoAntesDeSalvar = CriadorPedido.criaPedidoAntesDeSalvar();

        Pedido pedidoCriadoDepoisDeSalvar = pedidoRepository.save(pedidoCriadoAntesDeSalvar);

        Assertions.assertThat(pedidoCriadoDepoisDeSalvar).isNotNull();
        Assertions.assertThat(pedidoCriadoDepoisDeSalvar.getIdPedido()).isNotNull();
        Assertions.assertThat(pedidoCriadoDepoisDeSalvar.getPreco()).isEqualTo(pedidoCriadoAntesDeSalvar.getPreco());

    }

    @Test
    @DisplayName("deve retornar lista de pedidos se sucesso")
    public void devRetornarListaPedidoQuandoSucesso() {

        Pedido pedidoCriadoAntesDeSalvar = CriadorPedido.criaPedidoAntesDeSalvar();

        Pedido pedidoCriadoDepoisDeSalvar = pedidoRepository.save(pedidoCriadoAntesDeSalvar);

        Iterable<Pedido> pedidoRepositoryAll = pedidoRepository.findAll();

        Assertions.assertThat(pedidoRepositoryAll)
                .isNotEmpty()
                .contains(pedidoCriadoDepoisDeSalvar);

    }
}