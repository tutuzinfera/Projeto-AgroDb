package com.agrodb.back.dto;

import java.math.BigDecimal;

public record UpdatePedidoDto(String status, BigDecimal valorTotal) {
}
