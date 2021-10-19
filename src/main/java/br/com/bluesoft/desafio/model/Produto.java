package br.com.bluesoft.desafio.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Produto {

    @Id
    private String gtin;
    private String nome;

    public Produto() {
    }

    public Produto(String gtin, String nome) {
        this.gtin = gtin;
        this.nome = nome;
    }

    public String getGtin() {
        return gtin;
    }

    public void setGtin(String gtin) {
        this.gtin = gtin;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public static final class ProdutoBuilder {
        private String gtin;
        private String nome;

        private ProdutoBuilder() {
        }

        public static ProdutoBuilder newProduto() {
            return new ProdutoBuilder();
        }

        public ProdutoBuilder gtin(String gtin) {
            this.gtin = gtin;
            return this;
        }

        public ProdutoBuilder nome(String nome) {
            this.nome = nome;
            return this;
        }

        public Produto build() {
            Produto produto = new Produto();
            produto.setGtin(gtin);
            produto.setNome(nome);
            return produto;
        }
    }
}
