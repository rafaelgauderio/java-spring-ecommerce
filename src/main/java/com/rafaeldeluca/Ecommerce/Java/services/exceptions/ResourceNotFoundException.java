package com.rafaeldeluca.Ecommerce.Java.services.exceptions;

public class ResourceNotFoundException extends  RuntimeException {
    // excecao customizada para quando não encontar o recurso procurado
    // RuntimeException não exige que em tempo de compilação que seja obrigado o bloco try, catch

    public ResourceNotFoundException (String mensagem) {
        super (mensagem);
    }
}
