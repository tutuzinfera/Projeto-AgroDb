package com.agrodb.back.models;

import jakarta.persistence.*;

import java.util.Calendar;

@Entity
@Table(name = "produtor_produto")
public class ProdutorProduto {

    @EmbeddedId
    private ProdutorProdutoId id;

    @ManyToOne
    @MapsId("idProdutor")
    @JoinColumn(name = "id_produtor", referencedColumnName = "id_produtor")
    private Produtor_rural fk_id_produtor;
    @ManyToOne
    @MapsId("idProduto")
    @JoinColumn(name = "id_produto", referencedColumnName = "id_produto")
    private Produto fk_id_produto;

    @Column(name = "quantidade_disponivel")
    private Integer quantidade;

    public ProdutorProduto() {
    }

    public ProdutorProduto(Produtor_rural fk_id_produtor, Produto fk_id_produto, Integer quantidade) {
        this.id = new ProdutorProdutoId(fk_id_produtor.getId_rural(), fk_id_produto.getId_produto());
        this.fk_id_produtor = fk_id_produtor;
        this.fk_id_produto = fk_id_produto;
        this.quantidade = quantidade;
    }

    public ProdutorProdutoId getId() {
        return id;
    }

    public void setId(ProdutorProdutoId id) {
        this.id = id;
    }

    public Produtor_rural getFk_id_produtor() {
        return fk_id_produtor;
    }

    public void setFk_id_produtor(Produtor_rural fk_id_produtor) {
        this.fk_id_produtor = fk_id_produtor;
    }

    public Produto getFk_id_produto() {
        return fk_id_produto;
    }

    public void setFk_id_produto(Produto fk_id_produto) {
        this.fk_id_produto = fk_id_produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
