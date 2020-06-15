package br.com.ithappens.teste.service;

import br.com.ithappens.teste.dto.PedidoEstoqueDTO;
import br.com.ithappens.teste.enums.StatusPedido;
import br.com.ithappens.teste.enums.TipoPedido;
import br.com.ithappens.teste.modelodados.Filial;
import br.com.ithappens.teste.modelodados.Usuario;
import br.com.ithappens.teste.modelodados.PedidoEstoque;
import br.com.ithappens.teste.repository.FilialRepository;
import br.com.ithappens.teste.repository.UsuarioRepository;
import br.com.ithappens.teste.repository.PedidoEstoqueRepository;
import java.time.LocalDateTime;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoEstoqueService
{
    private PedidoEstoqueRepository pedidoEstoqueRepository;
    private FilialRepository filialRepository;
    private UsuarioRepository usuarioRepository;

    @Autowired
    public PedidoEstoqueService(PedidoEstoqueRepository pedidoEstoqueRepository, FilialRepository filialRepository, UsuarioRepository usuarioRepository)
    {
    	this.usuarioRepository = usuarioRepository;
        this.filialRepository = filialRepository;
        this.pedidoEstoqueRepository = pedidoEstoqueRepository;
    }


    @Transactional
    public PedidoEstoque criarPedidoEstoque(PedidoEstoqueDTO pedidoEstoqueDTO)
    {
        Optional<Filial> optionalFilial = this.filialRepository.findById(pedidoEstoqueDTO.getFilial().getId());

        if (!optionalFilial.isPresent())
        {
            throw new IllegalArgumentException("Não existe filial com este código na base");
        }

        PedidoEstoque pedidoEstoque = new PedidoEstoque();
        pedidoEstoque.setFilial(optionalFilial.get());
        pedidoEstoque.setTipoPedido(TipoPedido.SAIDA);
        pedidoEstoque.setDataPedido(LocalDateTime.now());
        //pedidoEstoque.setStatus(StatusPedido.ATIVO);
        return this.pedidoEstoqueRepository.save(pedidoEstoque);
    }
    
    public PedidoEstoque criarPedidoEstoqueEntrada(PedidoEstoqueDTO pedidoEstoqueDTO)
    {
        Optional<Filial> optionalFilial = this.filialRepository.findById(pedidoEstoqueDTO.getFilial().getId());

        if (!optionalFilial.isPresent())
        {
            throw new IllegalArgumentException("Não existe filial com este código na base");
        }

        Optional<Usuario> optionalUsuario = this.usuarioRepository.findById(pedidoEstoqueDTO.getUsuario().getId());
        
        if (!optionalUsuario.isPresent())
        {
            throw new IllegalArgumentException("Não existe usuário com este código na base");
        }        
        
        
        PedidoEstoque pedidoEstoque = new PedidoEstoque();
        pedidoEstoque.setFilial(optionalFilial.get());
        pedidoEstoque.setUsuario(optionalUsuario.get());
        
        
        pedidoEstoque.setTipoPedido(TipoPedido.SAIDA);
        pedidoEstoque.setDataPedido(LocalDateTime.now());
        return this.pedidoEstoqueRepository.save(pedidoEstoque);
    }
        
    public PedidoEstoque consultarPedidoEstoque(Long id)
    {
        Optional<PedidoEstoque> optionalPedidoEstoque = this.pedidoEstoqueRepository.findById(id);

        if (optionalPedidoEstoque.isPresent())
        {
            return optionalPedidoEstoque.get();
        }
        else
        {
            throw new IllegalArgumentException("Não existe pedido com este código na base");
        }

    }
}
