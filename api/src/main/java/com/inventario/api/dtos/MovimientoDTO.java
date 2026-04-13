package com.inventario.api.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovimientoDTO {

    private String tipoMovimiento;
    private Long usuarioId;
    private Long productoId;
    private String motivo;
    private Integer cantidad;
}