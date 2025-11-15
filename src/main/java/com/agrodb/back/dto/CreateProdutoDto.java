package com.agrodb.back.dto;

public record CreateProdutoDto(String nome, String categoria, float preco_unitario, String unidade_medida) {
}
