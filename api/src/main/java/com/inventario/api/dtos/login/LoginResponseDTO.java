package com.inventario.api.dtos.login;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponseDTO {
    private Long idUsuario;
    private String nombres;
    private String correo;
    private String rolNombre;
}