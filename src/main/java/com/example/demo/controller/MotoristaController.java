package com.example.demo.controller;

import java.util.UUID;

import com.example.demo.dto.MotoristaRequestDto;
import com.example.demo.dto.MotoristaResponseDto;
import com.example.demo.service.MotoristaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/motoristas")
public class MotoristaController {
    private final MotoristaService service;

    public MotoristaController(MotoristaService service) {
        this.service = service;
    }

    @GetMapping
    public Page<MotoristaResponseDto> listar(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) Boolean ativo,
            @PageableDefault(size = 10, sort = "nome") Pageable pageable) {
        return service.listar(nome, ativo, pageable);
    }

    @GetMapping("/{id}")
    public MotoristaResponseDto detalhar(@PathVariable UUID id) {
        return service.buscar(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MotoristaResponseDto criar(@RequestBody @Valid MotoristaRequestDto dto) {
        return service.criar(dto);
    }

    @PutMapping("/{id}")
    public MotoristaResponseDto atualizar(@PathVariable UUID id,
                                          @RequestBody @Valid MotoristaRequestDto dto) {
        return service.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable UUID id) {
        service.remover(id);
    }
}
