package br.com.ithappens.teste.controller;

import br.com.ithappens.teste.dto.PedidoEstoqueDTO;
import br.com.ithappens.teste.modelodados.PedidoEstoque;
import br.com.ithappens.teste.service.PedidoEstoqueService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @GetMapping("/pedido/{idPedido}")
    @ResponseStatus(HttpStatus.OK)
    public PedidoEstoque consultarPedidoEstoque(@PathVariable("idPedido") Long id)
    {
        return this.pedidoEstoqueService.consultarPedidoEstoque(id);
    }

}
