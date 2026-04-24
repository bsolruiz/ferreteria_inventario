package com.inventario.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.inventario.api.model.Movimiento;
import com.inventario.api.model.TipoMovimiento;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface MovimientoRepository extends JpaRepository<Movimiento, Integer> {

    @Query("SELECT m FROM Movimiento m JOIN FETCH m.producto JOIN FETCH m.usuario")
    List<Movimiento> findAllConRelaciones();

    @Query("SELECT COALESCE(SUM(m.cantidad), 0) FROM Movimiento m " +
            "WHERE m.producto.idProducto = :productoId AND m.tipoMovimiento = :tipo")
    Integer sumByProductoAndTipo(@Param("productoId") Integer productoId,
                                 @Param("tipo") TipoMovimiento tipo);
    default Integer sumarEntradas(Integer productoId) {
        return sumByProductoAndTipo(productoId, TipoMovimiento.ENTRADA);
    }
    default Integer sumarSalidas(Integer productoId) {
        return sumByProductoAndTipo(productoId, TipoMovimiento.SALIDA);
    }
    boolean existsByProducto_IdProducto(Integer idProducto);
}