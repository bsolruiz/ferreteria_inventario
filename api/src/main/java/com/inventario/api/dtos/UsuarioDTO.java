package com.tu.paquete.dto;

import lombok.Data;

@Data
public class UsuarioDTO {

    private Long id;
    private String nombres;
    private String correo;
    private String contrasena;
    private Long rolId;
    private Long estado;
}