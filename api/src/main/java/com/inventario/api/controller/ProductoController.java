package com.tu.paquete.controller;

import com.tu.paquete.dto.ProductoDTO;
import com.tu.paquete.model.Producto;
import com.tu.paquete.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    @PostMapping
    public Producto crearProducto(@RequestBody ProductoDTO dto) {
        return productoService.crearProducto(dto);
    }
}