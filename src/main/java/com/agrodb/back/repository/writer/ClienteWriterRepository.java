package com.agrodb.back.repository.writer;

import com.agrodb.back.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClienteWriterRepository extends JpaRepository<Cliente, Integer> {
}
