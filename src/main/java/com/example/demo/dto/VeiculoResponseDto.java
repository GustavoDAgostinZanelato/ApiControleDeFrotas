package com.example.demo.dto;

import java.time.LocalDate;
import java.util.UUID;

import com.example.demo.model.StatusVeiculo;

public record VeiculoResponseDto(
        UUID id,
        String placa,
        String modelo,
        Integer anoFabricacao,
        Double capacidadeKg,
        StatusVeiculo status,
        LocalDate dataCadastro
) {}
