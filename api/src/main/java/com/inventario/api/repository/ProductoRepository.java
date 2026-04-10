package com.tu.paquete.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tu.paquete.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
}