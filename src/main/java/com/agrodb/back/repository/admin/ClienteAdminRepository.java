package com.agrodb.back.repository.admin;

import com.agrodb.back.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteAdminRepository extends JpaRepository<Cliente, Integer> {
}
