package com.agrodb.back.repository.writer;

import com.agrodb.back.models.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoWriterRepository extends JpaRepository<Endereco, String> {
}
