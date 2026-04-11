package com.inventario.api.services;

import com.inventario.api.dtos.MovimientoDTO;
import com.inventario.api.model.*;
import com.inventario.api.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MovimientoService {

    private final MovimientoRepository movimientoRepository;
    private final ProductoRepository productoRepository;
    private final UsuarioRepository usuarioRepository;

    @Transactional
    public void registrarMovimiento(MovimientoDTO dto) {

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Producto producto = productoRepository.findById(dto.getProductoId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        TipoMovimiento tipo = TipoMovimiento.valueOf(dto.getTipoMovimiento().toUpperCase());

        int stockActual = producto.getCantidad() != null ? producto.getCantidad() : 0;

        if (tipo == TipoMovimiento.SALIDA && stockActual < 1) {
            throw new RuntimeException("Stock insuficiente para el producto: " + producto.getNombreProducto());
        }

        if (tipo == TipoMovimiento.ENTRADA) {
            producto.setCantidad(stockActual + 1);
        } else {
            producto.setCantidad(stockActual - 1);
        }
        productoRepository.save(producto);

        Movimiento movimiento = Movimiento.builder()
                .tipoMovimiento(tipo)
                .fechaRegistro(LocalDateTime.now())
                .motivo(dto.getMotivo())
                .usuario(usuario)
                .producto(producto)
                .build();

        movimientoRepository.save(movimiento);
    }

    public int calcularStock(Integer productoId) {
        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        return producto.getCantidad() != null ? producto.getCantidad() : 0;
    }
}