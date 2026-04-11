package com.inventario.api.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovimientoDTO {

    private String tipoMovimiento;
    private Integer usuarioId;
    private Integer productoId;
    private String motivo;
}