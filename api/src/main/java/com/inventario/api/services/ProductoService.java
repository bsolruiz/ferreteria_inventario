package com.tu.paquete.service;

import com.tu.paquete.dto.ProductoDTO;
import com.tu.paquete.model.*;
import com.tu.paquete.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;
    private final MovimientoRepository movimientoRepository;
    private final DetalleMovimientoRepository detalleRepository;
    private final UsuarioRepository usuarioRepository;

    @Transactional
    public Producto crearProducto(ProductoDTO dto) {

        if (dto.getCantidadInicial() == null || dto.getCantidadInicial() < 0) {
            throw new RuntimeException("Cantidad inicial inválida");
        }

        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        // Usuario por defecto (puedes ajustarlo luego)
        Usuario usuario = usuarioRepository.findById(1)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // 1. Crear producto
        Producto producto = Producto.builder()
                .nombreProducto(dto.getNombreProducto())
                .descripcion(dto.getDescripcion())
                .precio(dto.getPrecio())
                .codigoBarras(dto.getCodigoBarras())
                .categoria(categoria)
                .fechaCreacion(LocalDateTime.now())
                .build();

        producto = productoRepository.save(producto);

        // 2. Movimiento inicial
        Movimiento movimiento = Movimiento.builder()
                .tipoMovimiento(TipoMovimiento.ENTRADA)
                .fechaRegistro(LocalDateTime.now())
                .usuario(usuario)
                .build();

        movimiento = movimientoRepository.save(movimiento);

        // 3. Detalle
        DetalleMovimiento detalle = DetalleMovimiento.builder()
                .producto(producto)
                .movimiento(movimiento)
                .cantidad(dto.getCantidadInicial())
                .build();

        detalleRepository.save(detalle);

        return producto;
    }
}