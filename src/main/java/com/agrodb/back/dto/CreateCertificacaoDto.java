package com.agrodb.back.dto;

import java.util.Calendar;

public record CreateCertificacaoDto(String orgaoEmissor, Calendar validade) {
}
