package com.agrodb.back.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Endereco {

    @Id
    private String cep;

    private String complemento;

    private int numero;

    public Endereco(String cep, String complemento, int numero) {
        this.cep = cep;
        this.complemento = complemento;
        this.numero = numero;
    }

    public Endereco() {
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
}
