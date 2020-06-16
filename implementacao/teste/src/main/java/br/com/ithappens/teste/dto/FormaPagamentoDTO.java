package br.com.ithappens.teste.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormaPagamentoDTO 
{
	private Long pedido_id;
	private String pagamento ;    
    private Double valor;    
}
