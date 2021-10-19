package br.com.bluesoft.desafio.repository;

import br.com.bluesoft.desafio.model.Fornecedor;
import br.com.bluesoft.desafio.model.Produto;
import org.springframework.data.repository.CrudRepository;

public interface FornecedorRepository extends CrudRepository<Fornecedor,Long> {


}
