package com.agrodb.back.models;

import jakarta.persistence.*;

import java.time.YearMonth;

@Entity
@Table(name = "pagamento_cartao")
public class PagamentoCartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_pagamento_cartao;

    @OneToOne
    @JoinColumn(name = "fk_id_pagamento")
    private Pagamento pagamento;

    @Column(name = "nome_titular")
    private String nomeTitular;

    @Column(name = "bandeira")
    private String bandeira;

    @Column(name = "ultimos_digitos")
    private String ultimosDigitos;

    @Column(name = "data_expiracao")
    private YearMonth dataExpiracao;

    // getters e setters

    public Integer getId_pagamento_cartao() {
        return id_pagamento_cartao;
    }

    public void setId_pagamento_cartao(Integer id_pagamento_cartao) {
        this.id_pagamento_cartao = id_pagamento_cartao;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    public String getNomeTitular() {
        return nomeTitular;
    }

    public void setNomeTitular(String nomeTitular) {
        this.nomeTitular = nomeTitular;
    }

    public String getBandeira() {
        return bandeira;
    }

    public void setBandeira(String bandeira) {
        this.bandeira = bandeira;
    }

    public String getUltimosDigitos() {
        return ultimosDigitos;
    }

    public void setUltimosDigitos(String ultimosDigitos) {
        this.ultimosDigitos = ultimosDigitos;
    }

    public YearMonth getDataExpiracao() {
        return dataExpiracao;
    }

    public void setDataExpiracao(YearMonth dataExpiracao) {
        this.dataExpiracao = dataExpiracao;
    }
}

