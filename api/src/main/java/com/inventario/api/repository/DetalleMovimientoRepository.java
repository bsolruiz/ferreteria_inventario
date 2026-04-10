package com.tu.paquete.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tu.paquete.model.DetalleMovimiento;
import java.util.List;

public interface DetalleMovimientoRepository extends JpaRepository<DetalleMovimiento, Integer> {

    List<DetalleMovimiento> findByProducto_IdProducto(Integer idProducto);
}