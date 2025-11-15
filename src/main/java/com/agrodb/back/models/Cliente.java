package com.agrodb.back.models;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_cliente;

    private String nome;

    private String cpf;

    @Column(name = "telefone")
    private String tel;

    @ManyToOne
    @JoinColumn(name = "fk_cep", referencedColumnName = "cep")
    private Endereco fkcep;

    public Cliente(String nome, String cpf, String tel, Endereco fkcep) {
        this.nome = nome;
        this.cpf = cpf;
        this.tel = tel;
        this.fkcep = fkcep;
    }

    public Cliente() {

    }

    public Integer getIdCliente() {
        return id_cliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.id_cliente = idCliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Endereco getCep() {
        return fkcep;
    }

    public void setCep(Endereco cep) {
        this.fkcep = cep;
    }

    /*
    public Instant getCreationTimestamp() {
        return creationTimestamp;
    }

    public void setCreationTimestamp(Instant creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }

    public Instant getUpdateTimestamp() {
        return updateTimestamp;
    }

    public void setUpdateTimestamp(Instant updateTimestamp) {
        this.updateTimestamp = updateTimestamp;
    }
    */
}
