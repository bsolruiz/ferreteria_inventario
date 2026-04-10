package com.tu.paquete.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tu.paquete.model.Usuario;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByCorreo(String correo);
}