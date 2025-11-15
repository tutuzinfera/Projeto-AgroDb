package com.agrodb.back.repository.writer;

import com.agrodb.back.models.Certificacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificacaoWriterRepository extends JpaRepository<Certificacao, Integer> {
}
