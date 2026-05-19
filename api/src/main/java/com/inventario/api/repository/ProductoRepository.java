package com.inventario.api.repository;

import com.inventario.api.model.Categoria;
import com.inventario.api.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    // Solo productos activos
    @Query("SELECT p FROM Producto p JOIN FETCH p.categoria WHERE p.activo = true")
    List<Producto> findAllConCategoria();

    // Unicidad de código de barras globalmente (activo o no)
    boolean existsByCodigoBarras(String codigoBarras);

    // Unicidad de nombre SOLO entre activos
    boolean existsByNombreProductoIgnoreCaseAndActivoTrue(String nombreProducto);

    // Para validar cambio de código en actualización (entre activos)
    boolean existsByCodigoBarrasAndActivoTrue(String codigoBarras);

    Optional<Producto> findByCodigoBarras(String codigoBarras);

    List<Producto> findByCategoria(Categoria categoria);

    @Query("SELECT p FROM Producto p JOIN FETCH p.categoria WHERE p.cantidad <= :limite AND p.cantidad > 0 AND p.activo = true")
    List<Producto> findProductosBajoStock(@Param("limite") int limite);

    @Query("SELECT p FROM Producto p JOIN FETCH p.categoria WHERE (p.cantidad IS NULL OR p.cantidad = 0) AND p.activo = true")
    List<Producto> findProductosSinStock();
}