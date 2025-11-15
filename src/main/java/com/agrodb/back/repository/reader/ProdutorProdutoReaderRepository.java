package com.agrodb.back.repository.reader;

import com.agrodb.back.models.ProdutorProduto;
import com.agrodb.back.models.ProdutorProdutoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutorProdutoReaderRepository extends JpaRepository<ProdutorProduto, ProdutorProdutoId> {
}
