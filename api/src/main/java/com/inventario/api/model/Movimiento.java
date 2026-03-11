package com.inventario.api.model;

import com.inventario.api.constants.TipoMovimiento;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "movimiento")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Movimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_movimiento")
    private Integer idMovimiento;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_movimiento")
    private TipoMovimiento tipoMovimiento;

    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

}
