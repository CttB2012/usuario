package com.projeto.agendadorusuario.infra.exceptions;


import org.springframework.security.core.AuthenticationException;

public class UnauthorizedException extends AuthenticationException {

    public UnauthorizedException(String messagem){
        super(messagem);
    }

    public UnauthorizedException(String messagem, Throwable throwable){
        super(messagem, throwable);
    }
}
