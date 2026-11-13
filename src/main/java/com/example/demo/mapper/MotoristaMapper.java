package com.example.demo.mapper;

import com.example.demo.dto.MotoristaRequestDto;
import com.example.demo.dto.MotoristaResponseDto;
import com.example.demo.model.Motorista;
import org.springframework.stereotype.Component;

@Component
public class MotoristaMapper {

    public Motorista toEntity(MotoristaRequestDto dto) {
        Motorista motorista = new Motorista();
        atualizar(motorista, dto);
        return motorista;
    }

    // Copia os campos do DTO para uma entidade existente (usado no PUT).
    public void atualizar(Motorista motorista, MotoristaRequestDto dto) {
        motorista.setNome(dto.nome());
        motorista.setCpf(dto.cpf());
        motorista.setCnh(dto.cnh());
        motorista.setCategoriaCnh(dto.categoriaCnh());
        motorista.setValidadeCnh(dto.validadeCnh());
    }

    public MotoristaResponseDto toDto(Motorista motorista) {
        return new MotoristaResponseDto(
                motorista.getId(),
                motorista.getNome(),
                motorista.getCpf(),
                motorista.getCnh(),
                motorista.getCategoriaCnh(),
                motorista.getValidadeCnh(),
                motorista.getAtivo(),
                motorista.getDataCadastro());
    }
}
