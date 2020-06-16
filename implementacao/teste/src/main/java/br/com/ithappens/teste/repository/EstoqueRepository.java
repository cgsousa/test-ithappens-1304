package br.com.ithappens.teste.repository;

import br.com.ithappens.teste.modelodados.Estoque;
import org.springframework.data.repository.CrudRepository;

public interface EstoqueRepository extends CrudRepository<Estoque, Long>
{

}
