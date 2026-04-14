package com.inventario.api.repository;

import com.inventario.api.model.Producto;
import com.inventario.api.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    List<Producto> findByNombreProductoContainingIgnoreCase(String nombre);

    Optional<Producto> findByCodigoBarras(String codigoBarras);

    boolean existsByCodigoBarras(String codigoBarras);

    List<Producto> findByCategoria(Categoria categoria);

    List<Producto> findByCategoria_IdCategoria(Integer idCategoria);

    @Query("SELECT p FROM Producto p JOIN FETCH p.categoria WHERE p.cantidad <= :limite AND p.cantidad > 0")
    List<Producto> findProductosBajoStock(@Param("limite") int limite);

    @Query("SELECT p FROM Producto p JOIN FETCH p.categoria WHERE p.cantidad IS NULL OR p.cantidad = 0")
    List<Producto> findProductosSinStock();

    @Query("SELECT p FROM Producto p JOIN FETCH p.categoria")
    List<Producto> findAllConCategoria();
}