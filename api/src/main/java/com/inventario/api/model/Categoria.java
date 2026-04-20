package com.inventario.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "categoria")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCategoria;

    private String nombreCategoria;

    @OneToMany(mappedBy = "categoria")
    @JsonIgnore  // ← esto corta la recursión
    private List<Producto> productos;
}