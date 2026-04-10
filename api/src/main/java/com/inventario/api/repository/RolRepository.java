package com.tu.paquete.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tu.paquete.model.Rol;

public interface RolRepository extends JpaRepository<Rol, Integer> {
}