package com.projeto.agendadorusuario.business;

import com.projeto.agendadorusuario.business.dto.UsuarioDTO;
import com.projeto.agendadorusuario.business.mapper.UsuarioMapper;
import com.projeto.agendadorusuario.infra.entity.Usuario;
import com.projeto.agendadorusuario.infra.exceptions.ConflictException;
import com.projeto.agendadorusuario.infra.exceptions.ResourceNotFoundException;
import com.projeto.agendadorusuario.infra.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository _usuarioRepository;
    private final UsuarioMapper _usuarioMapper;
    private final PasswordEncoder _passwordEncoder;

    public UsuarioDTO salvarUsuario(UsuarioDTO usuarioDTO){
        emailExiste(usuarioDTO.getEmail());
        usuarioDTO.setSenha(_passwordEncoder.encode(usuarioDTO.getSenha()));

        Usuario usuario = _usuarioMapper.dtoParaUsuario(usuarioDTO);
        usuario = _usuarioRepository.save(usuario);
        //UsuarioDTO usuarioDTOResultado = _usuarioMapper.usuarioParaUsuarioDTO(usuario);
        return _usuarioMapper.usuarioParaUsuarioDTO(usuario);
    }

    public void emailExiste(String email){
        try{
            boolean emailExistente = verificarSeEmailExiste(email);
            if (emailExistente == true){
                throw new ConflictException("Email já existe na base de dados" + email);
            }
        }catch (ConflictException e){
            throw new ConflictException("Email já existe na base de dados" + e.getCause());
        }
    }

    public boolean verificarSeEmailExiste(String email){
        return _usuarioRepository.existsByEmail(email);
    }

    public Usuario buscarUsuarioByEmail(String email){
        return _usuarioRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Email Não encontrado: " + email));
    }

    public void DeletarUsuarioByEmail(String email){
        _usuarioRepository.deleteByEmail(email);
    }
}
