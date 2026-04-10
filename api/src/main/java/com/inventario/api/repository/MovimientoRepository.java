package com.inventario.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.inventario.api.model.Movimiento;

public interface MovimientoRepository extends JpaRepository<Movimiento, Integer> {
}