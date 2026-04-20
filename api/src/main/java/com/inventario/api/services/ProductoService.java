package com.inventario.api.services;

import com.inventario.api.dtos.ProductoDTO;
import com.inventario.api.dtos.ProductoResponseDTO;
import com.inventario.api.model.Categoria;
import com.inventario.api.model.Producto;
import com.inventario.api.repository.CategoriaRepository;
import com.inventario.api.repository.MovimientoRepository;
import com.inventario.api.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;
    private final MovimientoRepository movimientoRepository;
    // Crear
    public ProductoResponseDTO crearProducto(ProductoDTO dto) {
        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        if (productoRepository.existsByCodigoBarras(dto.getCodigoBarras())) {
            throw new RuntimeException("El código de barras ya existe");
        }
        if (productoRepository.existsByNombreProductoIgnoreCase(dto.getNombreProducto())) {
            throw new RuntimeException("Ya existe un producto con ese nombre");
        }
        Producto producto = Producto.builder()
                .nombreProducto(dto.getNombreProducto())
                .descripcion(dto.getDescripcion())
                .precio(dto.getPrecio())
                .codigoBarras(dto.getCodigoBarras())
                .categoria(categoria)
                .cantidad(0) // la cantidad la maneja movimientos, no creación
                .build();

        return mapToDTO(productoRepository.save(producto));
    }

    // Listar todos
    public List<ProductoResponseDTO> listarProductos() {
        return productoRepository.findAllConCategoria()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // Obtener por ID
    public ProductoResponseDTO obtenerPorId(Integer id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        return mapToDTO(producto);
    }

    // Actualizar (sin cantidad)
    public ProductoResponseDTO actualizarProducto(Integer id, ProductoDTO dto) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        // Validar código de barras si cambió
        if (!producto.getCodigoBarras().equals(dto.getCodigoBarras())
                && productoRepository.existsByCodigoBarras(dto.getCodigoBarras())) {
            throw new RuntimeException("El código de barras ya existe");
        }

        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        producto.setNombreProducto(dto.getNombreProducto());
        producto.setDescripcion(dto.getDescripcion());
        producto.setPrecio(dto.getPrecio());
        producto.setCodigoBarras(dto.getCodigoBarras());
        producto.setCategoria(categoria);
        // cantidad NO se toca aquí

        return mapToDTO(productoRepository.save(producto));
    }

    public void eliminarProducto(Integer id) {
        if (!productoRepository.existsById(id)) {
            throw new RuntimeException("Producto no encontrado");
        }
        if (movimientoRepository.existsByProducto_IdProducto(id)) {
            throw new RuntimeException("No se puede eliminar el producto porque tiene movimientos registrados");
        }
        productoRepository.deleteById(id);
    }

    // Mapper
    private ProductoResponseDTO mapToDTO(Producto p) {
        return ProductoResponseDTO.builder()
                .idProducto(p.getIdProducto())
                .nombreProducto(p.getNombreProducto())
                .descripcion(p.getDescripcion())
                .precio(p.getPrecio())
                .codigoBarras(p.getCodigoBarras())
                .cantidad(p.getCantidad())
                .categoriaId(p.getCategoria().getIdCategoria())
                .categoriaNombre(p.getCategoria().getNombreCategoria())
                .fechaCreacion(p.getFechaCreacion())
                .build();
    }
}