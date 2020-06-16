package br.com.ithappens.teste.service;

import br.com.ithappens.teste.dto.PedidoEstoqueDTO;
import br.com.ithappens.teste.dto.ItemPedidoEstoqueDTO;
import br.com.ithappens.teste.dto.FormaPagamentoDTO;
import br.com.ithappens.teste.enums.TipoPedido;
import br.com.ithappens.teste.enums.StatusItemPedido;
import br.com.ithappens.teste.enums.StatusFormaPagamento;
import br.com.ithappens.teste.modelodados.Filial;
import br.com.ithappens.teste.modelodados.Usuario;
import br.com.ithappens.teste.modelodados.Cliente;
import br.com.ithappens.teste.modelodados.PedidoEstoque;
import br.com.ithappens.teste.modelodados.ItensPedido;
import br.com.ithappens.teste.modelodados.Produto;
import br.com.ithappens.teste.modelodados.Estoque;
import br.com.ithappens.teste.modelodados.FormaPagamento;
import br.com.ithappens.teste.repository.FilialRepository;
import br.com.ithappens.teste.repository.UsuarioRepository;
import br.com.ithappens.teste.repository.ClienteRepository;
import br.com.ithappens.teste.repository.PedidoEstoqueRepository;
import br.com.ithappens.teste.repository.ItemPedidoEstoqueRepository;
import br.com.ithappens.teste.repository.ProdutoRepository;
import br.com.ithappens.teste.repository.EstoqueRepository;
import br.com.ithappens.teste.repository.FormaPagamentoRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
//import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoEstoqueService
{
    private PedidoEstoqueRepository pedidoEstoqueRepository;
    private FilialRepository filialRepository;
    private UsuarioRepository usuarioRepository;
    private ClienteRepository clienteRepository;
    private ItemPedidoEstoqueRepository itemPedidoEstoqueRepository;
    private ProdutoRepository produtoRepository; 
    private EstoqueRepository estoqueRepository;
    private FormaPagamentoRepository formaPagamentoRepository;
    
    @Autowired
    public PedidoEstoqueService(PedidoEstoqueRepository pedidoEstoqueRepository, FilialRepository filialRepository, UsuarioRepository usuarioRepository, ClienteRepository clienteRepository,
    		ItemPedidoEstoqueRepository itemPedidoEstoqueRepository, ProdutoRepository produtoRepository, EstoqueRepository estoqueRepository, 
    		FormaPagamentoRepository formaPagamentoRepository)
    {    	    	
        this.filialRepository = filialRepository;
        this.usuarioRepository = usuarioRepository;
        this.clienteRepository = clienteRepository;
        this.pedidoEstoqueRepository = pedidoEstoqueRepository;
        this.itemPedidoEstoqueRepository = itemPedidoEstoqueRepository;
        this.produtoRepository = produtoRepository;
        this.estoqueRepository = estoqueRepository;
        this.formaPagamentoRepository = formaPagamentoRepository;
    }

    @Transactional
    public PedidoEstoque criarPedidoEstoque(PedidoEstoqueDTO pedidoEstoqueDTO)
    {
        //Optional<Filial> optionalFilial = this.filialRepository.findById(pedidoEstoqueDTO.getFilial_id());
    	Optional<Filial> optionalFilial;
    	Optional<Usuario> optionalUsuario;
    	Optional<Cliente> optionalCliente = null;
    	
    	optionalFilial = this.filialRepository.findById(pedidoEstoqueDTO.getFilial_id());
        if (!optionalFilial.isPresent())
        {
            throw new IllegalArgumentException("Não existe filial com este código na base");
        }

        optionalUsuario = this.usuarioRepository.findById(pedidoEstoqueDTO.getUsuario_id());        
        if (!optionalUsuario.isPresent())
        {
            throw new IllegalArgumentException("Não existe usuário com este código na base");
        }                
        
        if(TipoPedido.valueOf(pedidoEstoqueDTO.getTipo()) ==TipoPedido.SAIDA)
        {
	        optionalCliente = this.clienteRepository.findById(pedidoEstoqueDTO.getCliente_id());
	        
	        if (!optionalCliente.isPresent())
	        {
	            throw new IllegalArgumentException("Não existe cliente com este código na base");
	        }
        }

        PedidoEstoque pedidoEstoque = new PedidoEstoque();
        pedidoEstoque.setFilial(optionalFilial.get());
        pedidoEstoque.setUsuario(optionalUsuario.get());
        //
        // tipo saida
        if(TipoPedido.valueOf(pedidoEstoqueDTO.getTipo()) ==TipoPedido.SAIDA)
        {
        	//
        	// seta cliente
        	pedidoEstoque.setCliente(optionalCliente.get());
        	pedidoEstoque.setTipoPedido(TipoPedido.SAIDA);
        	pedidoEstoque.setObservacao(pedidoEstoqueDTO.getObservacao());
        //
        // tipo entrada
        }else{
        	pedidoEstoque.setTipoPedido(TipoPedido.ENTRADA);        	
        }
        
        //pedidoEstoque.setDataPedido(LocalDateTime.now());
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

    public PedidoEstoque finalizarPedidoEstoque(FormaPagamentoDTO formaPagamentoDTO)
    {
    	Optional<PedidoEstoque> optionalPedidoEstoque = this.pedidoEstoqueRepository.findById(formaPagamentoDTO.getPedido_id());
    	if (!optionalPedidoEstoque.isPresent())
        {
            throw new IllegalArgumentException("Não existe pedido com este código na base");
        }
    	    	
    	PedidoEstoque pedidoEstoque = optionalPedidoEstoque.get();
    	    	
    	//
    	// valida itens
    	if(pedidoEstoque.getListaItensPedido().isEmpty())
        {
            throw new IllegalArgumentException("Não existe itens para finalizar o pedido");
        }    	
    	
    	//
    	// totaliza pedido    	
    	Double valorTotal = 0.0;    
    	for ( ItensPedido i : pedidoEstoque.getListaItensPedido()) 
    	{ 
    		valorTotal +=i.getQuantidade() * i.getValorUnitario(); 
    	}    	    	
    	pedidoEstoque.setValorTotal(valorTotal);
    	
    	//
    	// seta forma de pagamento
    	FormaPagamento formaPagamento = new FormaPagamento();
    	formaPagamento.setPedidoEstoque(pedidoEstoque);    	
    	formaPagamento.setPagamento(formaPagamentoDTO.getPagamento());
    	this.formaPagamentoRepository.save(formaPagamento);
    	
        return this.pedidoEstoqueRepository.save(pedidoEstoque);

    }
        
    public ItensPedido adicionarItemPedidoEstoque(ItemPedidoEstoqueDTO itemPedidoEstoqueDTO)
    {
        Optional<PedidoEstoque> optionalPedidoEstoque = this.pedidoEstoqueRepository.findById(itemPedidoEstoqueDTO.getPedido_id());

        if(!optionalPedidoEstoque.isPresent())        	
        {
            throw new IllegalArgumentException("Não existe pedido com este código na base");
        }
        
        //
        // chk item de entrada
        boolean entrada = optionalPedidoEstoque.get().getTipoPedido() == TipoPedido.ENTRADA;        
        
        //
        // valida qtde
        if(itemPedidoEstoqueDTO.getQuantidade()>0)
        {
        	//
        	// chk estoque
        	//Optional<Estoque> optionalEstoque = this.estoqueRepository.findById(itemPedidoEstoqueDTO.getPedido_id());
        	//if(!entrada)
        	//{
        	//	throw new IllegalArgumentException("Ainda não implementado");
        	//}
        }else {
        	throw new IllegalArgumentException("Não permite quantidade zerada");
        }
        
                
        Optional<Produto> optionalProduto = this.produtoRepository.findById(itemPedidoEstoqueDTO.getProduto().getId());
        //
        // produto inexistente
        if(!optionalProduto.isPresent())
        {
        	//
        	// inclui novo produto
        	Produto produto = new Produto();
        	produto.setCod_barras(itemPedidoEstoqueDTO.getProduto().getCodigo_barras());
        	produto.setDescricao(itemPedidoEstoqueDTO.getProduto().getDescricao());
        	this.produtoRepository.save(produto) ; 
        	
        	optionalProduto.get().setId(produto.getId());
        	optionalProduto.get().setCod_barras(produto.getCod_barras());
        	optionalProduto.get().setDescricao(produto.getDescricao());
        	        	
        	//
        	// inicializa o estoque do produto na filial
        	Estoque estoque = new Estoque();
        	estoque.setFilial(optionalPedidoEstoque.get().getFilial());
        	estoque.setSaldo(itemPedidoEstoqueDTO.getQuantidade());
        	this.estoqueRepository.save(estoque);
        	
        //
        // produto existente        	
        }else{
        	//
        	// soma estoque do produto na filial
        	if(entrada)
        	{
        		//
        		//        		
        		//Estoque estoque = this.estoqueRepository.findById(id)
        	}
        }
                
        ItensPedido itensPedido = new ItensPedido();
        itensPedido.setPedidoEstoque(optionalPedidoEstoque.get());
        itensPedido.setProduto(optionalProduto.get());
        itensPedido.setStatus(StatusItemPedido.ATIVO);
        itensPedido.setQuantidade(itemPedidoEstoqueDTO.getQuantidade());
        itensPedido.setValorUnitario(itemPedidoEstoqueDTO.getValorUnitario());                
    	return this.itemPedidoEstoqueRepository.save(itensPedido);
    }
    
    
}
