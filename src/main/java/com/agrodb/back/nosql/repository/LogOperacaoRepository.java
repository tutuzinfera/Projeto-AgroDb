package com.agrodb.back.nosql.repository;

import com.agrodb.back.nosql.models.LogOperacao;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LogOperacaoRepository extends MongoRepository<LogOperacao, String> {
}
