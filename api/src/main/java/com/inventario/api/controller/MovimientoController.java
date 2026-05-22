package com.inventario.api.controller;

import com.inventario.api.dtos.MovimientoDTO;
import com.inventario.api.services.MovimientoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/movimientos")
@RequiredArgsConstructor
public class MovimientoController {

    private final MovimientoService movimientoService;

    @PostMapping
    public ResponseEntity<String> registrarMovimiento(@RequestBody MovimientoDTO dto) {
        movimientoService.registrarMovimiento(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Movimiento registrado correctamente");
    }

    @GetMapping("/stock/{productoId}")
    public int obtenerStock(@PathVariable Integer productoId) {
        return movimientoService.calcularStock(productoId);
    }
}