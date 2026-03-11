package com.inventario.api.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(name = "nombres", nullable = false)
    private String nombres;

    @Column(name = "correo", nullable = false, unique = true)
    private String correo;

    @Column(name = "contraseña", nullable = false)
    private String contrasena;

    @ManyToOne
    @JoinColumn(name = "rol_id")
    private Rol rol;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

    @Column(name = "estado")
    private Integer estado;

}