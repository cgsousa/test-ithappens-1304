package br.com.ithappens.teste.repository;

import br.com.ithappens.teste.modelodados.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> 
{

}
