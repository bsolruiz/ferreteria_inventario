package com.inventario.api.controller;

import com.inventario.api.dtos.MovimientoDTO;
import com.inventario.api.services.MovimientoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movimientos")
@RequiredArgsConstructor
public class MovimientoController {

    private final MovimientoService movimientoService;

    @PostMapping
    public String registrarMovimiento(@RequestBody MovimientoDTO dto) {
        movimientoService.registrarMovimiento(dto);
        return "Movimiento registrado correctamente";
    }

    @GetMapping("/stock/{productoId}")
    public int obtenerStock(@PathVariable Integer productoId) {
        return movimientoService.calcularStock(productoId);
}