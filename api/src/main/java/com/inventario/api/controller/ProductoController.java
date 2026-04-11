package com.inventario.api.controller;
import com.inventario.api.dtos.ProductoDTO;
import com.inventario.api.model.Producto;
import com.inventario.api.services.ProductoService;
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