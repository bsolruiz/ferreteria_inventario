package com.tu.paquete.dto;

import lombok.Data;

@Data
public class UsuarioDTO {

    private String nombres;
    private String correo;
    private String contraseña;
    private Integer rolId;
}