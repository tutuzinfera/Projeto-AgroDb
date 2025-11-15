package com.agrodb.back.repository.reader;

import com.agrodb.back.models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoReaderRepository extends JpaRepository<Produto, Integer> {
}
