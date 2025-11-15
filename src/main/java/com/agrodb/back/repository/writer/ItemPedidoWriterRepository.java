package com.agrodb.back.repository.writer;

import com.agrodb.back.models.ItemPedido;
import com.agrodb.back.models.ItemPedidoId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemPedidoWriterRepository extends JpaRepository<ItemPedido, ItemPedidoId> {
}
