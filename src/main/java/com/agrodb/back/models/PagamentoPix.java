package com.agrodb.back.models;

import jakarta.persistence.*;

@Entity
@Table(name = "pagamento_pix")
public class PagamentoPix {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_pagamento_pix;

    @OneToOne
    @JoinColumn(name = "fk_id_pagamento") // coluna que referencia pagamento
    private Pagamento pagamento;

    @Column(name = "chave_pix")
    private String chavePix;

    @Column(name = "nome_titular")
    private String nomeTitular ;



    public Integer getId_pagamento_pix() {
        return id_pagamento_pix;
    }

    public void setId_pagamento_pix(Integer id_pagamento_pix) {
        this.id_pagamento_pix = id_pagamento_pix;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    public String getChavePix() {
        return chavePix;
    }

    public void setChavePix(String chavePix) {
        this.chavePix = chavePix;
    }

    public String getNomeTitular() {
        return nomeTitular;
    }

    public void setNomeTitular(String nomeTitular) {
        this.nomeTitular = nomeTitular;
    }
}

