package com.tu.paquete.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tu.paquete.model.Movimiento;

public interface MovimientoRepository extends JpaRepository<Movimiento, Integer> {
}