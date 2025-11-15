package com.agrodb.back.controllers;

import com.agrodb.back.dto.CreatePagamentoDto;
import com.agrodb.back.models.Pagamento;
import com.agrodb.back.service.PagamentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/pagamento")
public class PagamentoController {

    private final PagamentoService pagamentoService;

    public PagamentoController(PagamentoService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('WRITER','ADMIN')")
    public ResponseEntity<Void> criarPagamento(@RequestBody CreatePagamentoDto dto) {
        var pagId = pagamentoService.createPagamento(dto);
        return ResponseEntity.created(URI.create("/pagamentos/" + pagId.toString())).build();
    }

    @GetMapping("/{id_pagamento}")
    @PreAuthorize("hasAnyRole('READER','ADMIN')")
    public Pagamento buscarPagamento(@PathVariable("id_pagamento") Integer id) {
        return pagamentoService.buscarPagamentoPeloId(id);
    }
}

