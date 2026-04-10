package com.inventario.api.service;

import com.inventario.api.dtos.DetalleMovimientoDTO;
import com.inventario.api.dtos.MovimientoRequestDTO;
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
    private final DetalleMovimientoRepository detalleRepository;
    private final ProductoRepository productoRepository;
    private final UsuarioRepository usuarioRepository;

    @Transactional
    public Movimiento crearMovimiento(MovimientoRequestDTO dto) {

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        TipoMovimiento tipo = TipoMovimiento.valueOf(dto.getTipoMovimiento().toUpperCase());

        Movimiento movimiento = Movimiento.builder()
                .tipoMovimiento(tipo)
                .fechaRegistro(LocalDateTime.now())
                .usuario(usuario)
                .build();

        movimiento = movimientoRepository.save(movimiento);

        for (DetalleMovimientoDTO det : dto.getDetalles()) {

            Producto producto = productoRepository.findById(det.getProductoId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            int stockActual = calcularStock(producto.getIdProducto());

            if (tipo == TipoMovimiento.SALIDA && det.getCantidad() > stockActual) {
                throw new RuntimeException("Stock insuficiente para el producto: " + producto.getNombreProducto());
            }

            DetalleMovimiento detalle = DetalleMovimiento.builder()
                    .producto(producto)
                    .movimiento(movimiento)
                    .cantidad(det.getCantidad())
                    .build();

            detalleRepository.save(detalle);
        }

        return movimiento;
    }

    public int calcularStock(Integer productoId) {

        Integer entradas = detalleRepository.sumarEntradas(productoId);
        Integer salidas = detalleRepository.sumarSalidas(productoId);

        entradas = (entradas == null) ? 0 : entradas;
        salidas = (salidas == null) ? 0 : salidas;

        return entradas - salidas;
    }
}