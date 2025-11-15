package com.agrodb.back.models;

import java.io.Serializable;
import java.util.Objects;

public class ItemPedidoId implements Serializable {

    private Integer idProduto;
    private Integer idProdutor;
    private Integer idPedido;

    public ItemPedidoId() {
    }

    public ItemPedidoId(Integer idProduto, Integer idProdutor, Integer idPedido) {
        this.idProduto = idProduto;
        this.idProdutor = idProdutor;
        this.idPedido = idPedido;
    }

    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    public Integer getIdProdutor() {
        return idProdutor;
    }

    public void setIdProdutor(Integer idProdutor) {
        this.idProdutor = idProdutor;
    }

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemPedidoId that)) return false;
        return Objects.equals(idPedido, that.idPedido)
                && Objects.equals(idProduto, that.idProduto)
                && Objects.equals(idProdutor, that.idProdutor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPedido, idProduto, idProdutor);
    }

}
