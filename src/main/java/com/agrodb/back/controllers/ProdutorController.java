package com.agrodb.back.controllers;


import com.agrodb.back.dto.CreateProdutorDto;
import com.agrodb.back.dto.UpdateProdutorDto;
import com.agrodb.back.models.Produtor_rural;
import com.agrodb.back.service.ProdutorService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/produtor")
public class ProdutorController {

    private ProdutorService produtorService;

    public ProdutorController(ProdutorService produtorService) {
        this.produtorService = produtorService;
    }

    //  Create
    @PostMapping
    @PreAuthorize("hasAnyRole('WRITER','ADMIN')")
    public ResponseEntity<Produtor_rural> createProdutor(@RequestBody CreateProdutorDto createProdutorDto){
        var produtorId = produtorService.createProdutor(createProdutorDto);
        return ResponseEntity.created(URI.create("/produtor/" + produtorId.toString())).build();
    }

    //  Read One
    @GetMapping("/{id_rural}")
    @PreAuthorize("hasAnyRole('READER','ADMIN')")
    public ResponseEntity<Produtor_rural> buscarProdutor(@PathVariable("id_rural") Integer id) {
        var produtor = produtorService.buscarProdutorPeloId(id);
        return ResponseEntity.ok(produtor);
    }

    //  Update
    @PostMapping("/{id_rural}")
    @PreAuthorize("hasAnyRole('WRITER','ADMIN')")
    public ResponseEntity<Produtor_rural> atualizarProdutor(@PathVariable("id_rural") Integer id, @RequestBody UpdateProdutorDto updateProdutorDto) {
        var atualizado = produtorService.updateProdutor(id, updateProdutorDto);
        return ResponseEntity.ok(atualizado);
    }

    //  Read all
    @GetMapping()
    @PreAuthorize("hasAnyRole('READER','ADMIN')")
    public List<Produtor_rural> listarProdutores() {
        return produtorService.listarTodos();
    }

    // Delete
    @DeleteMapping("/{id_rural}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Void> deletarProdutor(@PathVariable(("id_rural")) Integer id) {
        produtorService.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }
}
