package com.projeto.agendadorusuario.business.dto;

import jakarta.persistence.Column;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TelefoneDTO {

    private Long id;
    private String numero;
    private String ddd;
}
