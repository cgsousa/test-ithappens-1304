package br.com.ithappens.teste.controller;

import br.com.ithappens.teste.dto.PedidoEstoqueDTO;
import br.com.ithappens.teste.dto.ItemPedidoEstoqueDTO;
import br.com.ithappens.teste.dto.FormaPagamentoDTO;
import br.com.ithappens.teste.modelodados.ItensPedido;
import br.com.ithappens.teste.modelodados.PedidoEstoque;
import br.com.ithappens.teste.service.PedidoEstoqueService;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PedidoEstoqueController
{

    private PedidoEstoqueService pedidoEstoqueService;
    

    @Autowired
    public PedidoEstoqueController(PedidoEstoqueService pedidoEstoqueService)
    {
        this.pedidoEstoqueService = pedidoEstoqueService;
    }

    @PostMapping("/pedido")
    @ResponseStatus(HttpStatus.OK)
    public PedidoEstoque criarPedidoEstoque(@Valid @RequestBody PedidoEstoqueDTO pedidoEstoqueDTO)
    {
        return this.pedidoEstoqueService.criarPedidoEstoque(pedidoEstoqueDTO);
    }

    /*    
    @PostMapping("/pedido")
    @ResponseStatus(HttpStatus.OK)
    public PedidoEstoque criarPedidoEstoqueEntrada(@Valid @RequestBody PedidoEstoqueEntradaDTO pedidoEstoqueEntradaDTO)
    {
        return this.pedidoEstoqueService.criarPedidoEstoqueEntrada(pedidoEstoqueEntradaDTO);
    }    

    @PostMapping("/pedido")
    @ResponseStatus(HttpStatus.OK)
    public PedidoEstoque criarPedidoEstoqueSaida(@Valid @RequestBody PedidoEstoqueSaidaDTO pedidoEstoqueSaidaDTO)
    {
        return this.pedidoEstoqueService.criarPedidoEstoqueSaida(pedidoEstoqueSaidaDTO);
    }        
    */
    
    @GetMapping("/pedido/{idPedido}")
    @ResponseStatus(HttpStatus.OK)
    public PedidoEstoque consultarPedidoEstoque(@PathVariable("idPedido") Long id)
    {
        return this.pedidoEstoqueService.consultarPedidoEstoque(id);
    }
    
    @PostMapping("/pedido/item")
    @ResponseStatus(HttpStatus.OK)
    public ItensPedido adicionarItemPedidoEstoque(@Valid @RequestBody ItemPedidoEstoqueDTO itemPedidoEstoqueDTO)
    {
        return this.pedidoEstoqueService.adicionarItemPedidoEstoque(itemPedidoEstoqueDTO);
    }

    /*
    @PatchMapping("/pedido")
    @ResponseStatus(HttpStatus.OK)
    public PedidoEstoque finalizarPedidoEstoque(@Valid @RequestBody FormaPagamentoDTO formaPagamentoDTO)
    {
        return this.pedidoEstoqueService.finalizarPedidoEstoque(formaPagamentoDTO);
    }
    */
    
    
}
