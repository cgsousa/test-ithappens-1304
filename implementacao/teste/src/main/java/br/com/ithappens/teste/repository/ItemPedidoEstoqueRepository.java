package br.com.ithappens.teste.repository;

import br.com.ithappens.teste.modelodados.ItensPedido;
import org.springframework.data.repository.CrudRepository;

public interface ItemPedidoEstoqueRepository extends CrudRepository<ItensPedido, Long>
{

}
