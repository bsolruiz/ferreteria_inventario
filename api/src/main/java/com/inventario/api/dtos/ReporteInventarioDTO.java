package com.inventario.api.dtos;

import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReporteInventarioDTO {
    private Integer idProducto;
    private String nombreProducto;
    private String descripcion;
    private String codigoBarras;
    private String categoriaNombre;
    private Integer cantidad;
    private BigDecimal precio;
    private BigDecimal valorTotal;
}