package com.agrodb.back.dto;

import com.agrodb.back.models.Cliente;

import java.util.Date;

public record RegisterPedidoDto(float valor_total, Cliente fk_idCliente) {
}
