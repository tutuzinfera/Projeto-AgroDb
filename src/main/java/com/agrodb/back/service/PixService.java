package com.agrodb.back.service;

import com.agrodb.back.dto.CreatePagamentoPixDto;
import com.agrodb.back.models.Pagamento;
import com.agrodb.back.models.PagamentoPix;
import com.agrodb.back.nosql.service.LogOperacaoService;
import com.agrodb.back.repository.reader.PedidoReaderRepository;
import com.agrodb.back.repository.reader.PixReaderRepository;
import com.agrodb.back.repository.writer.PagamentoWriterRepository;
import com.agrodb.back.repository.writer.PixWriterRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Calendar;

@Service
public class PixService {

    private final PagamentoWriterRepository pagamentoWriterRepository;
    private final PedidoReaderRepository pedidoReaderRepository;
    private final PixWriterRepository pagamentoPixWriterRepository;
    private final PixReaderRepository pixReaderRepository;
    private final LogOperacaoService logOperacaoService;

    public PixService(PagamentoWriterRepository pagamentoWriterRepository,
                      PedidoReaderRepository pedidoReaderRepository,
                      PixWriterRepository pagamentoPixWriterRepository, PixReaderRepository pixReaderRepository, LogOperacaoService logOperacaoService) {
        this.pagamentoWriterRepository = pagamentoWriterRepository;
        this.pedidoReaderRepository = pedidoReaderRepository;
        this.pagamentoPixWriterRepository = pagamentoPixWriterRepository;
        this.pixReaderRepository = pixReaderRepository;
        this.logOperacaoService = logOperacaoService;
    }

    public Integer criarPagamentoPix(CreatePagamentoPixDto dto) {

        var pedido = pedidoReaderRepository.findById(dto.idPedido())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Pedido de id " + dto.idPedido() + " não encontrado."
                ));

        Pagamento pagamento = new Pagamento();
        pagamento.setPedido(pedido);
        pagamento.setValor(dto.valor());
        pagamento.setDataPagamento(Calendar.getInstance());
        pagamento.setStatus(dto.status() != null ? dto.status() : "Pendente");

        Pagamento pagamentoSalvo = pagamentoWriterRepository.save(pagamento);

        PagamentoPix pagamentoPix = new PagamentoPix();
        pagamentoPix.setPagamento(pagamentoSalvo);
        pagamentoPix.setChavePix(dto.chavePix());
        pagamentoPix.setNomeTitular(dto.nomeTitular());

        PagamentoPix pixSalvo = pagamentoPixWriterRepository.save(pagamentoPix);

        logOperacaoService.registrar("CREATE", "PagamentoPix", pagamentoPix.getId_pagamento_pix().toString());

        return pixSalvo.getId_pagamento_pix();
    }

    public PagamentoPix buscarPix(Integer id) {
        return pixReaderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException (
                        HttpStatus.NOT_FOUND,
                        "Pix com id" + id + "não encontrado."
                ));
    }
}
