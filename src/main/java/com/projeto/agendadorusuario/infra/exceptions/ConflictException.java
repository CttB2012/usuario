package com.projeto.agendadorusuario.infra.exceptions;

public class ConflictException extends RuntimeException{

    public ConflictException(String mensagem){
        super(mensagem);
    }

    public ConflictException(String messagem, Throwable cause) {
        super(messagem);
    }

}
