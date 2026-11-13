package com.example.demo.dto;

import java.time.LocalDate;

import com.example.demo.model.CategoriaCnh;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record MotoristaRequestDto(
        @NotBlank(message = "O nome é obrigatório")
        @Size(min = 2, max = 100, message = "O nome deve ter entre 2 e 100 caracteres")
        String nome,

        @NotBlank(message = "O CPF é obrigatório")
        @Pattern(regexp = "\\d{11}", message = "O CPF deve ter 11 dígitos numéricos")
        String cpf,

        @NotBlank(message = "A CNH é obrigatória")
        @Pattern(regexp = "\\d{11}", message = "A CNH deve ter 11 dígitos numéricos")
        String cnh,

        @NotNull(message = "A categoria da CNH é obrigatória")
        CategoriaCnh categoriaCnh,

        @NotNull(message = "A validade da CNH é obrigatória")
        LocalDate validadeCnh,

        Boolean ativo
) {}
