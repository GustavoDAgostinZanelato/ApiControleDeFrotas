package com.example.demo.config;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.model.CategoriaCnh;
import com.example.demo.model.Motorista;
import com.example.demo.model.StatusVeiculo;
import com.example.demo.model.Veiculo;
import com.example.demo.repository.MotoristaRepository;
import com.example.demo.repository.VeiculoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SeedConfig {

    @Bean
    CommandLineRunner seed(MotoristaRepository motoristaRepository, VeiculoRepository veiculoRepository) {
        return args -> {
            if (motoristaRepository.count() == 0) {
                motoristaRepository.saveAll(List.of(
                        motorista("Carlos Almeida", "11111111111", "90000000001", CategoriaCnh.E, LocalDate.now().plusYears(3), true),
                        motorista("Ana Souza", "22222222222", "90000000002", CategoriaCnh.D, LocalDate.now().plusYears(2), true),
                        motorista("Bruno Lima", "33333333333", "90000000003", CategoriaCnh.C, LocalDate.now().plusYears(1), true),
                        motorista("Daniela Rocha", "44444444444", "90000000004", CategoriaCnh.B, LocalDate.now().plusYears(4), true),
                        motorista("Eduardo Pires", "55555555555", "90000000005", CategoriaCnh.E, LocalDate.now().minusMonths(2), true),
                        motorista("Fernanda Costa", "66666666666", "90000000006", CategoriaCnh.C, LocalDate.now().plusYears(2), false),
                        motorista("Gustavo Reis", "77777777777", "90000000007", CategoriaCnh.D, LocalDate.now().plusMonths(8), true),
                        motorista("Helena Martins", "88888888888", "90000000008", CategoriaCnh.E, LocalDate.now().plusYears(5), true),
                        motorista("Igor Nunes", "99999999999", "90000000009", CategoriaCnh.B, LocalDate.now().plusYears(1), false),
                        motorista("Juliana Dias", "10101010101", "90000000010", CategoriaCnh.C, LocalDate.now().plusYears(3), true),
                        motorista("Lucas Ferreira", "12121212121", "90000000011", CategoriaCnh.D, LocalDate.now().plusYears(2), true),
                        motorista("Marina Teixeira", "13131313131", "90000000012", CategoriaCnh.E, LocalDate.now().plusYears(4), true)));
            }

            if (veiculoRepository.count() == 0) {
                veiculoRepository.saveAll(List.of(
                        veiculo("ABC1234", "Volvo FH 540", 2020, 25000.0, StatusVeiculo.DISPONIVEL),
                        veiculo("DEF5G67", "Scania R450", 2021, 28000.0, StatusVeiculo.DISPONIVEL),
                        veiculo("GHI9012", "Mercedes-Benz Actros", 2019, 24000.0, StatusVeiculo.EM_VIAGEM),
                        veiculo("JKL3M45", "Iveco Daily", 2018, 3500.0, StatusVeiculo.DISPONIVEL),
                        veiculo("MNO7890", "Volkswagen Delivery", 2017, 6000.0, StatusVeiculo.EM_MANUTENCAO),
                        veiculo("PQR1S23", "Ford Cargo 2429", 2016, 14000.0, StatusVeiculo.INATIVO),
                        veiculo("STU4567", "DAF XF", 2022, 26000.0, StatusVeiculo.DISPONIVEL),
                        veiculo("VWX8Y90", "Fiat Fiorino", 2023, 650.0, StatusVeiculo.DISPONIVEL)));
            }
        };
    }

    private Veiculo veiculo(String placa, String modelo, int anoFabricacao, double capacidadeKg,
                            StatusVeiculo status) {
        Veiculo v = new Veiculo();
        v.setPlaca(placa);
        v.setModelo(modelo);
        v.setAnoFabricacao(anoFabricacao);
        v.setCapacidadeKg(capacidadeKg);
        v.setStatus(status);
        v.setDataCadastro(LocalDate.now());
        return v;
    }

    private Motorista motorista(String nome, String cpf, String cnh, CategoriaCnh categoria,
                                LocalDate validadeCnh, boolean ativo) {
        Motorista m = new Motorista();
        m.setNome(nome);
        m.setCpf(cpf);
        m.setCnh(cnh);
        m.setCategoriaCnh(categoria);
        m.setValidadeCnh(validadeCnh);
        m.setAtivo(ativo);
        m.setDataCadastro(LocalDate.now());
        return m;
    }
}
