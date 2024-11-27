package com.projeto.agendadorusuario.controller;

import com.projeto.agendadorusuario.business.UsuarioService;
import com.projeto.agendadorusuario.business.dto.EnderecoDTO;
import com.projeto.agendadorusuario.business.dto.TelefoneDTO;
import com.projeto.agendadorusuario.business.dto.UsuarioDTO;
import com.projeto.agendadorusuario.infra.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService _usuarioService;
    private final AuthenticationManager _authenticationManager;
    private final JwtUtil _jwtUtil;


    @PostMapping
    public ResponseEntity<UsuarioDTO> salvarUsuario(@RequestBody UsuarioDTO usuarioDTO){
        return ResponseEntity.ok(_usuarioService.salvarUsuario(usuarioDTO));
    }

    @PostMapping("/login")
    public String login(@RequestBody UsuarioDTO usuarioDTO){
        Authentication authentication = _authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(usuarioDTO.getEmail(),
                        usuarioDTO.getSenha()));

        return "Bearer " + _jwtUtil.generateToken(authentication.getName());
    }

    @GetMapping
    private ResponseEntity<UsuarioDTO> buscarUsuarioByEmail(@RequestParam("email") String email){
        return ResponseEntity.ok(_usuarioService.buscarUsuarioByEmail(email));
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deletarUsuarioByEmail(@PathVariable String email){
        _usuarioService.DeletarUsuarioByEmail(email);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<UsuarioDTO> atualizarDadosUsuario(@RequestBody UsuarioDTO usuarioDTO,
                                                            @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(_usuarioService.atualizarDadosUsuario(usuarioDTO, token));
    }

    @PutMapping("/endereco")
    public ResponseEntity<EnderecoDTO> atualizarEndereco(@RequestBody EnderecoDTO enderecoDTO,
                                                         @RequestParam("id") Long id){
        return ResponseEntity.ok(_usuarioService.atualizarEndereco(enderecoDTO, id));
    }

    @PutMapping("/telefone")
    public ResponseEntity<TelefoneDTO> atualizarTelefone(@RequestBody TelefoneDTO telefoneDTO,
                                                         @RequestParam("id") Long id){
        return ResponseEntity.ok(_usuarioService.atualizarTelefone(telefoneDTO, id));
    }

    @PostMapping("/enderecoCadastro")
    public ResponseEntity<EnderecoDTO> cadastrarEndereco(@RequestBody EnderecoDTO enderecoDTO,
                                                         @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(_usuarioService.cadastrarEndereco(token, enderecoDTO));
    }

    @PostMapping("/telefoneCadastro")
    public ResponseEntity<TelefoneDTO> cadastrarTelefone(@RequestBody TelefoneDTO telefoneDTO,
                                                         @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(_usuarioService.cadastrarTelefone(telefoneDTO, token));
    }

}
