package com.agrodb.back.dto;

import java.math.BigDecimal;

public record UpdateProdutoDto(String nome, String categoria, BigDecimal preco_unitario, String unidade_medida ){
}
