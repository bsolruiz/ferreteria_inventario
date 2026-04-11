package com.inventario.api.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioDTO {

    private String nombres;
    private String correo;
    private String contrasena;
    private Integer rolId;
    private Long estado;


}