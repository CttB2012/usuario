package com.projeto.agendadorusuario.business;

import com.projeto.agendadorusuario.business.dto.UsuarioDTO;
import com.projeto.agendadorusuario.business.mapper.UsuarioMapper;
import com.projeto.agendadorusuario.infra.entity.Usuario;
import com.projeto.agendadorusuario.infra.exceptions.ConflictException;
import com.projeto.agendadorusuario.infra.exceptions.ResourceNotFoundException;
import com.projeto.agendadorusuario.infra.repository.UsuarioRepository;
import com.projeto.agendadorusuario.infra.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository _usuarioRepository;
    private final UsuarioMapper _usuarioMapper;
    private final PasswordEncoder _passwordEncoder;
    private final JwtUtil _jwtUtil;

    public UsuarioDTO salvarUsuario(UsuarioDTO usuarioDTO) {
        emailExiste(usuarioDTO.getEmail());
        usuarioDTO.setSenha(_passwordEncoder.encode(usuarioDTO.getSenha()));

        Usuario usuario = _usuarioMapper.dtoParaUsuario(usuarioDTO);
        usuario = _usuarioRepository.save(usuario);
        //UsuarioDTO usuarioDTOResultado = _usuarioMapper.usuarioParaUsuarioDTO(usuario);
        return _usuarioMapper.usuarioParaUsuarioDTO(usuario);
    }

    public void emailExiste(String email) {
        try {
            boolean emailExistente = verificarSeEmailExiste(email);
            if (emailExistente == true) {
                throw new ConflictException("Email já existe na base de dados" + email);
            }
        } catch (ConflictException e) {
            throw new ConflictException("Email já existe na base de dados" + e.getCause());
        }
    }

    public boolean verificarSeEmailExiste(String email) {
        return _usuarioRepository.existsByEmail(email);
    }

    public Usuario buscarUsuarioByEmail(String email) {
        return _usuarioRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Email Não encontrado: " + email));
    }

    public void DeletarUsuarioByEmail(String email) {
        _usuarioRepository.deleteByEmail(email);

    }

        public UsuarioDTO atualizarDadosUsuario (UsuarioDTO usuarioDTO, String token){

            //Extrai o email do usuario usando o token (assim nao obriga o usuario a passar o email)
            String email = _jwtUtil.extrairEmailToken(token.substring(7));

            //Criptografa a senha novamente caso ela seja passada, caso contrario continua a criptografia antiga
            usuarioDTO.setSenha(usuarioDTO.getSenha() != null ? _passwordEncoder.encode(usuarioDTO.getSenha()) : null);

            //Busca os dados do usuario no repositorio
            Usuario usuarioEntity = _usuarioRepository.findByEmail(email).orElseThrow(() ->
                    new ResourceNotFoundException("Email não encontrado"));

            // Atualiza as informações que foram passadas e, as que não foram, utiliza as que ja existiam
            Usuario usuario = _usuarioMapper.updateUsuario(usuarioDTO, usuarioEntity);


            //Recebe o usuario (linha 57) salva no repositorio, chama o metodo para converter para DTO
            return _usuarioMapper.usuarioParaUsuarioDTO(_usuarioRepository.save(usuario));
        }
}
