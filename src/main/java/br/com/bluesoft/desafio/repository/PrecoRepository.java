package br.com.bluesoft.desafio.repository;

import br.com.bluesoft.desafio.model.Preco;
import br.com.bluesoft.desafio.model.Produto;
import org.springframework.data.repository.CrudRepository;

public interface PrecoRepository extends CrudRepository<Preco, Long> {


}
