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
@Table(name = "veiculos")
public class Veiculo {
    //-----------------------------------------------------------------
    // Definição das propriedades da classe Veiculo
    //-----------------------------------------------------------------
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true, nullable = false)
    private String placa;

    private String modelo;
    private Integer anoFabricacao;
    private Double capacidadeKg;

    @Enumerated(EnumType.STRING)
    private StatusVeiculo status;

    private LocalDate dataCadastro;


    //-----------------------------------------------------------------
    //Definição do construtor e métodos getters e setters
    //-----------------------------------------------------------------
    public Veiculo() {
    }

    public UUID getId() {
        return id;
    }

    //Placa do veículo
    public String getPlaca() {  
        return placa;
    }
    public void setPlaca(String placa) {
        this.placa = placa;
    }

    //Modelo do veículo
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    public String getModelo() {
        return modelo;
    }

    //Ano de fabricação do veículo
    public void setAnoFabricacao(Integer anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
    }
    public Integer getAnoFabricacao() {
        return anoFabricacao;
    }

    //Capacidade de carga do veículo em kg
    public void setCapacidadeKg(Double capacidadeKg) {
        this.capacidadeKg = capacidadeKg;
    }
    public Double getCapacidadeKg() {
        return capacidadeKg;
    }

    //Status do veículo
    public void setStatus(StatusVeiculo status) {
        this.status = status;
    }
    public StatusVeiculo getStatus() {
        return status;
    }

    //Data de cadastro do veículo
    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
    public LocalDate getDataCadastro() {
        return dataCadastro;
    }
}