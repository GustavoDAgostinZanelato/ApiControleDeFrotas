package com.example.demo.dto;

import java.time.LocalDate;
import java.util.UUID;

import com.example.demo.model.CategoriaCnh;

public record MotoristaResponseDto(
        UUID id,
        String nome,
        String cpf,
        String cnh,
        CategoriaCnh categoriaCnh,
        LocalDate validadeCnh,
        Boolean ativo,
        LocalDate dataCadastro
) {}
