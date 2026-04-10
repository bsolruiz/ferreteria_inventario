package com.inventario.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.inventario.api.model.DetalleMovimiento;
import java.util.List;

public interface DetalleMovimientoRepository extends JpaRepository<DetalleMovimiento, Integer> {

    List<DetalleMovimiento> findByProducto_IdProducto(Integer idProducto);
}