package com.agrodb.back.models;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Calendar;

@Entity
@Table(name = "pagamento")
public class Pagamento {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id_pagamento;

        @Column(name = "valor")
        private BigDecimal valor;

        @Column(name = "data_pagamento")
        private Calendar dataPagamento;

        @Column(name = "status")
        private String status;

        @ManyToOne
        @JoinColumn(name = "fk_id_pedido")
        private Pedido pedido;

        public Integer getId_pagamento() {
            return id_pagamento;
        }

        public void setId_pagamento(Integer id_pagamento) {
            this.id_pagamento = id_pagamento;
        }

        public BigDecimal getValor() {
            return valor;
        }

        public void setValor(BigDecimal valor) {
            this.valor = valor;
        }

        public Calendar getDataPagamento() {
            return dataPagamento;
        }

        public void setDataPagamento(Calendar dataPagamento) {
            this.dataPagamento = dataPagamento;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Pedido getPedido() {
            return pedido;
        }

        public void setPedido(Pedido pedido) {
            this.pedido = pedido;
        }
    }

