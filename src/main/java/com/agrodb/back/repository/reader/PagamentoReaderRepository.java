package com.agrodb.back.repository.reader;

import com.agrodb.back.models.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoReaderRepository extends JpaRepository<Pagamento, Long> {
}
