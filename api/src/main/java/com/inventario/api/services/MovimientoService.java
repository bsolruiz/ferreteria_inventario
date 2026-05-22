package com.inventario.api.services;

import com.inventario.api.dtos.MovimientoDTO;
import com.inventario.api.exceptions.DatoInvalidoException;
import com.inventario.api.exceptions.RecursoNoEncontradoException;
import com.inventario.api.exceptions.StockInsuficienteException;
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


        if (dto.getCantidad() == null || dto.getCantidad() <= 0) {
            throw new DatoInvalidoException("La cantidad debe ser mayor a 0");
        }

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RecursoNoEncontradoException("Usuario no encontrado"));

        Producto producto = productoRepository.findById(dto.getProductoId().intValue())
                .orElseThrow(() -> new RecursoNoEncontradoException("Producto no encontrado"));

        TipoMovimiento tipo;
        try {
            tipo = TipoMovimiento.valueOf(dto.getTipoMovimiento().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new DatoInvalidoException("Tipo de movimiento inválido: " + dto.getTipoMovimiento());
        }

        int stockActual = producto.getCantidad() != null ? producto.getCantidad() : 0;

        if (tipo == TipoMovimiento.SALIDA && stockActual < dto.getCantidad()) {
            throw new StockInsuficienteException("Stock insuficiente. Stock actual: " + stockActual +
                    ", cantidad solicitada: " + dto.getCantidad());
        }

        if (tipo == TipoMovimiento.ENTRADA) {
            producto.setCantidad(stockActual + dto.getCantidad());
        } else {
            producto.setCantidad(stockActual - dto.getCantidad());
        }
        productoRepository.save(producto);

        Movimiento movimiento = Movimiento.builder()
                .tipoMovimiento(tipo)
                .fechaRegistro(LocalDateTime.now())
                .motivo(dto.getMotivo())
                .cantidad(dto.getCantidad())
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