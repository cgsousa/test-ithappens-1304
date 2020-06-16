package br.com.ithappens.teste.dto;

import br.com.ithappens.teste.enums.StatusFormaPagamento;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormaPagamentoDTO 
{
	private Long pedido_id;
	private StatusFormaPagamento pagamento ;    
    private Double valor;    
}
