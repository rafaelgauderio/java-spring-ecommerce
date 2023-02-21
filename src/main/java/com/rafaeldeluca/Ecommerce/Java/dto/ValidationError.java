package com.rafaeldeluca.Ecommerce.Java.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends  CustomError {

    private List<FieldMessage> erros = new ArrayList<FieldMessage>();

    public ValidationError(Instant momento, Integer status, String erro, String caminho) {
        super(momento, status, erro, caminho);
    }

    public List<FieldMessage> getErros () {
        return erros;
    }

    // método para adicionar mensagens a lista de erros
    public void adicionarErro (String nomeDoCampo, String mensagem) {
        erros.add(new FieldMessage(nomeDoCampo,mensagem));
    }

}
