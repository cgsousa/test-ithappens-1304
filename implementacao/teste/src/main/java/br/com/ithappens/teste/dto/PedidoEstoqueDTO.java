package br.com.ithappens.teste.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoEstoqueDTO
{
    private FilialDTO filial;
    private UsuarioDTO usuario;
    private ClienteDTO cliente;
    private String observacao;
    
}
