package br.com.bluesoft.desafio.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.bluesoft.desafio.model.Produto;

public interface ProdutoRepository extends CrudRepository<Produto, String> {

    @Query(value = "select p from Produto p where p.gtin= ?1")
    Produto findBygtin(String gtin);

}
