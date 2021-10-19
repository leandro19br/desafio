package br.com.bluesoft.desafio.model;

import javax.persistence.*;
import java.util.List;

/**
 * @project desafio
 * Created by Leandro Saniago on 13/10/2021 - 18:48.
 * Classe representa o objeto Fornecedor
 */
@Entity
public class Fornecedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fornecedorId;
    private String nome;
    private String cnpj;

    public Fornecedor() {
    }

    public Long getFornecedorId() {
        return fornecedorId;
    }

    public void setFornecedorId(Long fornecedorId) {
        this.fornecedorId = fornecedorId;
    }

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


    @Override
    public String toString() {
        return "Fornecedor{" +
                "nome='" + nome + '\'' +
                ", cnpj='" + cnpj + '\'' +
                '}';
    }


    public static final class FornecedorBuilder {
        private String nome;
        private String cnpj;

        private FornecedorBuilder() {
        }

        public static FornecedorBuilder newFornecedor() {
            return new FornecedorBuilder();
        }

        public FornecedorBuilder nome(String nome) {
            this.nome = nome;
            return this;
        }

        public FornecedorBuilder cnpj(String cnpj) {
            this.cnpj = cnpj;
            return this;
        }

        public Fornecedor build() {
            Fornecedor fornecedor = new Fornecedor();
            fornecedor.setNome(nome);
            fornecedor.setCnpj(cnpj);
            return fornecedor;
        }
    }
}
