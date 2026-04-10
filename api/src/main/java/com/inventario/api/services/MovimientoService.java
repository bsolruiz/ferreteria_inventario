package com.tu.paquete.service;

import com.tu.paquete.dto.MovimientoDTO;
import com.tu.paquete.model.*;
import com.tu.paquete.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovimientoService {

    private final ProductoRepository productoRepository;
    private final MovimientoRepository movimientoRepository;
    private final DetalleMovimientoRepository detalleRepository;
    private final UsuarioRepository usuarioRepository;

    @Transactional
    public void registrarMovimiento(MovimientoDTO dto) {

        Producto producto = productoRepository.findById(dto.getProductoId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        TipoMovimiento tipo = TipoMovimiento.valueOf(dto.getTipoMovimiento().toUpperCase());

        // Validar stock si es SALIDA
        if (tipo == TipoMovimiento.SALIDA) {
            int stockActual = calcularStock(producto.getIdProducto());

            if (dto.getCantidad() > stockActual) {
                throw new RuntimeException("Stock insuficiente");
            }
        }

        // Crear movimiento
        Movimiento movimiento = Movimiento.builder()
                .tipoMovimiento(tipo)
                .fechaRegistro(LocalDateTime.now())
                .usuario(usuario)
                .build();

        movimiento = movimientoRepository.save(movimiento);

        // Crear detalle
        DetalleMovimiento detalle = DetalleMovimiento.builder()
                .producto(producto)
                .movimiento(movimiento)
                .cantidad(dto.getCantidad())
                .build();

        detalleRepository.save(detalle);
    }

    // 🔹 Calcular stock actual (simple)
    public int calcularStock(Integer productoId) {

        List<DetalleMovimiento> detalles = detalleRepository.findByProducto_IdProducto(productoId);

        int entradas = 0;
        int salidas = 0;

        for (DetalleMovimiento d : detalles) {
            if (d.getMovimiento().getTipoMovimiento() == TipoMovimiento.ENTRADA) {
                entradas += d.getCantidad();
            } else {
                salidas += d.getCantidad();
            }
        }

        return entradas - salidas;
    }
}