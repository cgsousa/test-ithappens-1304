package br.com.ithappens.teste.modelodados;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.com.ithappens.teste.enums.StatusItemPedido;
import lombok.Data;

@Data
@Entity
public class ItensPedido
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pedido_estoque_id")
    private PedidoEstoque pedidoEstoque;    
    
    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;
    
    @Enumerated(EnumType.STRING)
    private  StatusItemPedido status;
    
    private Integer quantidade;
    
    private Double valorUnitario ;
    
    private Double valorTotal ;
        
}
