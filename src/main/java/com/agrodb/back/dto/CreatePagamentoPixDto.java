package com.agrodb.back.dto;

import java.math.BigDecimal;

public record CreatePagamentoPixDto(Integer idPedido,
                                    BigDecimal valor,
                                    String chavePix,
                                    String status,
                                    String nomeTitular) {
}
