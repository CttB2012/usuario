package com.projeto.agendadorusuario.business.dto;


import lombok.*;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UsuarioDTO {

    private String nome;
    private String email;
    private String senha;

    private List<EnderecoDTO> enderecosDTO;
    private List<TelefoneDTO> telefonesDTO;


}
