package com.agrodb.back.dto;

public record CreateProdutorDto(String nome, String cpf_cnpj, String tel, CreateCertificacaoDto certificacao) {
}
