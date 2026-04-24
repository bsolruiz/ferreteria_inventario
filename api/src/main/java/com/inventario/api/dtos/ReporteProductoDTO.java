package com.inventario.api.dtos;

import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReporteProductoDTO {
    private Integer idProducto;
    private String nombreProducto;
    private String descripcion;
    private BigDecimal precio;
    private String codigoBarras;
    private String categoriaNombre;
    private Integer cantidad;
}