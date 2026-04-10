package com.tu.paquete.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class ProductoDTO {

    private String nombreProducto;
    private String descripcion;
    private BigDecimal precio;
    private String codigoBarras;
    private Integer categoriaId;

    private Integer cantidadInicial;
}