package com.agrodb.back.models;

import jakarta.persistence.*;

import java.util.Calendar;

@Entity
@Table(name = "pagamento_boleto")
public class PagamentoBoleto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_pagamento_boleto;

    @OneToOne
    @JoinColumn(name = "fk_id_pagamento")
    private Pagamento pagamento;

    @Column(name = "linha_digitavel")
    private String linhaDigitavel;

    @Column(name = "codigo_barras")
    private String codigoBarras;

    @Column(name = "data_vencimento")
    private Calendar dataVencimento;


    public Integer getId_pagamento_boleto() {
        return id_pagamento_boleto;
    }

    public void setId_pagamento_boleto(Integer id_pagamento_boleto) {
        this.id_pagamento_boleto = id_pagamento_boleto;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    public String getLinhaDigitavel() {
        return linhaDigitavel;
    }

    public void setLinhaDigitavel(String linhaDigitavel) {
        this.linhaDigitavel = linhaDigitavel;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public Calendar getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Calendar dataVencimento) {
        this.dataVencimento = dataVencimento;
    }
}

