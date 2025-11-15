package com.agrodb.back.controllers;

import com.agrodb.back.dto.CreatePagamentoBoletoDto;
import com.agrodb.back.models.PagamentoBoleto;
import com.agrodb.back.service.BoletoService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/pagamento")
public class BoletoController {

    private final BoletoService boletoService;

    public BoletoController(BoletoService boletoService) {
        this.boletoService = boletoService;
    }

//    Create
    @PostMapping("/boleto")
    @PreAuthorize("hasAnyRole('WRITER','ADMIN')")
    public ResponseEntity<Void> criarBoleto(@RequestBody CreatePagamentoBoletoDto dto) {
        Integer id = boletoService.createPagamentoBoleto(dto);
        return ResponseEntity.created(URI.create("/pagamentos/boleto/" + id)).build();
    }

//    Read One
    @GetMapping("/boleto/{id_boleto}")
    @PreAuthorize("hasAnyRole('READER','ADMIN')")
    public PagamentoBoleto buscarBoleto(@PathVariable("id_boleto") Integer id) {
        return boletoService.buscarBoleto(id);
    }
}

