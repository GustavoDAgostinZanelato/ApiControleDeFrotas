package com.example.demo.mapper;

import com.example.demo.dto.VeiculoRequestDto;
import com.example.demo.dto.VeiculoResponseDto;
import com.example.demo.model.Veiculo;
import org.springframework.stereotype.Component;

@Component
public class VeiculoMapper {

    //Retorna uma entidade Veiculo a partir de um VeiculoRequestDto
    public Veiculo toEntity(VeiculoRequestDto dto) {
        Veiculo veiculo = new Veiculo();
        atualizar(veiculo, dto);
        return veiculo;
    }

    //Atualiza uma entidade Veiculo existente a partir de um VeiculoRequestDto
    //O status é tratado no service (valor padrão e PUT sem status).
    public void atualizar(Veiculo veiculo, VeiculoRequestDto dto) {
        veiculo.setPlaca(dto.placa().toUpperCase());
        veiculo.setModelo(dto.modelo());
        veiculo.setAnoFabricacao(dto.anoFabricacao());
        veiculo.setCapacidadeKg(dto.capacidadeKg());
    }

    //Retorna um VeiculoResponseDto a partir de uma entidade Veiculo
    public VeiculoResponseDto toDto(Veiculo veiculo) {
        return new VeiculoResponseDto(
                veiculo.getId(),
                veiculo.getPlaca(),
                veiculo.getModelo(),
                veiculo.getAnoFabricacao(),
                veiculo.getCapacidadeKg(),
                veiculo.getStatus(),
                veiculo.getDataCadastro()
        );
    }
}
