package com.example.demo.exception;

import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class ApiExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(ApiExceptionHandler.class);

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ProblemDetail naoEncontrado(RecursoNaoEncontradoException ex) {
        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problem.setTitle("Recurso não encontrado");
        problem.setDetail(ex.getMessage());
        return problem;
    }

    @ExceptionHandler(ConflitoException.class)
    public ProblemDetail conflito(ConflitoException ex) {
        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.CONFLICT);
        problem.setTitle("Conflito de dados");
        problem.setDetail(ex.getMessage());
        return problem;
    }

    @ExceptionHandler(RegraNegocioException.class)
    public ProblemDetail regraNegocio(RegraNegocioException ex) {
        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_CONTENT);
        problem.setTitle("Regra de negócio violada");
        problem.setDetail(ex.getMessage());
        return problem;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail validacao(MethodArgumentNotValidException ex) {
        Map<String, String> erros = new LinkedHashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(erro ->
                erros.put(erro.getField(), erro.getDefaultMessage()));

        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problem.setTitle("Erro de validação");
        problem.setDetail("Um ou mais campos são inválidos");
        problem.setProperty("errors", erros);
        return problem;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ProblemDetail corpoInvalido(HttpMessageNotReadableException ex) {
        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problem.setTitle("Corpo da requisição inválido");
        problem.setDetail("JSON malformado ou com valor inválido (ex.: enum, data ou número)");
        return problem;
    }

    // Parâmetro de URL com tipo errado (ex.: UUID inválido no path, enum inválido no filtro).
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ProblemDetail parametroInvalido(MethodArgumentTypeMismatchException ex) {
        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problem.setTitle("Parâmetro inválido");
        problem.setDetail("Valor inválido para o parâmetro '" + ex.getName() + "': " + ex.getValue());
        return problem;
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail generico(Exception ex) {
        log.error("Erro inesperado", ex);
        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        problem.setTitle("Erro interno");
        problem.setDetail("Ocorreu um erro inesperado");
        return problem;
    }
}
