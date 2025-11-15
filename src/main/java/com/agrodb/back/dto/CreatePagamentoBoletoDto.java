package com.agrodb.back.dto;

import java.math.BigDecimal;
import java.util.Calendar;

public record CreatePagamentoBoletoDto(Integer idPedido,
                                       BigDecimal valor,
                                       String linhaDigitavel,
                                       String codigoBarras,
                                       Calendar dataVencimento,
                                       String status) {
}
