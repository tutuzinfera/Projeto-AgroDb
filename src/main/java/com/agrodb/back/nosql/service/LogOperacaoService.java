package com.agrodb.back.nosql.service;

import com.agrodb.back.nosql.models.LogOperacao;
import com.agrodb.back.nosql.repository.LogOperacaoRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Calendar;

@Service
public class LogOperacaoService {

    private final LogOperacaoRepository logOperacaoRepository;

    public LogOperacaoService(LogOperacaoRepository logOperacaoRepository) {
        this.logOperacaoRepository = logOperacaoRepository;
    }

    public void registrar(String tipoOperacao, String entidade, String idEntidade) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String usuario = (auth != null ? auth.getName() : "desconhecido");

        LogOperacao log = new LogOperacao();
        log.setTipoOperacao(tipoOperacao);
        log.setEntidade(entidade);
        log.setIdEntidade(idEntidade);
        log.setUsuario(usuario);
        log.setDataHora(LocalDateTime.now());

        logOperacaoRepository.save(log);
    }
}
