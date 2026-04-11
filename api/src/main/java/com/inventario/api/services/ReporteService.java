package com.inventario.api.services;

import com.inventario.api.dtos.*;
import com.inventario.api.model.Movimiento;
import com.inventario.api.model.Producto;
import com.inventario.api.repository.MovimientoRepository;
import com.inventario.api.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReporteService {

    private final MovimientoRepository movimientoRepository;
    private final ProductoRepository productoRepository;

    public List<ReporteMovimientoDTO> getHistorialMovimientos() {
        return movimientoRepository.findAll().stream()
                .map(m -> ReporteMovimientoDTO.builder()
                        .idMovimiento(m.getIdMovimiento())
                        .tipoMovimiento(m.getTipoMovimiento().name())
                        .fechaRegistro(m.getFechaRegistro())
                        .motivo(m.getMotivo())
                        .productoNombre(m.getProducto().getNombreProducto())
                        .usuarioNombre(m.getUsuario().getNombre())
                        .build())
                .collect(Collectors.toList());
    }

    public List<ReporteProductoDTO> getProductosExistentes() {
        return productoRepository.findAll().stream()
                .map(p -> ReporteProductoDTO.builder()
                        .idProducto(p.getIdProducto())
                        .nombreProducto(p.getNombreProducto())
                        .descripcion(p.getDescripcion())
                        .precio(p.getPrecio())
                        .codigoBarras(p.getCodigoBarras())
                        .categoriaNombre(p.getCategoria().getNombre())
                        .cantidad(p.getCantidad())
                        .build())
                .collect(Collectors.toList());
    }

    public List<ReporteInventarioDTO> getProductosBajoStock() {
        return productoRepository.findAll().stream()
                .filter(p -> p.getCantidad() != null && p.getCantidad() <= 10 && p.getCantidad() > 0)
                .map(this::toInventarioDTO)
                .collect(Collectors.toList());
    }

    public List<ReporteInventarioDTO> getProductosSinStock() {
        return productoRepository.findAll().stream()
                .filter(p -> p.getCantidad() == null || p.getCantidad() == 0)
                .map(this::toInventarioDTO)
                .collect(Collectors.toList());
    }

    public List<ReporteInventarioDTO> getInventarioTotal() {
        return productoRepository.findAll().stream()
                .map(this::toInventarioDTO)
                .collect(Collectors.toList());
    }

    private ReporteInventarioDTO toInventarioDTO(Producto p) {
        BigDecimal valorTotal = p.getPrecio().multiply(BigDecimal.valueOf(p.getCantidad() != null ? p.getCantidad() : 0));
        return ReporteInventarioDTO.builder()
                .idProducto(p.getIdProducto())
                .nombreProducto(p.getNombreProducto())
                .descripcion(p.getDescripcion())
                .codigoBarras(p.getCodigoBarras())
                .categoriaNombre(p.getCategoria().getNombre())
                .cantidad(p.getCantidad())
                .precio(p.getPrecio())
                .valorTotal(valorTotal)
                .build();
    }
}