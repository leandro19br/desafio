package br.com.bluesoft.desafio.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * @project desafio
 * Created by Leandro Saniago on 13/10/2021 - 18:57.
 * Classe representa o objeto Preco
 */
@Entity
public class Preco{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long precoId;
    private BigDecimal preco;
    private int quantidadeMinima;


    public Preco() {
    }

    public Long getPrecoId() {
        return precoId;
    }

    public void setPrecoId(Long precoId) {
        this.precoId = precoId;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public int getQuantidadeMinima() {
        return quantidadeMinima;
    }

    public void setQuantidadeMinima(int quantidadeMinima) {
        this.quantidadeMinima = quantidadeMinima;
    }


}
