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
    //-----------------------------------------------------------------
    // Definição das propriedades da classe Motorista
    //-----------------------------------------------------------------
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


    //-----------------------------------------------------------------
    //Definição do construtor e métodos getters e setters
    //-----------------------------------------------------------------
    public Motorista() {
    }

    // Id do motorista
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }

    //Nome do motorista
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    //CPF do motorista
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    //CNH do motorista
    public String getCnh() {
        return cnh;
    }
    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    //Categoria da CNH
    public CategoriaCnh getCategoriaCnh() {
        return categoriaCnh;
    }
    public void setCategoriaCnh(CategoriaCnh categoriaCnh) {
        this.categoriaCnh = categoriaCnh;
    }

    //Validade da CNH
    public LocalDate getValidadeCnh() {
        return validadeCnh;
    }
    public void setValidadeCnh(LocalDate validadeCnh) {
        this.validadeCnh = validadeCnh;
    }

    //Ativo
    public Boolean getAtivo() {
        return ativo;
    }
    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    //Data de cadastro
    public LocalDate getDataCadastro() {
        return dataCadastro;
    }
    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
}
