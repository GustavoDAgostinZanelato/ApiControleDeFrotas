package com.example.demo.repository;

import java.util.Optional;
import java.util.UUID;

import com.example.demo.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface VeiculoRepository
        extends JpaRepository<Veiculo, UUID>, JpaSpecificationExecutor<Veiculo> {

    Optional<Veiculo> findByPlaca(String placa);
}
