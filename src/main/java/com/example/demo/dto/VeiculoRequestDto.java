package com.example.demo.dto;

import com.example.demo.model.StatusVeiculo;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record VeiculoRequestDto(
        // Aceita o padrão antigo (ABC1234) e o Mercosul (ABC1D23).
        @NotBlank(message = "A placa é obrigatória")
        @Pattern(regexp = "(?i)[A-Z]{3}\\d[A-Z0-9]\\d{2}",
                message = "A placa deve estar no formato ABC1234 ou ABC1D23")
        String placa,

        @NotBlank(message = "O modelo é obrigatório")
        @Size(min = 2, max = 100, message = "O modelo deve ter entre 2 e 100 caracteres")
        String modelo,

        @NotNull(message = "O ano de fabricação é obrigatório")
        @Min(value = 1950, message = "O ano de fabricação deve ser no mínimo 1950")
        Integer anoFabricacao,

        @NotNull(message = "A capacidade em kg é obrigatória")
        @Positive(message = "A capacidade deve ser maior que zero")
        Double capacidadeKg,

        // Opcional: se não for informado, o veículo é criado como DISPONIVEL.
        StatusVeiculo status
) {}
