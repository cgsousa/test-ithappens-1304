package br.com.ithappens.teste.modelodados;

import br.com.ithappens.teste.enums.TipoPedido;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class PedidoEstoque
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "filial_id")
    private Filial filial;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
        
    @Enumerated(EnumType.STRING)
    private TipoPedido tipoPedido;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pedidoEstoque", fetch = FetchType.EAGER)
    private List<ItensPedido> listaItensPedido;
        
    private LocalDateTime dataPedido;

}
