package com.projeto.agendadorusuario.business;

import com.projeto.agendadorusuario.business.dto.UsuarioDTO;
import com.projeto.agendadorusuario.business.mapper.UsuarioMapper;
import com.projeto.agendadorusuario.infra.entity.Usuario;
import com.projeto.agendadorusuario.infra.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository _usuarioRepository;
    private final UsuarioMapper _usuarioMapper;

    public UsuarioDTO salvarUsuario(UsuarioDTO usuarioDTO){

        Usuario usuario = _usuarioMapper.dtoParaUsuario(usuarioDTO);
        usuario = _usuarioRepository.save(usuario);
        UsuarioDTO usuarioDTOResultado = _usuarioMapper.usuarioParaUsuarioDTO(usuario);
        return usuarioDTOResultado;
    }
}
