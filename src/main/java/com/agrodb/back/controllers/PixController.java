package com.agrodb.back.controllers;

import com.agrodb.back.dto.CreatePagamentoPixDto;
import com.agrodb.back.models.PagamentoPix;
import com.agrodb.back.service.PixService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/pagamento")
public class PixController {

    private final PixService pixService;

    public PixController(PixService pixService) {
        this.pixService = pixService;
    }

    @PostMapping("/pix")
    @PreAuthorize("hasAnyRole('WRITER','ADMIN')")
    public ResponseEntity<PagamentoPix> criarPix(@RequestBody CreatePagamentoPixDto dto) {
        Integer id = pixService.criarPagamentoPix(dto);
        return ResponseEntity.created(URI.create("/pagamentos/pix/" + id.toString())).build();
    }

    @GetMapping("/pix/{id_pix}")
    @PreAuthorize("hasAnyRole('READER','ADMIN')")
    public PagamentoPix buscarPix(@PathVariable("id_pix") Integer id){
        return pixService.buscarPix(id);
    }
}

