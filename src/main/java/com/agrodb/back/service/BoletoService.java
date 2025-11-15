package com.agrodb.back.service;

import com.agrodb.back.dto.CreatePagamentoBoletoDto;
import com.agrodb.back.models.Pagamento;
import com.agrodb.back.models.PagamentoBoleto;
import com.agrodb.back.nosql.service.LogOperacaoService;
import com.agrodb.back.repository.reader.BoletoReaderRepository;
import com.agrodb.back.repository.reader.PedidoReaderRepository;
import com.agrodb.back.repository.writer.PagamentoBoletoWriterRepository;
import com.agrodb.back.repository.writer.PagamentoWriterRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Calendar;

@Service
public class BoletoService {

    private final PagamentoWriterRepository pagamentoWriterRepository;
    private final PedidoReaderRepository pedidoReaderRepository;
    private final PagamentoBoletoWriterRepository pagamentoBoletoWriterRepository;
    private final BoletoReaderRepository boletoReaderRepository;
    private final LogOperacaoService logOperacaoService;

    public BoletoService(PagamentoWriterRepository pagamentoWriterRepository, PedidoReaderRepository pedidoReaderRepository, PagamentoBoletoWriterRepository pagamentoBoletoWriterRepository, BoletoReaderRepository boletoReaderRepository, LogOperacaoService logOperacaoService) {
        this.pagamentoWriterRepository = pagamentoWriterRepository;
        this.pedidoReaderRepository = pedidoReaderRepository;
        this.pagamentoBoletoWriterRepository = pagamentoBoletoWriterRepository;
        this.boletoReaderRepository = boletoReaderRepository;
        this.logOperacaoService = logOperacaoService;
    }

    public Integer createPagamentoBoleto(CreatePagamentoBoletoDto dto) {

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

        PagamentoBoleto boleto = new PagamentoBoleto();
        boleto.setPagamento(pagamentoSalvo);
        boleto.setLinhaDigitavel(dto.linhaDigitavel());
        boleto.setCodigoBarras(dto.codigoBarras());
        boleto.setDataVencimento(dto.dataVencimento());

        PagamentoBoleto salvo = pagamentoBoletoWriterRepository.save(boleto);

        logOperacaoService.registrar("CREATE", "PagamenteBoleto", boleto.getId_pagamento_boleto().toString());

        return salvo.getId_pagamento_boleto();
    }

    public PagamentoBoleto buscarBoleto(Integer id) {
        return boletoReaderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException (
                        HttpStatus.NOT_FOUND,
                        "Pagamento com id" + id + "não encontrado."
                ));
    }
}

