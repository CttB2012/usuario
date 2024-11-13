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
                .enderecoList(listaDtoParaListaEndereco(usuarioDTO.getEnderecosDTO()))
                .telefoneList(listaDtoParaListaTelefone(usuarioDTO.getTelefonesDTO()))

                .build();
    }

    public List<Endereco>  listaDtoParaListaEndereco(List<EnderecoDTO> enderecosDTOS){
        //Mesma função do FOR abaixo
        //return enderecosDTOS.stream().map(this::dtoParaEndereco).toList();

        List<Endereco> enderecoList = new ArrayList<>();
        for (EnderecoDTO enderecoDTO : enderecosDTOS){
            enderecoList.add(enderecoParaDTO(enderecoDTO));
        }
        return enderecoList;
    }


    public Endereco enderecoParaDTO(EnderecoDTO enderecoDTO){
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

        List<Telefone> telefoneList = new ArrayList<>();
        for(TelefoneDTO telefoneDTO : telefonesDTOS){
            telefoneList.add(dtoParaTelefone(telefoneDTO));
        }
        return telefoneList;
    }

    public Telefone dtoParaTelefone(TelefoneDTO telefoneDTO){

        Telefone telefone = new Telefone();
        telefone.setNumero(telefoneDTO.getNumero());
        telefone.setDdd(telefoneDTO.getDdd());
        return telefone;
    }


    public UsuarioDTO usuarioParaUsuarioDTO(Usuario usuario){
        return UsuarioDTO.builder()
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .senha(usuario.getSenha())
                .enderecosDTO(listaEnderecoParaListaDTO(usuario.getEnderecoList()))
                .telefonesDTO(listaTelefoneparaListaDTO(usuario.getTelefoneList()))

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
                .rua(endereco.getRua())
                .numero(endereco.getNumero())
                .cidade(endereco.getCidade())
                .complemento(endereco.getComplemento())
                .cep(endereco.getCep())
                .estado(endereco.getEstado())
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
                .numero(telefone.getNumero())
                .ddd(telefone.getDdd())
                .build();
    }

}