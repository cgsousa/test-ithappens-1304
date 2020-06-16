package br.com.ithappens.teste.repository;

import br.com.ithappens.teste.modelodados.Produto;
import org.springframework.data.repository.CrudRepository;

public interface ProdutoRepository extends CrudRepository<Produto, Long>
{

}
