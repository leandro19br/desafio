package br.com.bluesoft.desafio.requests;

/**
 * @project desafio
 * Created by Leandro Saniago on 15/10/2021 - 09:09.
 */
public class ProdutoDTO {

    private String gtin;

    private String nome;

    private int quantidade;

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

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }


    public static final class ProdutoDTOBuilder {
        private String gtin;
        private String nome;
        private int quantidade;

        private ProdutoDTOBuilder() {
        }

        public static ProdutoDTOBuilder newProdutoDTO() {
            return new ProdutoDTOBuilder();
        }

        public ProdutoDTOBuilder gtin(String gtin) {
            this.gtin = gtin;
            return this;
        }

        public ProdutoDTOBuilder nome(String nome) {
            this.nome = nome;
            return this;
        }

        public ProdutoDTOBuilder quantidade(int quantidade) {
            this.quantidade = quantidade;
            return this;
        }

        public ProdutoDTO build() {
            ProdutoDTO produtoDTO = new ProdutoDTO();
            produtoDTO.setGtin(gtin);
            produtoDTO.setNome(nome);
            produtoDTO.setQuantidade(quantidade);
            return produtoDTO;
        }
    }
}
