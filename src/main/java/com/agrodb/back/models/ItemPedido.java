package com.agrodb.back.models;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "item_pedido")
public class ItemPedido {

    @EmbeddedId
    private ItemPedidoId id;

    @ManyToOne
    @MapsId("idPedido")
    @JoinColumn(name = "id_pedido", referencedColumnName ="id_pedido")
    private Pedido pedido;
    @ManyToOne
    @MapsId("idProdutor")
    @JoinColumn(name = "id_produtor", referencedColumnName = "id_produtor")
    private Produtor_rural produtor;
    @ManyToOne
    @MapsId("idProduto")
    @JoinColumn(name = "id_produto", referencedColumnName = "id_produto")
    private Produto produto;

    private int quantidade;

    private BigDecimal preco_unitario;

    public ItemPedido() {
    }

    public ItemPedido(Pedido pedido,
                      Produto produto,
                      Produtor_rural produtor,
                      int quantidade,
                      BigDecimal precoUnitario) {
        this.id = new ItemPedidoId(
                pedido.getId_pedido(),
                produto.getId_produto(),
                produtor.getId_rural()
        );
        this.pedido = pedido;
        this.produto = produto;
        this.produtor = produtor;
        this.quantidade = quantidade;
        this.preco_unitario = precoUnitario;
    }

    public ItemPedidoId getId() {
        return id;
    }

    public void setId(ItemPedidoId id) {
        this.id = id;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Produtor_rural getProdutor() {
        return produtor;
    }

    public void setProdutor(Produtor_rural produtor) {
        this.produtor = produtor;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getPreco_unitario() {
        return preco_unitario;
    }

    public void setPreco_unitario(BigDecimal preco_unitario) {
        this.preco_unitario = preco_unitario;
    }
}
