package com.agrodb.back.repository.admin;

import com.agrodb.back.models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoAdminRepository extends JpaRepository<Produto, Integer> {
}
