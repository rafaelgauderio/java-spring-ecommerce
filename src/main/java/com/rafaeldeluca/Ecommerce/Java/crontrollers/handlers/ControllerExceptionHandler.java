package com.rafaeldeluca.Ecommerce.Java.crontrollers.handlers;

import com.rafaeldeluca.Ecommerce.Java.dto.CustomError;
import com.rafaeldeluca.Ecommerce.Java.dto.FieldMessage;
import com.rafaeldeluca.Ecommerce.Java.dto.ValidationError;
import com.rafaeldeluca.Ecommerce.Java.services.exceptions.DataBaseIntegrityException;
import com.rafaeldeluca.Ecommerce.Java.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

// na classe com annotation @ControllerAdvice, devemos definir tratameto globais para exceções específicas
// assim não precisamos colocar blocos try-catch em várias partes do código.
@ControllerAdvice
public class ControllerExceptionHandler {
    // handler vão ser as classes de manipulação
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomError> recursoNaoEncontrado(ResourceNotFoundException erro, HttpServletRequest requisicao) {
        HttpStatus statusDoErro = HttpStatus.NOT_FOUND; //404
        CustomError errorCustomizado = new CustomError(Instant.now(), statusDoErro.value(), erro.getMessage(),requisicao.getRequestURI());
        return ResponseEntity.status(statusDoErro).body(errorCustomizado);

    }

    @ExceptionHandler(DataBaseIntegrityException.class)
    public ResponseEntity<CustomError> violacaoDeIntegridadeReferencial (DataBaseIntegrityException erro, HttpServletRequest requisicao) {
        HttpStatus statusDoErro = HttpStatus.BAD_REQUEST; //400
        CustomError errorCustomizado = new CustomError(Instant.now(), statusDoErro.value(), erro.getMessage(),requisicao.getRequestURI());
        return ResponseEntity.status(statusDoErro).body(errorCustomizado);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomError> argumentoNaoValidado (MethodArgumentNotValidException erro, HttpServletRequest requisicao) {
        HttpStatus statusDoErro = HttpStatus.UNPROCESSABLE_ENTITY; //422
        // ao invvés de instanciar o CustomError, instanciar a classe filha ValidationError
        ValidationError erroDeValidacao = new ValidationError(Instant.now(), statusDoErro.value(), "Dados inválidos. código do erro: "+ erro.getStatusCode(), requisicao.getRequestURI());
        // capturar uma lista com os FieldErrors, vai pegar só os campos que estiverem preenchidos
        // em desacardo com a validação da regra de negócio da entidade
        for (FieldError erroDeCampo : erro.getBindingResult().getFieldErrors()) {
            erroDeValidacao.adicionarErro(erroDeCampo.getField(),erroDeCampo.getDefaultMessage());
        }
        return ResponseEntity.status(statusDoErro).body(erroDeValidacao);
    }

}
