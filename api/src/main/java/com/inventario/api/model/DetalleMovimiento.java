package com.inventario.api.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "detalle_movimiento")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetalleMovimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle")
    private Integer idDetalle;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "movimiento_id")
    private Movimiento movimiento;

    @Column(name = "cantidad")
    private Integer cantidad;

}
