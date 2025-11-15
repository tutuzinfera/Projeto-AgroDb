package com.agrodb.back.controllers;

import com.agrodb.back.dto.CreatePedidoDto;
import com.agrodb.back.dto.UpdatePedidoDto;
import com.agrodb.back.models.Pedido;
import com.agrodb.back.service.PedidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    public PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    //  Create
    @PostMapping
    @PreAuthorize("hasAnyRole('WRITER','ADMIN')")
    public ResponseEntity<Pedido> createPedido(@RequestBody CreatePedidoDto createPedidoDto){
        var pedidoId = pedidoService.createPedido(createPedidoDto);
        return ResponseEntity.created(URI.create("/pedidos/" + pedidoId.toString())).build();
    }

    //  Read One
    @GetMapping("{id_pedido}")
    @PreAuthorize("hasAnyRole('READER','ADMIN')")
    public List<Pedido> buscarPedidos(@PathVariable("id_pedido") Integer id) {
        return pedidoService.buscarPedidosDeClientes(id);
    }

    //  Update
    @PostMapping("{id_pedido}")
    @PreAuthorize("hasAnyRole('WRITER','ADMIN')")
    public ResponseEntity<Pedido> updatePedido(@PathVariable("id_pedido") Integer id, @RequestBody UpdatePedidoDto updatePedidoDto) {
        var atualizado = pedidoService.updatePedido(id, updatePedidoDto);
        return ResponseEntity.ok(atualizado);
    }

    //Read All
    @GetMapping
    @PreAuthorize("hasAnyRole('READER','ADMIN')")
    public List<Pedido> listarPedidos() {
        return pedidoService.listarTodos();
    }

    @DeleteMapping("{id_pedido}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Void> deletePedido(@PathVariable("id_pedido") Integer id) {
        pedidoService.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }

}
