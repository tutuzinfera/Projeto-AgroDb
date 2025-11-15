package com.agrodb.back.service;

import com.agrodb.back.dto.CreatePedidoDto;
import com.agrodb.back.dto.UpdatePedidoDto;
import com.agrodb.back.dto.UpdateProdutorDto;
import com.agrodb.back.models.Cliente;
import com.agrodb.back.models.Pedido;
import com.agrodb.back.models.Produto;
import com.agrodb.back.models.Produtor_rural;
import com.agrodb.back.nosql.service.LogOperacaoService;
import com.agrodb.back.repository.admin.PedidoAdminRepository;
import com.agrodb.back.repository.reader.ClienteReaderRepository;
import com.agrodb.back.repository.reader.PedidoReaderRepository;
import com.agrodb.back.repository.writer.PedidoWriterRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Calendar;
import java.util.List;

@Service
public class PedidoService {

    private final PedidoReaderRepository pedidoReaderRepository;
    private final PedidoWriterRepository pedidoWriterRepository;
    private final PedidoAdminRepository pedidoAdminRepository;
    private final ClienteReaderRepository clienteReaderRepository;
    private final LogOperacaoService logOperacaoService;


    public PedidoService(PedidoReaderRepository pedidoReaderRepository, PedidoWriterRepository pedidoWriterRepository, PedidoAdminRepository pedidoAdminRepository, ClienteReaderRepository clienteReaderRepository, LogOperacaoService logOperacaoService) {
        this.pedidoReaderRepository = pedidoReaderRepository;
        this.pedidoWriterRepository = pedidoWriterRepository;
        this.pedidoAdminRepository = pedidoAdminRepository;
        this.clienteReaderRepository = clienteReaderRepository;
        this.logOperacaoService = logOperacaoService;
    }

//  Create
    public Integer createPedido(CreatePedidoDto dto){

        Cliente cliente = clienteReaderRepository.findById(dto.fkIdCliente())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Cliente com id " + dto.fkIdCliente() + " não encontrado."
                ));

        Pedido pedido = new Pedido();
        pedido.setValor_total(dto.valor_total());
        pedido.setCliente(cliente);
        pedido.setStatus(dto.status() != null ? dto.status() : "Pendente");
        pedido.setData_pedido(Calendar.getInstance());

        Pedido salvo = pedidoWriterRepository.save(pedido);

        logOperacaoService.registrar("CREATE", "Pedido", pedido.getId_pedido().toString());

        return salvo.getId_pedido();
    }

//  Find by Id
    public List<Pedido> buscarPedidosDeClientes(Integer id){
        Cliente clienteId = clienteReaderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Cliente com id " + id + "não encontrado."
                ));

        return pedidoReaderRepository.findByCliente(clienteId);
    }

//  Find all
    public List<Pedido> listarTodos() {
        return pedidoReaderRepository.findAll();
    }

//  Update
    public Pedido updatePedido(Integer id, UpdatePedidoDto dto) {

        Pedido existente = pedidoReaderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Produtor com id " + id + "não encontrado."
                ));

        if (dto.status() != null) {
            existente.setStatus(dto.status());
        }
        if (dto.valorTotal() != null) {
            existente.setValor_total(dto.valorTotal());
        }

        Pedido atualizado = pedidoWriterRepository.save(existente);

        logOperacaoService.registrar("UPDATE", "Pedido", id.toString());

        return atualizado;
    }
//  Delete
    public void deletarPorId(Integer id){
        Pedido deletar = pedidoReaderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Pedido com id " + id + "não encontrado."
                ));

        pedidoAdminRepository.deleteById(id);
    }
}
