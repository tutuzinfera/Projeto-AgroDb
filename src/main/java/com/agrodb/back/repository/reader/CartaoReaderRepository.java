package com.agrodb.back.repository.reader;

import com.agrodb.back.models.PagamentoCartao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartaoReaderRepository extends JpaRepository<PagamentoCartao, Integer> {
}
