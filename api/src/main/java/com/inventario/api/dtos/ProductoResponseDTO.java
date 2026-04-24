package com.inventario.api.dtos;

import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ProductoResponseDTO {
    private Integer idProducto;
    private String nombreProducto;
    private String descripcion;
    private BigDecimal precio;
    private String codigoBarras;
    private Integer cantidad;
    private Integer categoriaId;
    private String categoriaNombre;
    private LocalDateTime fechaCreacion;
}