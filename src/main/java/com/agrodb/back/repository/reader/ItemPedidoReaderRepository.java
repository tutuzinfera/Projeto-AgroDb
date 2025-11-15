package com.agrodb.back.repository.reader;

import com.agrodb.back.models.ItemPedido;
import com.agrodb.back.models.ItemPedidoId;
import com.agrodb.back.models.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemPedidoReaderRepository extends JpaRepository<ItemPedido, ItemPedidoId> {
    List<ItemPedido> findByPedido(Pedido pedido);
}
