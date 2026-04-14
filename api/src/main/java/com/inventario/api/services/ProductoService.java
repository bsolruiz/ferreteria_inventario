package com.inventario.api.services;

import com.inventario.api.dtos.ProductoDTO;
import com.inventario.api.model.Categoria;
import com.inventario.api.model.Producto;
import com.inventario.api.repository.CategoriaRepository;
import com.inventario.api.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;

    public Producto crearProducto(ProductoDTO dto) {

        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        if (productoRepository.existsByCodigoBarras(dto.getCodigoBarras())) {
            throw new RuntimeException("El código de barras ya existe");
        }

        Producto producto = Producto.builder()
                .nombreProducto(dto.getNombreProducto())
                .descripcion(dto.getDescripcion())
                .precio(dto.getPrecio())
                .codigoBarras(dto.getCodigoBarras())
                .categoria(categoria)
                .cantidad(0)
                .fechaCreacion(LocalDateTime.now())
                .fechaActualizacion(LocalDateTime.now())
                .build();

        return productoRepository.save(producto);
    }
}