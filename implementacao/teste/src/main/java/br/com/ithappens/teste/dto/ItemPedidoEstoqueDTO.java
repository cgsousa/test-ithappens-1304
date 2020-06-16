package br.com.ithappens.teste.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemPedidoEstoqueDTO 
{
	//private PedidoEstoqueDTO pedido;
	private Long pedido_id;
	private ProdutoDTO produto;
	private String status ;    
    private Integer quantidade;
    private Double valorUnitario ;    
    //private Double valorTotal ;
}
