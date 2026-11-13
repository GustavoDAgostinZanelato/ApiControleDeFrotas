package com.example.demo.repository;

import java.util.Optional;
import java.util.UUID;

import com.example.demo.model.Motorista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MotoristaRepository
        extends JpaRepository<Motorista, UUID>, JpaSpecificationExecutor<Motorista> {

    Optional<Motorista> findByCpf(String cpf);

    Optional<Motorista> findByCnh(String cnh);
}
