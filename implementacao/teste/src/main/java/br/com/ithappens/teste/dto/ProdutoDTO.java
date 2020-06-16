package br.com.ithappens.teste.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoDTO {
    private Long id;
    private String codigo_barras;
    private String descricao;	

}
