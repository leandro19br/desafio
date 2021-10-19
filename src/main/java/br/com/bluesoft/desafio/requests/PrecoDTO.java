package br.com.bluesoft.desafio.requests;

import br.com.bluesoft.desafio.model.Preco;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * @project desafio
 * Created by Leandro Saniago on 15/10/2021 - 09:10.
 */
public class PrecoDTO implements Comparable<PrecoDTO> {


    private BigDecimal preco;
    @JsonProperty("quantidade_minima")
    private int quantidadeMinima;
    @JsonBackReference
    private FornecedorDTO fornecedorDTO;

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

    public FornecedorDTO getFornecedorDTO() {
        return fornecedorDTO;
    }

    public void setFornecedorDTO(FornecedorDTO fornecedorDTO) {
        this.fornecedorDTO = fornecedorDTO;
    }

    @Override
    public int compareTo(PrecoDTO o) {
        return  this.preco.compareTo(o.preco);
    }


    public static final class PrecoDTOBuilder {
        private BigDecimal preco;
        private int quantidadeMinima;
        private FornecedorDTO fornecedorDTO;

        private PrecoDTOBuilder() {
        }

        public static PrecoDTOBuilder newPrecoDTO() {
            return new PrecoDTOBuilder();
        }

        public PrecoDTOBuilder preco(BigDecimal preco) {
            this.preco = preco;
            return this;
        }

        public PrecoDTOBuilder quantidadeMinima(int quantidadeMinima) {
            this.quantidadeMinima = quantidadeMinima;
            return this;
        }

        public PrecoDTOBuilder fornecedorDTO(FornecedorDTO fornecedorDTO) {
            this.fornecedorDTO = fornecedorDTO;
            return this;
        }

        public PrecoDTO build() {
            PrecoDTO precoDTO = new PrecoDTO();
            precoDTO.setPreco(preco);
            precoDTO.setQuantidadeMinima(quantidadeMinima);
            precoDTO.setFornecedorDTO(fornecedorDTO);
            return precoDTO;
        }
    }
}
