package com.inventario.api.dtos;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReporteMovimientoDTO {
    private Integer idMovimiento;
    private String tipoMovimiento;
    private LocalDateTime fechaRegistro;
    private String motivo;
    private String productoNombre;
    private String usuarioNombre;
}