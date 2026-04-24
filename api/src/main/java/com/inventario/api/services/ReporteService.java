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
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReporteService {


    private static final int UMBRAL_BAJO_STOCK = 10;

    private final MovimientoRepository movimientoRepository;
    private final ProductoRepository productoRepository;

    public List<ReporteMovimientoDTO> getHistorialMovimientos() {
        return movimientoRepository.findAllConRelaciones().stream()
                .map(this::toMovimientoDTO)
                .collect(Collectors.toList());
    }

    public List<ReporteProductoDTO> getProductosExistentes() {
        return productoRepository.findAllConCategoria().stream()
                .map(this::toProductoDTO)
                .collect(Collectors.toList());
    }

    public List<ReporteInventarioDTO> getProductosBajoStock() {
        return productoRepository.findProductosBajoStock(UMBRAL_BAJO_STOCK).stream()
                .map(this::toInventarioDTO)
                .collect(Collectors.toList());
    }

    public List<ReporteInventarioDTO> getProductosSinStock() {
        return productoRepository.findProductosSinStock().stream()
                .map(this::toInventarioDTO)
                .collect(Collectors.toList());
    }

    public List<ReporteInventarioDTO> getInventarioTotal() {
        return productoRepository.findAllConCategoria().stream()
                .map(this::toInventarioDTO)
                .collect(Collectors.toList());
    }

    private ReporteMovimientoDTO toMovimientoDTO(Movimiento m) {
        return ReporteMovimientoDTO.builder()
                .idMovimiento(m.getIdMovimiento())
                .tipoMovimiento(m.getTipoMovimiento().name())
                .fechaRegistro(m.getFechaRegistro())
                .motivo(m.getMotivo())
                .productoNombre(m.getProducto().getNombreProducto())
                .usuarioNombre(m.getUsuario().getNombres())
                .cantidad(m.getCantidad())
                .build();
    }

    private ReporteProductoDTO toProductoDTO(Producto p) {
        return ReporteProductoDTO.builder()
                .idProducto(p.getIdProducto())
                .nombreProducto(p.getNombreProducto())
                .descripcion(p.getDescripcion())
                .precio(p.getPrecio())
                .codigoBarras(p.getCodigoBarras())
                .categoriaNombre(p.getCategoria().getNombreCategoria())
                .cantidad(p.getCantidad())
                .build();
    }

    private ReporteInventarioDTO toInventarioDTO(Producto p) {
        BigDecimal precio = Optional.ofNullable(p.getPrecio()).orElse(BigDecimal.ZERO);
        int cantidad = Optional.ofNullable(p.getCantidad()).orElse(0);
        BigDecimal valorTotal = precio.multiply(BigDecimal.valueOf(cantidad));

        return ReporteInventarioDTO.builder()
                .idProducto(p.getIdProducto())
                .nombreProducto(p.getNombreProducto())
                .descripcion(p.getDescripcion())
                .codigoBarras(p.getCodigoBarras())
                .categoriaNombre(p.getCategoria().getNombreCategoria())
                .cantidad(cantidad)
                .precio(precio)
                .valorTotal(valorTotal)
                .build();
    }
}