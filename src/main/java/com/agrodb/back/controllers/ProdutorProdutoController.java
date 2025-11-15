package com.agrodb.back.controllers;

import com.agrodb.back.dto.VincularProdutorProdutoDto;
import com.agrodb.back.models.ProdutorProduto;
import com.agrodb.back.service.ProdutorProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/ProdutorProduto")
public class ProdutorProdutoController {

    public ProdutorProdutoService ppService;

    public ProdutorProdutoController(ProdutorProdutoService ppService) {
        this.ppService = ppService;
    }

    @PostMapping("/{idProdutor}")
    @PreAuthorize("hasAnyRole('WRITER','ADMIN')")
    public ResponseEntity<ProdutorProduto> vincularProdutorProduto(@PathVariable("idProdutor") Integer id, @RequestBody VincularProdutorProdutoDto dto){
        var ppId = ppService.vincularProdutoAoProdutor(id, dto);
        return ResponseEntity.created(URI.create("/ProdutorProduto/" + ppId.toString())).build();
    }
}
