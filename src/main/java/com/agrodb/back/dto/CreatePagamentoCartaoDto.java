package com.agrodb.back.dto;

import java.math.BigDecimal;

public record CreatePagamentoCartaoDto(Integer idPedido,
                                       BigDecimal valor,
                                       String nomeTitular,
                                       String bandeira,
                                       String ultimosDigitos,
                                       String dataExpiracao, // ex: "12/27"
                                       String status) {
}
