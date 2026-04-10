package com.tu.paquete.controller;

import com.tu.paquete.dto.MovimientoDTO;
import com.tu.paquete.service.MovimientoService;
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
}