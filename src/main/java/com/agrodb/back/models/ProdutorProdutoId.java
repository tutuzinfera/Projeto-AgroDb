package com.agrodb.back.models;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ProdutorProdutoId implements Serializable {

    private Integer idProdutor;
    private Integer idProduto;

    public ProdutorProdutoId() {
    }

    public ProdutorProdutoId(Integer idProdutor, Integer idProduto) {
        this.idProdutor = idProdutor;
        this.idProduto = idProduto;
    }

    public Integer getIdProdutor() {
        return idProdutor;
    }

    public void setIdProdutor(Integer idProdutor) {
        this.idProdutor = idProdutor;
    }

    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    // MUITO IMPORTANTE para chave composta funcionar direito:
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProdutorProdutoId that)) return false;
        return Objects.equals(idProdutor, that.idProdutor)
                && Objects.equals(idProduto, that.idProduto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProdutor, idProduto);
    }
}
