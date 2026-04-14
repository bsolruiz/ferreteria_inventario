package com.inventario.api.dtos;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovimientoRequestDTO {

    private String tipoMovimiento; // ENTRADA o SALIDA
    private Integer usuarioId;
    private Integer productoId;
    private String motivo;
}