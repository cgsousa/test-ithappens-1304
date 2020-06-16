package br.com.ithappens.teste.repository;

import br.com.ithappens.teste.modelodados.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface ClienteRepository extends CrudRepository<Cliente, Long> 
{

}