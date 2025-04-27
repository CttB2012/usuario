package com.projeto.agendadorusuario.business.mapper;

import com.projeto.agendadorusuario.business.dto.EnderecoDTO;
import com.projeto.agendadorusuario.business.dto.TelefoneDTO;
import com.projeto.agendadorusuario.business.dto.UsuarioDTO;
import com.projeto.agendadorusuario.infra.entity.Endereco;
import com.projeto.agendadorusuario.infra.entity.Telefone;
import com.projeto.agendadorusuario.infra.entity.Usuario;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UsuarioMapper {

    public Usuario dtoParaUsuario(UsuarioDTO usuarioDTO){
        return Usuario.builder()
                .nome(usuarioDTO.getNome())
                .email(usuarioDTO.getEmail())
                .senha(usuarioDTO.getSenha())
                .enderecoList(usuarioDTO.getEnderecosDTO() != null ?
                        listaDtoParaListaEndereco(usuarioDTO.getEnderecosDTO()) : null)
                .telefoneList(usuarioDTO.getTelefonesDTO() != null ?
                        listaDtoParaListaTelefone(usuarioDTO.getTelefonesDTO()) : null)

                .build();
    }

    public List<Endereco>  listaDtoParaListaEndereco(List<EnderecoDTO> enderecosDTOS){
        //Mesma função do FOR abaixo
        return enderecosDTOS.stream().map(this::dtoParaEndereco).toList();

//        List<Endereco> enderecoList = new ArrayList<>();
//        for (EnderecoDTO enderecoDTO : enderecosDTOS){
//            enderecoList.add(dtoParaEndereco(enderecoDTO));
//        }
//        return enderecoList;
    }


    public Endereco dtoParaEndereco(EnderecoDTO enderecoDTO){
        return Endereco.builder()
                .rua(enderecoDTO.getRua())
                .numero(enderecoDTO.getNumero())
                .cidade(enderecoDTO.getCidade())
                .complemento(enderecoDTO.getComplemento())
                .cep(enderecoDTO.getCep())
                .estado(enderecoDTO.getEstado())
                .build();
    }

    public List<Telefone> listaDtoParaListaTelefone(List<TelefoneDTO> telefonesDTOS){

//        List<Telefone> telefoneList = new ArrayList<>();
//        for(TelefoneDTO telefoneDTO : telefonesDTOS){
//            telefoneList.add(dtoParaTelefone(telefoneDTO));
//        }
        return telefonesDTOS.stream().map(this::dtoParaTelefone).toList();
    }

    public Telefone dtoParaTelefone(TelefoneDTO telefoneDTO){

//        Telefone telefone = new Telefone();
//        telefone.setNumero(telefoneDTO.getNumero());
//        telefone.setDdd(telefoneDTO.getDdd());
        return Telefone.builder()
                .numero(telefoneDTO.getNumero())
                .ddd(telefoneDTO.getDdd())
                .build();
    }





    public UsuarioDTO usuarioParaUsuarioDTO(Usuario usuario){
        return UsuarioDTO.builder()
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .senha(usuario.getSenha())
                .enderecosDTO(usuario.getEnderecoList() != null ?
                        listaEnderecoParaListaDTO(usuario.getEnderecoList()) : null)
                .telefonesDTO(usuario.getTelefoneList() != null ?
                        listaTelefoneparaListaDTO(usuario.getTelefoneList()) : null)

                .build();
    }

    public List<EnderecoDTO>  listaEnderecoParaListaDTO(List<Endereco> listaEnderecos){
        //Mesma função do FOR abaixo
        //return listaEnderecos.stream().map(this::dtoParaEndereco).toList();

        List<EnderecoDTO> enderecosDTOS = new ArrayList<>();
        for (Endereco endereco : listaEnderecos){
            enderecosDTOS.add(enderecoParaDTO(endereco));
        }
        return enderecosDTOS;
    }

    public EnderecoDTO enderecoParaDTO(Endereco endereco){
        return EnderecoDTO.builder()
                .id(endereco.getId())
                .rua(endereco.getRua())
                .numero(endereco.getNumero())
                .cidade(endereco.getCidade())
                .complemento(endereco.getComplemento())
                .cep(endereco.getCep())
                .estado(endereco.getEstado())
                .build();
    }

    public Endereco DtoParaEnderecoEntity(EnderecoDTO enderecoDTO, Long idUsuario){
        return Endereco.builder()
                .rua(enderecoDTO.getRua())
                .numero(enderecoDTO.getNumero())
                .complemento(enderecoDTO.getComplemento())
                .cep(enderecoDTO.getCep())
                .cidade(enderecoDTO.getCidade())
                .estado(enderecoDTO.getEstado())
                .usuario_id(idUsuario)
                .build();
    }

    public Telefone dtoParaTelefoneEntity(TelefoneDTO telefoneDTO, Long idUsuario ){
        return Telefone.builder()
                .numero(telefoneDTO.getNumero())
                .ddd(telefoneDTO.getDdd())
                .usuario_id(idUsuario)

                .build();
    }


    public List<TelefoneDTO> listaTelefoneparaListaDTO(List<Telefone> listaTelefone){

        List<TelefoneDTO> telefoneList = new ArrayList<>();
        for(Telefone telefones : listaTelefone){
            telefoneList.add(telefoneParaTelefoneDTO(telefones));
        }
        return telefoneList;
    }

    public TelefoneDTO telefoneParaTelefoneDTO(Telefone telefone){
        return TelefoneDTO.builder()
                .id(telefone.getId())
                .numero(telefone.getNumero())
                .ddd(telefone.getDdd())
                .build();
    }

    public Usuario updateUsuario(UsuarioDTO usuarioDTO, Usuario usuarioEntity){
        return Usuario.builder()
                .id(usuarioEntity.getId())
                .nome(usuarioDTO.getNome() != null ? usuarioDTO.getNome() : usuarioEntity.getNome())
                .senha(usuarioDTO.getSenha() != null ? usuarioDTO.getSenha(): usuarioEntity.getSenha())
                .email(usuarioDTO.getEmail() != null ? usuarioDTO.getEmail() : usuarioEntity.getEmail())
                .enderecoList(usuarioEntity.getEnderecoList())
                .telefoneList(usuarioEntity.getTelefoneList())
                .build();
    }

    public Endereco updateEndereco(EnderecoDTO enderecoDTO, Endereco enderecoEntity){
        return Endereco.builder()
                .id(enderecoEntity.getId())
                .rua(enderecoDTO.getRua() != null ? enderecoDTO.getRua() : enderecoEntity.getRua())
                .numero(enderecoDTO.getNumero() != null ? enderecoDTO.getNumero() : enderecoEntity.getNumero())
                .complemento(enderecoDTO.getComplemento() != null ? enderecoDTO.getComplemento() : enderecoEntity.getComplemento())
                .cidade(enderecoDTO.getCidade() != null ? enderecoDTO.getCidade() : enderecoEntity.getCidade())
                .estado(enderecoDTO.getEstado() != null ? enderecoDTO.getEstado() : enderecoEntity.getEstado())
                .cep(enderecoDTO.getCep() != null ? enderecoDTO.getCep() : enderecoEntity.getCep())
                .build();

    }

    public Telefone updateTelefone(TelefoneDTO telefoneDTO, Telefone telefoneEntity){
        return Telefone.builder()
                .id(telefoneEntity.getId())
                .numero(telefoneDTO.getNumero() != null ? telefoneDTO.getNumero() : telefoneEntity.getNumero())
                .ddd(telefoneDTO.getDdd() != null ? telefoneDTO.getDdd() : telefoneEntity.getDdd())
                .build();
    }

}
