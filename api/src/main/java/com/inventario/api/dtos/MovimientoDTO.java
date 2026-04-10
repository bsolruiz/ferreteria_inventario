package com.tu.paquete.dto;

import lombok.Data;

@Data
public class MovimientoDTO {

    private Integer productoId;
    private Integer cantidad;
    private String tipoMovimiento;
    private Integer usuarioId;
}