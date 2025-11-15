package com.agrodb.back.repository.reader;

import com.agrodb.back.models.Cliente;
import com.agrodb.back.models.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EnderecoReaderRepository extends JpaRepository<Endereco, String> {
}
