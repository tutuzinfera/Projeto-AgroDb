package com.agrodb.back.models;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_pedido;


    private Calendar data_pedido;


    private String status;


    private BigDecimal valor_total;

    @ManyToOne
    @JoinColumn(name = "fk_id_cliente", referencedColumnName = "id_cliente")
    private Cliente cliente;


    public Pedido(Integer id_pedido, Calendar data_pedido, String status, BigDecimal valor_total, Cliente fk_idCliente) {
        this.id_pedido = id_pedido;
        this.data_pedido = data_pedido;
        this.status = status;
        this.valor_total = valor_total;
        this.cliente = fk_idCliente;
    }

    public Pedido() {
    }

    public Integer getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(Integer id_pedido) {
        this.id_pedido = id_pedido;
    }

    public Calendar getData_pedido() {
        return data_pedido;
    }

    public void setData_pedido(Calendar data_pedido) {
        this.data_pedido = data_pedido;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getValor_total() {
        return valor_total;
    }

    public void setValor_total(BigDecimal valor_total) {
        this.valor_total = valor_total;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente fk_idCliente) {
        this.cliente = fk_idCliente;
    }
}
