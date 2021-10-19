package br.com.bluesoft.desafio.requests;

import br.com.bluesoft.desafio.model.Preco;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.OneToMany;
import java.util.List;

/**
 * @project desafio
 * Created by Leandro Saniago on 15/10/2021 - 09:10.
 */
public class FornecedorDTO {

    private String nome;
    private String cnpj;
    @JsonManagedReference
    private List<PrecoDTO> precos;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public List<PrecoDTO> getPrecos() {
        return precos;
    }

    public void setPrecos(List<PrecoDTO> precos) {
        this.precos = precos;
    }


    public static final class FornecedorDTOBuilder {
        private String nome;
        private String cnpj;
        private List<PrecoDTO> precos;

        private FornecedorDTOBuilder() {
        }

        public static FornecedorDTOBuilder newFornecedorDTO() {
            return new FornecedorDTOBuilder();
        }

        public FornecedorDTOBuilder nome(String nome) {
            this.nome = nome;
            return this;
        }

        public FornecedorDTOBuilder cnpj(String cnpj) {
            this.cnpj = cnpj;
            return this;
        }

        public FornecedorDTOBuilder precos(List<PrecoDTO> precos) {
            this.precos = precos;
            return this;
        }

        public FornecedorDTO build() {
            FornecedorDTO fornecedorDTO = new FornecedorDTO();
            fornecedorDTO.setNome(nome);
            fornecedorDTO.setCnpj(cnpj);
            fornecedorDTO.setPrecos(precos);
            return fornecedorDTO;
        }
    }
}
