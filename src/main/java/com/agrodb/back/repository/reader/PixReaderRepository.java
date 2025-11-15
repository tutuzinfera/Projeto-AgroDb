package com.agrodb.back.repository.reader;

import com.agrodb.back.models.PagamentoPix;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PixReaderRepository extends JpaRepository<PagamentoPix, Integer> {
}
