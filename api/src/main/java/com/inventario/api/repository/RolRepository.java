package com.inventario.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.inventario.api.model.Rol;

public interface RolRepository extends JpaRepository<Rol, Integer> {
}