package com.example.demo.service;

import java.time.LocalDate;
import java.util.UUID;

import com.example.demo.dto.VeiculoRequestDto;
import com.example.demo.dto.VeiculoResponseDto;
import com.example.demo.exception.ConflitoException;
import com.example.demo.exception.RecursoNaoEncontradoException;
import com.example.demo.mapper.VeiculoMapper;
import com.example.demo.model.StatusVeiculo;
import com.example.demo.model.Veiculo;
import com.example.demo.repository.VeiculoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VeiculoService {
    private static final String RECURSO = "Veiculo";

    private final VeiculoRepository repository;
    private final VeiculoMapper mapper;

    public VeiculoService(VeiculoRepository repository, VeiculoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public Page<VeiculoResponseDto> listar(String placa, String modelo, StatusVeiculo status, Pageable pageable) {
        // Cada filtro só entra na consulta se foi informado.
        Specification<Veiculo> filtros = (root, query, cb) -> cb.conjunction();
        if (placa != null && !placa.isBlank()) {
            filtros = filtros.and((root, query, cb) ->
                    cb.like(cb.lower(root.get("placa")), "%" + placa.toLowerCase() + "%"));
        }
        if (modelo != null && !modelo.isBlank()) {
            filtros = filtros.and((root, query, cb) ->
                    cb.like(cb.lower(root.get("modelo")), "%" + modelo.toLowerCase() + "%"));
        }
        if (status != null) {
            filtros = filtros.and((root, query, cb) -> cb.equal(root.get("status"), status));
        }
        return repository.findAll(filtros, pageable).map(mapper::toDto);
    }

    @Transactional(readOnly = true)
    public VeiculoResponseDto buscar(UUID id) {
        return mapper.toDto(buscarEntidade(id));
    }

    @Transactional
    public VeiculoResponseDto criar(VeiculoRequestDto dto) {
        validarUnicidade(dto, null);
        Veiculo veiculo = mapper.toEntity(dto);
        veiculo.setStatus(dto.status() == null ? StatusVeiculo.DISPONIVEL : dto.status());
        veiculo.setDataCadastro(LocalDate.now());
        return mapper.toDto(repository.save(veiculo));
    }

    @Transactional
    public VeiculoResponseDto atualizar(UUID id, VeiculoRequestDto dto) {
        Veiculo veiculo = buscarEntidade(id);
        validarUnicidade(dto, id);
        mapper.atualizar(veiculo, dto);
        if (dto.status() != null) {
            veiculo.setStatus(dto.status());
        }
        return mapper.toDto(repository.save(veiculo));
    }

    @Transactional
    public void remover(UUID id) {
        repository.delete(buscarEntidade(id));
    }

    private Veiculo buscarEntidade(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException(RECURSO, id));
    }

    // idAtual é o id do próprio veículo no PUT (para ele não conflitar consigo mesmo).
    private void validarUnicidade(VeiculoRequestDto dto, UUID idAtual) {
        String placa = dto.placa().toUpperCase();
        repository.findByPlaca(placa).ifPresent(outro -> {
            if (!outro.getId().equals(idAtual)) {
                throw new ConflitoException("Já existe um veículo com a placa " + placa);
            }
        });
    }
}
