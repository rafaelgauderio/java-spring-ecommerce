package com.rafaeldeluca.Ecommerce.Java.dto;

import java.time.Instant;

public class CustomError {

    private Instant momento;
    private Integer status;
    private String erro;
    private String caminho;

   // não criar construtor vazio em classe sem os método set, para obrigar a requisição informar
   // todos os parâmetros
    public CustomError(Instant momento, Integer status, String erro, String caminho) {
        this.momento = momento;
        this.status = status;
        this.erro = erro;
        this.caminho = caminho;
    }

    public Instant getMomento() {
        return momento;
    }

    public Integer getStatus() {
        return status;
    }

    public String getErro() {
        return erro;
    }

    public String getCaminho() {
        return caminho;
    }
}
