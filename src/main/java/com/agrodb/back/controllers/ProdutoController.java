package com.agrodb.back.controllers;


import com.agrodb.back.dto.CreateProdutoDto;
import com.agrodb.back.dto.UpdateProdutoDto;
import com.agrodb.back.models.Produto;
import com.agrodb.back.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    private ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('WRITER','ADMIN')")
    public ResponseEntity<Produto> createProduto(@Valid @RequestBody CreateProdutoDto createProdutoDto) {
        var produtoId = produtoService.createProduto(createProdutoDto);
        return ResponseEntity.created(URI.create("/clientes/" + produtoId.toString())).build();
    }

    @GetMapping("/{id_produto}")
    @PreAuthorize("hasAnyRole('READER','ADMIN')")
    public ResponseEntity<Produto> buscarProduto(@PathVariable("id_produto") Integer id){
        Produto produto = produtoService.buscarProdutoPeloId(id);
        return ResponseEntity.ok(produto);
    }

    @PostMapping("/{id_produto}")
    @PreAuthorize("hasAnyRole('WRITER','ADMIN')")
    public ResponseEntity<Produto> atualizarProduto(@PathVariable("id_produto") Integer id, @RequestBody UpdateProdutoDto updateProdutoDto) {
        var atualizado = produtoService.updateProduto(id, updateProdutoDto);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id_produto}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Void> deletarProduto(@PathVariable("id_produto") Integer id) {
        produtoService.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('READER','ADMIN')")
    public List<Produto> listarProdutos(){
        return produtoService.listarTodos();
    }
}
