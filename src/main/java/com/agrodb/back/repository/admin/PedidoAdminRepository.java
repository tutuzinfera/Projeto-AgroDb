package com.agrodb.back.repository.admin;

import com.agrodb.back.models.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoAdminRepository extends JpaRepository<Pedido, Integer> {
}
