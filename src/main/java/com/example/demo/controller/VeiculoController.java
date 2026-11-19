package com.example.demo.controller;

import java.util.UUID;

import com.example.demo.dto.VeiculoRequestDto;
import com.example.demo.dto.VeiculoResponseDto;
import com.example.demo.model.StatusVeiculo;
import com.example.demo.service.VeiculoService;
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
@RequestMapping("/veiculos")
public class VeiculoController {
    private final VeiculoService service;

    public VeiculoController(VeiculoService service) {
        this.service = service;
    }

    @GetMapping
    public Page<VeiculoResponseDto> listar(
            @RequestParam(required = false) String placa,
            @RequestParam(required = false) String modelo,
            @RequestParam(required = false) StatusVeiculo status,
            @PageableDefault(size = 10, sort = "placa") Pageable pageable) {
        return service.listar(placa, modelo, status, pageable);
    }

    @GetMapping("/{id}")
    public VeiculoResponseDto detalhar(@PathVariable UUID id) {
        return service.buscar(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VeiculoResponseDto criar(@RequestBody @Valid VeiculoRequestDto dto) {
        return service.criar(dto);
    }

    @PutMapping("/{id}")
    public VeiculoResponseDto atualizar(@PathVariable UUID id,
                                        @RequestBody @Valid VeiculoRequestDto dto) {
        return service.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable UUID id) {
        service.remover(id);
    }
}
