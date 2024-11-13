package com.projeto.agendadorusuario.controller;

import com.projeto.agendadorusuario.business.UsuarioService;
import com.projeto.agendadorusuario.business.dto.UsuarioDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService _usuarioService;

    public ResponseEntity<UsuarioDTO> salvarUsuario(@RequestBody UsuarioDTO usuarioDTO){
        return ResponseEntity.ok(_usuarioService.salvarUsuario(usuarioDTO));
    }
}
