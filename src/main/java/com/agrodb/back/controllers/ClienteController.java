package com.agrodb.back.controllers;

import com.agrodb.back.dto.CreateClienteDto;
import com.agrodb.back.dto.UpdateClienteDto;
import com.agrodb.back.models.Cliente;
import com.agrodb.back.repository.reader.ClienteReaderRepository;
import com.agrodb.back.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }
//  Create
    @PostMapping
    @PreAuthorize("hasAnyRole('WRITER','ADMIN')")
    public ResponseEntity<Cliente> createCliente(@Valid @RequestBody CreateClienteDto createClienteDto){
        var clienteID = clienteService.createCliente(createClienteDto);
        return ResponseEntity.created(URI.create("/clientes/" + clienteID.toString())).build();
    }
//  Read One
    @GetMapping("/{id_cliente}")
    @PreAuthorize("hasAnyRole('READER','ADMIN')")
    public ResponseEntity<Cliente> buscarClientePorCpf(@PathVariable("id_cliente") Integer id_cliente){
        var cliente = clienteService.buscarClientePeloId(id_cliente);
        return ResponseEntity.ok(cliente);
    }
//  Update
    @PostMapping("/{id_cliente}")
    @PreAuthorize("hasAnyRole('WRITER','ADMIN')")
    public ResponseEntity<Cliente> atualizarCliente(@PathVariable("id_cliente") Integer id, @RequestBody UpdateClienteDto updateClienteDto) {
        Cliente atualizado = clienteService.updateCliente(id, updateClienteDto);
        return ResponseEntity.ok(atualizado);
    }
//  Read all
    @GetMapping
    @PreAuthorize("hasAnyRole('READER','ADMIN')")
    public List<Cliente> listarClientes(){
       return clienteService.listarTodos();
    }

    @DeleteMapping("{id_cliente}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Void> deletar(@PathVariable("id_cliente") Integer id) {
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
