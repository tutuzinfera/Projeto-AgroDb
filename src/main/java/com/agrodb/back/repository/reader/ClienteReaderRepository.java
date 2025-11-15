package com.agrodb.back.repository.reader;

import com.agrodb.back.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteReaderRepository extends JpaRepository<Cliente, Integer> {
}
