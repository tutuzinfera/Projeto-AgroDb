package com.agrodb.back.nosql.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Calendar;

@Document(collection = "logs_operacoes")
public class LogOperacao {

    @Id
    private String id;

    private String tipoOperacao;    //Create, update, etc...
    private String entidade;        // Clientes, Produto, Pedido, etc...
    private String idEntidade;      // "1", "2", etc...
    private String usuario;         // app_reader, app_writer, app_admin...
    private LocalDateTime dataHora;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipoOperacao() {
        return tipoOperacao;
    }

    public void setTipoOperacao(String tipoOperacao) {
        this.tipoOperacao = tipoOperacao;
    }

    public String getEntidade() {
        return entidade;
    }

    public void setEntidade(String entidade) {
        this.entidade = entidade;
    }

    public String getIdEntidade() {
        return idEntidade;
    }

    public void setIdEntidade(String idEntidade) {
        this.idEntidade = idEntidade;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }
}
