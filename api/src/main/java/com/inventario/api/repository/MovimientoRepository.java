package com.inventario.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.inventario.api.model.Movimiento;
import com.inventario.api.model.TipoMovimiento;

public interface MovimientoRepository extends JpaRepository<Movimiento, Integer> {

    @Query("SELECT COUNT(m) FROM Movimiento m WHERE m.producto.idProducto = :productoId AND m.tipoMovimiento = :tipo")
    Integer sumByProductoAndTipo(Integer productoId, TipoMovimiento tipo);

    default Integer sumarEntradas(Integer productoId) {
        return sumByProductoAndTipo(productoId, TipoMovimiento.ENTRADA);
    }

    default Integer sumarSalidas(Integer productoId) {
        return sumByProductoAndTipo(productoId, TipoMovimiento.SALIDA);
    }
}