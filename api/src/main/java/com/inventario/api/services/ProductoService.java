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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;
    private final MovimientoRepository movimientoRepository;

    public ProductoResponseDTO crearProducto(ProductoDTO dto) {
        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        // Código de barras: único globalmente (activo o inactivo)
        if (productoRepository.existsByCodigoBarras(dto.getCodigoBarras())) {
            throw new RuntimeException("El código de barras ya existe");
        }

        // Nombre: único solo entre productos activos
        if (productoRepository.existsByNombreProductoIgnoreCaseAndActivoTrue(dto.getNombreProducto())) {
            throw new RuntimeException("Ya existe un producto activo con ese nombre");
        }

        Producto producto = Producto.builder()
                .nombreProducto(dto.getNombreProducto())
                .descripcion(dto.getDescripcion())
                .precio(dto.getPrecio())
                .codigoBarras(dto.getCodigoBarras())
                .categoria(categoria)
                .cantidad(0)
                .build();
        // activo se setea en @PrePersist

        return mapToDTO(productoRepository.save(producto));
    }

    public List<ProductoResponseDTO> listarProductos() {
        // findAllConCategoria ya filtra activo = true
        return productoRepository.findAllConCategoria()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public ProductoResponseDTO obtenerPorId(Integer id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        return mapToDTO(producto);
    }

    public ProductoResponseDTO actualizarProducto(Integer id, ProductoDTO dto) {

        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        // Validar nombre único entre productos activos
        if (!producto.getNombreProducto().equalsIgnoreCase(dto.getNombreProducto())
                && productoRepository.existsByNombreProductoIgnoreCaseAndActivoTrueAndIdProductoNot(
                dto.getNombreProducto(),
                id)) {

            throw new RuntimeException("Ya existe un producto activo con ese nombre");
        }

        // Validar código de barras solo si cambió
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

        return mapToDTO(productoRepository.save(producto));
    }

    @Transactional
    public void eliminarProducto(Integer id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        // Soft delete: inactivar, los movimientos permanecen intactos
        producto.setActivo(false);
        productoRepository.save(producto);
    }

    @Transactional
    public ProductoResponseDTO activarProducto(Integer id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        if (producto.getActivo()) {
            throw new RuntimeException("El producto ya está activo");
        }

        // Validar nombre único entre productos activos al reactivar
        if (productoRepository.existsByNombreProductoIgnoreCaseAndActivoTrue(producto.getNombreProducto())) {
            throw new RuntimeException("Ya existe un producto activo con ese nombre");
        }

        producto.setActivo(true);
        return mapToDTO(productoRepository.save(producto));
    }

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
                .tieneMovimientos(movimientoRepository.existsByProducto_IdProducto(p.getIdProducto()))
                .activo(p.getActivo())
                .build();
    }
}