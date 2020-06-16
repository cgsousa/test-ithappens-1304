package br.com.ithappens.teste.modelodados;

import br.com.ithappens.teste.enums.StatusFormaPagamento;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class FormaPagamento 
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pedido_estoque_id")
    private PedidoEstoque pedidoEstoque;    
    
    private StatusFormaPagamento pagamento;
    
    private Double valor ;
	
}
