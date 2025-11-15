package com.agrodb.back.repository.writer;

import com.agrodb.back.models.PagamentoCartao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoCartaoWriterRepository  extends JpaRepository<PagamentoCartao, Long> {
}
