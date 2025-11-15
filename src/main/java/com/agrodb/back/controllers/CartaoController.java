package com.agrodb.back.controllers;

import com.agrodb.back.dto.CreatePagamentoCartaoDto;
import com.agrodb.back.models.PagamentoCartao;
import com.agrodb.back.service.CartaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/pagamento")
public class CartaoController {

    private final CartaoService cartaoService;

    public CartaoController(CartaoService cartaoService) {
        this.cartaoService = cartaoService;
    }

//    Create
    @PostMapping("/cartao")
    @PreAuthorize("hasAnyRole('WRITER','ADMIN')")
    public ResponseEntity<Void> criarCartao(@RequestBody CreatePagamentoCartaoDto dto) {
        Integer id = cartaoService.createPagamentoCartao(dto);
        return ResponseEntity.created(URI.create("/pagamentos/cartao/" + id)).build();
    }

//    Read One
    @GetMapping("/cartao/{id_cartao}")
    @PreAuthorize("hasAnyRole('READER','ADMIN')")
    public PagamentoCartao buscarCartao(@PathVariable("id_cartao") Integer id) {
        return cartaoService.buscarCartao(id);
    }
}

