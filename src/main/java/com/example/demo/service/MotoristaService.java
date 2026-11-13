package com.example.demo.service;

import java.time.LocalDate;
import java.util.UUID;

import com.example.demo.dto.MotoristaRequestDto;
import com.example.demo.dto.MotoristaResponseDto;
import com.example.demo.exception.ConflitoException;
import com.example.demo.exception.RecursoNaoEncontradoException;
import com.example.demo.mapper.MotoristaMapper;
import com.example.demo.model.Motorista;
import com.example.demo.repository.MotoristaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MotoristaService {
    private static final String RECURSO = "Motorista";

    private final MotoristaRepository repository;
    private final MotoristaMapper mapper;

    public MotoristaService(MotoristaRepository repository, MotoristaMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public Page<MotoristaResponseDto> listar(String nome, Boolean ativo, Pageable pageable) {
        // Cada filtro só entra na consulta se foi informado.
        Specification<Motorista> filtros = (root, query, cb) -> cb.conjunction();
        if (nome != null && !nome.isBlank()) {
            filtros = filtros.and((root, query, cb) ->
                    cb.like(cb.lower(root.get("nome")), "%" + nome.toLowerCase() + "%"));
        }
        if (ativo != null) {
            filtros = filtros.and((root, query, cb) -> cb.equal(root.get("ativo"), ativo));
        }
        return repository.findAll(filtros, pageable).map(mapper::toDto);
    }

    @Transactional(readOnly = true)
    public MotoristaResponseDto buscar(UUID id) {
        return mapper.toDto(buscarEntidade(id));
    }

    @Transactional
    public MotoristaResponseDto criar(MotoristaRequestDto dto) {
        validarUnicidade(dto, null);
        Motorista motorista = mapper.toEntity(dto);
        motorista.setAtivo(dto.ativo() == null ? Boolean.TRUE : dto.ativo());
        motorista.setDataCadastro(LocalDate.now());
        return mapper.toDto(repository.save(motorista));
    }

    @Transactional
    public MotoristaResponseDto atualizar(UUID id, MotoristaRequestDto dto) {
        Motorista motorista = buscarEntidade(id);
        validarUnicidade(dto, id);
        mapper.atualizar(motorista, dto);
        if (dto.ativo() != null) {
            motorista.setAtivo(dto.ativo());
        }
        return mapper.toDto(repository.save(motorista));
    }

    @Transactional
    public void remover(UUID id) {
        repository.delete(buscarEntidade(id));
    }

    private Motorista buscarEntidade(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException(RECURSO, id));
    }

    // idAtual é o id do próprio motorista no PUT (para ele não conflitar consigo mesmo).
    private void validarUnicidade(MotoristaRequestDto dto, UUID idAtual) {
        repository.findByCpf(dto.cpf()).ifPresent(outro -> {
            if (!outro.getId().equals(idAtual)) {
                throw new ConflitoException("Já existe um motorista com o CPF " + dto.cpf());
            }
        });
        repository.findByCnh(dto.cnh()).ifPresent(outro -> {
            if (!outro.getId().equals(idAtual)) {
                throw new ConflitoException("Já existe um motorista com a CNH " + dto.cnh());
            }
        });
    }
}
