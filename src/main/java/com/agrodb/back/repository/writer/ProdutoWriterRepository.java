package com.agrodb.back.repository.writer;

import com.agrodb.back.models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoWriterRepository extends JpaRepository<Produto, Integer> {
}
