package com.agrodb.back.controllers;

import com.agrodb.back.dto.AddItemDto;
import com.agrodb.back.models.ItemPedido;
import com.agrodb.back.models.ItemPedidoId;
import com.agrodb.back.service.ItemPedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pedido")
public class ItemPedidoController {

    public ItemPedidoService ipService;

    public ItemPedidoController(ItemPedidoService ipService) {
        this.ipService = ipService;
    }

    @PostMapping("/item/{id}")
    @PreAuthorize("hasAnyRole('WRITER','ADMIN')")
    public ResponseEntity<ItemPedidoId> addItens(@PathVariable("id") Integer id, @RequestBody AddItemDto addItensDto){
        var itemId = ipService.addItemPedido(id, addItensDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)   // 201 Created
                .body(itemId);
    }

    @GetMapping("/item/{idPedido}")
    @PreAuthorize("hasAnyRole('READER','ADMIN')")
    public List<ItemPedido> listarItens(@PathVariable("idPedido") Integer idPedido) {
        return ipService.listaItens(idPedido);
    }
}
