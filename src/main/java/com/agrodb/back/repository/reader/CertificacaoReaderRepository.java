package com.agrodb.back.repository.reader;

import com.agrodb.back.models.Certificacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificacaoReaderRepository extends JpaRepository<Certificacao, Integer> {
}
