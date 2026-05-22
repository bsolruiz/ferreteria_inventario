package com.inventario.api.controller;

import com.inventario.api.dtos.MovimientoDTO;
import com.inventario.api.services.MovimientoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/movimientos")
@RequiredArgsConstructor
public class MovimientoController {

    private final MovimientoService movimientoService;

    @PostMapping
    public ResponseEntity<Map<String, String>> registrarMovimiento(
            @RequestBody MovimientoDTO dto) {
        try {
            movimientoService.registrarMovimiento(dto);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(Map.of("mensaje", "Movimiento registrado correctamente"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("mensaje", "Tipo de movimiento inválido. Los valores aceptados son: ENTRADA, SALIDA"));
        } catch (RuntimeException e) {
            HttpStatus status = resolverStatus(e.getMessage());
            return ResponseEntity
                    .status(status)
                    .body(Map.of("mensaje", e.getMessage()));
        }
    }

    @GetMapping("/stock/{productoId}")
    public ResponseEntity<Map<String, Object>> obtenerStock(
            @PathVariable Integer productoId) {
        try {
            int stock = movimientoService.calcularStock(productoId);
            return ResponseEntity.ok(Map.of("stock", stock));
        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(Map.of("mensaje", e.getMessage()));
        }
    }

    private HttpStatus resolverStatus(String mensaje) {
        if (mensaje == null) return HttpStatus.INTERNAL_SERVER_ERROR;
        if (mensaje.contains("no encontrado")) return HttpStatus.NOT_FOUND;
        if (mensaje.contains("Stock insuficiente")) return HttpStatus.CONFLICT;
        return HttpStatus.BAD_REQUEST;
    }
}