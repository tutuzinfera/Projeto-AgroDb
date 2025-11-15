package com.agrodb.back.dto;

public record ClienteResponseDto(Integer id,
                                 String nome,
                                 String cpf,
                                 String tel,
                                 String ce) {
}
