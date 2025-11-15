package com.agrodb.back.dto;

public record CreateClienteDto(String nome, String cpf, String tel, CreateEnderecoDto endereco) {
}
