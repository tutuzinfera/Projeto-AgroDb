package com.agrodb.back.repository.reader;

import com.agrodb.back.models.PagamentoBoleto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoletoReaderRepository extends JpaRepository<PagamentoBoleto, Integer> {
}
