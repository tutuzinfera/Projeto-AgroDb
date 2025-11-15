package com.agrodb.back.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Calendar;

@Entity
public class Certificacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id_certificacao;

    private String orgao_emissor;

    private Calendar validade;

    public Integer getId_certificao() {
        return id_certificacao;
    }

    public void setId_certificacao(Integer id_certificao) {
        this.id_certificacao = id_certificao;
    }

    public String getOrgao_emissor() {
        return orgao_emissor;
    }

    public void setOrgao_emissor(String orgao_emissor) {
        this.orgao_emissor = orgao_emissor;
    }

    public Calendar getValidade() {
        return validade;
    }

    public void setValidade(Calendar validade) {
        this.validade = validade;
    }
}
