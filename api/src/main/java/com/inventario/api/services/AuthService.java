package com.inventario.api.services;

import com.inventario.api.dtos.login.LoginRequestDTO;
import com.inventario.api.dtos.login.LoginResponseDTO;
import com.inventario.api.model.Usuario;
import com.inventario.api.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public LoginResponseDTO login(LoginRequestDTO request) {

        Usuario usuario = usuarioRepository.findByCorreo(request.getCorreo())
                .orElseThrow(() -> new RuntimeException("Credenciales incorrectas"));

        if (usuario.getEstado() == null || usuario.getEstado() == 0L) {
            throw new RuntimeException("Usuario inactivo. Contacta al administrador.");
        }

        if (!passwordEncoder.matches(request.getContrasena(), usuario.getContrasena())) {
            throw new RuntimeException("Credenciales incorrectas");
        }

        return LoginResponseDTO.builder()
                .idUsuario(usuario.getIdUsuario())
                .nombres(usuario.getNombres())
                .correo(usuario.getCorreo())
                .rolNombre(usuario.getRol().getNombreRol())
                .build();
    }
}