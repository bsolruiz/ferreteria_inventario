package com.inventario.api.services;

import com.inventario.api.dtos.UsuarioDTO;
import com.inventario.api.model.Rol;
import com.inventario.api.model.Usuario;
import com.inventario.api.repository.RolRepository;
import com.inventario.api.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;

    //crear
    public UsuarioDTO crearUsuario(UsuarioDTO dto) {

        // Validación de campos obligatorios
        if (dto.getNombres() == null || dto.getNombres().isBlank()) {
            throw new RuntimeException("El nombre es obligatorio");
        }

        if (dto.getCorreo() == null || dto.getCorreo().isBlank()) {
            throw new RuntimeException("El correo es obligatorio");
        }

        if (dto.getContrasena() == null || dto.getContrasena().isBlank()) {
            throw new RuntimeException("La contraseña es obligatoria");
        }

        if (dto.getRolId() == null) {
            throw new RuntimeException("El rol es obligatorio");
        }

        if (usuarioRepository.existsByCorreo(dto.getCorreo())) {
            throw new RuntimeException("El correo ya está registrado");
        }

        Rol rol = rolRepository.findById(dto.getRolId())
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

        Usuario usuario = new Usuario();
        usuario.setNombres(dto.getNombres());
        usuario.setCorreo(dto.getCorreo());
        usuario.setContrasena(passwordEncoder.encode(dto.getContrasena()));
        usuario.setRol(rol);

        return mapToDTO(usuarioRepository.save(usuario));
    }

    //actualizar
    public UsuarioDTO actualizarUsuario(Long id, UsuarioDTO dto) {

        // Validación de campos obligatorios
        if (dto.getNombres() == null || dto.getNombres().isBlank()) {
            throw new RuntimeException("El nombre es obligatorio");
        }

        if (dto.getCorreo() == null || dto.getCorreo().isBlank()) {
            throw new RuntimeException("El correo es obligatorio");
        }

        if (dto.getEstado() == null) {
            throw new RuntimeException("El estado es obligatorio");
        }

        if (dto.getRolId() == null) {
            throw new RuntimeException("El rol es obligatorio");
        }

        if (dto.getContrasena() == null)
        {
            throw new RuntimeException("la contrasena es obligatoria");

        }

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuario.setNombres(dto.getNombres());
        usuario.setCorreo(dto.getCorreo());
        usuario.setEstado(dto.getEstado());

        Rol rol = rolRepository.findById(dto.getRolId())
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
        usuario.setRol(rol);

        if (dto.getContrasena() != null && !dto.getContrasena().isBlank()) {
            usuario.setContrasena(passwordEncoder.encode(dto.getContrasena()));
        }

        return mapToDTO(usuarioRepository.save(usuario));
    }

    //estado
    public void eliminarUsuario(Long id) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuario.setEstado(0L);
        usuarioRepository.save(usuario);
    }

    //lista id
    public UsuarioDTO obtenerUsuario(Long id) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return mapToDTO(usuario);
    }

    //lista todos
    public List<UsuarioDTO> listarUsuarios() {

        return usuarioRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    //mapper
    private UsuarioDTO mapToDTO(Usuario usuario) {

        UsuarioDTO dto = new UsuarioDTO();
        dto.setIdUsuario(usuario.getIdUsuario());
        dto.setNombres(usuario.getNombres());
        dto.setCorreo(usuario.getCorreo());
        dto.setContrasena(null);
        dto.setRolId(usuario.getRol().getIdRol());
        dto.setEstado(usuario.getEstado());

        return dto;
    }
}