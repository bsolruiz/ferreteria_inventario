package com.inventario.api.controller;

import com.inventario.api.model.Categoria;
import com.inventario.api.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/categorias")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaRepository categoriaRepository;

    @GetMapping
    public List<Categoria> listarCategorias() {
        return categoriaRepository.findAll();
    }

    /*
   INSERT INTO categoria (nombre_categoria) VALUES ('Herramientas');
INSERT INTO categoria (nombre_categoria) VALUES ('Plomería');
INSERT INTO categoria (nombre_categoria) VALUES ('Eléctrico');
INSERT INTO categoria (nombre_categoria) VALUES ('Construcción');
     */
}