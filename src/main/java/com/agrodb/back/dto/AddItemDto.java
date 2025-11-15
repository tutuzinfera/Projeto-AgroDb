package com.agrodb.back.dto;

import java.math.BigDecimal;

public record AddItemDto(Integer idProduto, Integer idProdutor, int quantidade, BigDecimal precoUnitario) {
}
