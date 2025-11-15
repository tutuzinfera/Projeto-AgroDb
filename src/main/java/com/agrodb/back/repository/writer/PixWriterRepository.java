package com.agrodb.back.repository.writer;

import com.agrodb.back.models.PagamentoPix;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PixWriterRepository extends JpaRepository<PagamentoPix, Integer> {
}
