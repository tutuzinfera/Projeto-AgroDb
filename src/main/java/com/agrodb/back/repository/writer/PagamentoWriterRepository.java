package com.agrodb.back.repository.writer;

import com.agrodb.back.models.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoWriterRepository extends JpaRepository<Pagamento, Integer> {
}
