package com.inventario.api.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductoDTO {

    @NotBlank(message = "El nombre del producto es obligatorio")
    private String nombreProducto;

    private String descripcion;

    @NotNull(message = "El precio es obligatorio")
    @Positive(message = "El precio debe ser mayor a 0")
    private BigDecimal precio;

    @NotBlank(message = "El código de barras es obligatorio")
    private String codigoBarras;

    @NotNull(message = "La categoría es obligatoria")
    private Integer categoriaId;

    private Integer cantidad;
}