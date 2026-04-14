package com.inventario.api.dtos.login;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDTO {
    private String correo;
    private String contrasena;
}