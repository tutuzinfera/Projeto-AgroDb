package com.agrodb.back.dto;

import java.math.BigDecimal;

public record CreatePagamentoDto(Integer idPedido,
                                 BigDecimal valor,
                                 String status
) {
}
