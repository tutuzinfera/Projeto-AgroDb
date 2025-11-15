package com.agrodb.back.dto;

import java.math.BigDecimal;


public record CreatePedidoDto(Integer fkIdCliente, BigDecimal valor_total, String status) {
}
