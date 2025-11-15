package com.agrodb.back.models;

import jakarta.persistence.*;

import java.util.Calendar;

@Entity
public class Produtor_rural {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produtor")
    private Integer id_rural;

    @ManyToOne
    @JoinColumn(name = "fk_id_certificacao", referencedColumnName = "id_certificacao")
    private Certificacao certificacao;

    private String nome;

    private String cpf_cnpj;

    @Column(name = "telefone")
    private String tel;

    private Calendar data_cadastro;

    public Produtor_rural(String name, String cpf_cnpj, String tel) {
        this.nome = name;
        this.cpf_cnpj = cpf_cnpj;
        this.tel = tel;
    }

    public Produtor_rural() {
    }

    public Integer getId_rural() {
        return id_rural;
    }

    public void setId_rural(Integer id_rural) {
        this.id_rural = id_rural;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf_cnpj() {
        return cpf_cnpj;
    }

    public void setCpf_cnpj(String cpf_cnpj) {
        this.cpf_cnpj = cpf_cnpj;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Calendar getData_cadastro() {
        return data_cadastro;
    }

    public void setData_cadastro(Calendar data_cadastro) {
        this.data_cadastro = data_cadastro;
    }

    public Certificacao getCertificacao() {
        return certificacao;
    }

    public void setCertificacao(Certificacao certificacao) {
        this.certificacao = certificacao;
    }
}
