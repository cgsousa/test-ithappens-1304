package br.com.ithappens.teste.modelodados;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity

public class Estoque
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "filial_id")
    private Filial filial;
    
    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;
    
    private Integer saldo;    

}
