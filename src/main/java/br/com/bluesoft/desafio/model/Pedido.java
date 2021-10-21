package br.com.bluesoft.desafio.model;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * @project desafio
 * Created by Leandro Saniago on 15/10/2021 - 09:18.
 * Classe representa o objeto Pedido
 */

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPedido;
    @OneToMany(cascade=CascadeType.PERSIST)
    private List<Fornecedor> fornecedor;
    @OneToMany(cascade=CascadeType.PERSIST)
    private List<ItemPedido> produtos;
    private int quantidade;
    private BigDecimal preco;
    private BigDecimal total;

    public Pedido() {
    }

    public Long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Long idPedido) {
        this.idPedido = idPedido;
    }

    public List<Fornecedor> getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(List<Fornecedor> fornecedor) {
        this.fornecedor = fornecedor;
    }

    public List<ItemPedido> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<ItemPedido> produtos) {
        this.produtos = produtos;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }


    public static final class PedidoBuilder {
        private Long idPedido;
        private List<Fornecedor> fornecedor;
        private List <ItemPedido> produtos;
        private int quantidade;
        private BigDecimal preco;
        private BigDecimal total;

        private PedidoBuilder() {
        }

        public static PedidoBuilder newPedido() {
            return new PedidoBuilder();
        }

        public PedidoBuilder idPedido(Long idPedido) {
            this.idPedido = idPedido;
            return this;
        }

        public PedidoBuilder fornecedor(List<Fornecedor> fornecedor) {
            this.fornecedor = fornecedor;
            return this;
        }

        public PedidoBuilder produtos(List<ItemPedido> produtos) {
            this.produtos = produtos;
            return this;
        }

        public PedidoBuilder quantidade(int quantidade) {
            this.quantidade = quantidade;
            return this;
        }

        public PedidoBuilder preco(BigDecimal preco) {
            this.preco = preco;
            return this;
        }

        public PedidoBuilder total(BigDecimal total) {
            this.total = total;
            return this;
        }

        public Pedido build() {
            Pedido pedido = new Pedido();
            pedido.setIdPedido(idPedido);
            pedido.setFornecedor(fornecedor);
            pedido.setProdutos(produtos);
            pedido.setQuantidade(quantidade);
            pedido.setPreco(preco);
            pedido.setTotal(total);
            return pedido;
        }
    }
}
