package com.inventario.api.dtos;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetalleMovimientoDTO {

    private Integer productoId;
    private Integer cantidad;
}
