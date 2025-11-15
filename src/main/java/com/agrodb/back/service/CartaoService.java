package com.agrodb.back.service;

import com.agrodb.back.dto.CreatePagamentoCartaoDto;
import com.agrodb.back.models.Pagamento;
import com.agrodb.back.models.PagamentoBoleto;
import com.agrodb.back.models.PagamentoCartao;
import com.agrodb.back.nosql.service.LogOperacaoService;
import com.agrodb.back.repository.reader.CartaoReaderRepository;
import com.agrodb.back.repository.reader.PedidoReaderRepository;
import com.agrodb.back.repository.writer.PagamentoCartaoWriterRepository;
import com.agrodb.back.repository.writer.PagamentoWriterRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Calendar;

@Service
public class CartaoService {

    private final PagamentoWriterRepository pagamentoWriterRepository;
    private final PedidoReaderRepository pedidoReaderRepository;
    private final PagamentoCartaoWriterRepository pagamentoCartaoWriterRepository;
    private final CartaoReaderRepository cartaoReaderRepository;
    private final LogOperacaoService logOperacaoService;

    public CartaoService(PagamentoWriterRepository pagamentoWriterRepository, PedidoReaderRepository pedidoReaderRepository, PagamentoCartaoWriterRepository pagamentoCartaoWriterRepository, CartaoReaderRepository cartaoReaderRepository, LogOperacaoService logOperacaoService) {
        this.pagamentoWriterRepository = pagamentoWriterRepository;
        this.pedidoReaderRepository = pedidoReaderRepository;
        this.pagamentoCartaoWriterRepository = pagamentoCartaoWriterRepository;
        this.cartaoReaderRepository = cartaoReaderRepository;
        this.logOperacaoService = logOperacaoService;
    }

    public Integer createPagamentoCartao(CreatePagamentoCartaoDto dto) {

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

        PagamentoCartao cartao = new PagamentoCartao();
        cartao.setPagamento(pagamentoSalvo);
        cartao.setNomeTitular(dto.nomeTitular());
        cartao.setBandeira(dto.bandeira());
        cartao.setUltimosDigitos(dto.ultimosDigitos());

        PagamentoCartao salvo = pagamentoCartaoWriterRepository.save(cartao);

        logOperacaoService.registrar("DELETE", "PagamentoCartao", cartao.getId_pagamento_cartao().toString());

        return salvo.getId_pagamento_cartao();
    }

    public PagamentoCartao buscarCartao(Integer id) {
        return cartaoReaderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException (
                        HttpStatus.NOT_FOUND,
                        "Pagamento com id" + id + "não encontrado."
                ));
    }
}

