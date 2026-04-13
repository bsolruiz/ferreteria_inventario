package com.inventario.api.controller;

import com.inventario.api.dtos.*;
import com.inventario.api.services.ReporteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reportes")
@RequiredArgsConstructor
public class ReporteController {

    private final ReporteService reporteService;

    @GetMapping("/movimientos")
    public List<ReporteMovimientoDTO> historialMovimientos() {
        return reporteService.getHistorialMovimientos();
    }

    @GetMapping("/productos")
    public List<ReporteProductoDTO> productosExistentes() {
        return reporteService.getProductosExistentes();
    }

    @GetMapping("/bajo-stock")
    public List<ReporteInventarioDTO> productosBajoStock() {
        return reporteService.getProductosBajoStock();
    }

    @GetMapping("/sin-stock")
    public List<ReporteInventarioDTO> productosSinStock() {
        return reporteService.getProductosSinStock();
    }

    @GetMapping("/inventario")
    public List<ReporteInventarioDTO> inventarioTotal() {
        return reporteService.getInventarioTotal();
    }
}