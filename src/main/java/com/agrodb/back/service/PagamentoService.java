package com.agrodb.back.service;

import com.agrodb.back.dto.CreatePagamentoDto;
import com.agrodb.back.models.Pagamento;
import com.agrodb.back.models.Pedido;
import com.agrodb.back.nosql.service.LogOperacaoService;
import com.agrodb.back.repository.reader.PedidoReaderRepository;
import com.agrodb.back.repository.writer.PagamentoWriterRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Calendar;

@Service
public class PagamentoService {

    private final PagamentoWriterRepository pagamentoWriterRepository;
    private final PagamentoWriterRepository pagamentoReaderRepository;
    private final PedidoReaderRepository pedidoReaderRepository;
    private final LogOperacaoService logOperacaoService;

    public PagamentoService(PagamentoWriterRepository pagamentoWriterRepository, PagamentoWriterRepository pagamentoReaderRepository,
                            PedidoReaderRepository pedidoReaderRepository, LogOperacaoService logOperacaoService) {
        this.pagamentoWriterRepository = pagamentoWriterRepository;
        this.pagamentoReaderRepository = pagamentoReaderRepository;
        this.pedidoReaderRepository = pedidoReaderRepository;
        this.logOperacaoService = logOperacaoService;
    }

    public Integer createPagamento(CreatePagamentoDto dto) {

        Pedido pedido = pedidoReaderRepository.findById(dto.idPedido())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Pedido de id " + dto.idPedido() + " não encontrado."
                ));

        Pagamento pagamento = new Pagamento();
        pagamento.setPedido(pedido);
        pagamento.setValor(dto.valor());
        pagamento.setStatus(dto.status() != null ? dto.status() : "Pendente");
        pagamento.setDataPagamento(Calendar.getInstance());

        Pagamento pagSaved = pagamentoWriterRepository.save(pagamento);

        logOperacaoService.registrar("CREATE", "Pagamento", pagamento.getId_pagamento().toString());

        return pagSaved.getId_pagamento();
    }

    public Pagamento buscarPagamentoPeloId(Integer id) {
        return pagamentoReaderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException (
                        HttpStatus.NOT_FOUND,
                        "Pagamento com id" + id + "não encontrado."
                ));
    }
}
