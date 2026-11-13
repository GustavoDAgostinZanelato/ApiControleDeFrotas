package com.example.demo.model;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "motoristas")
public class Motorista {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String nome;

    @Column(unique = true, nullable = false)
    private String cpf;

    @Column(unique = true, nullable = false)
    private String cnh;

    @Enumerated(EnumType.STRING)
    private CategoriaCnh categoriaCnh;

    private LocalDate validadeCnh;
    private Boolean ativo;
    private LocalDate dataCadastro;

    public Motorista() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public String getCnh() {
        return cnh;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    public CategoriaCnh getCategoriaCnh() {
        return categoriaCnh;
    }

    public void setCategoriaCnh(CategoriaCnh categoriaCnh) {
        this.categoriaCnh = categoriaCnh;
    }

    public LocalDate getValidadeCnh() {
        return validadeCnh;
    }

    public void setValidadeCnh(LocalDate validadeCnh) {
        this.validadeCnh = validadeCnh;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
}
