package com.tu.paquete.controller;

import com.tu.paquete.dto.UsuarioDTO;
import com.tu.paquete.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    //crear
    @PostMapping
    public UsuarioDTO crearUsuario(@RequestBody UsuarioDTO dto) {
        return usuarioService.crearUsuario(dto);
    }

    //todos
    @GetMapping
    public List<UsuarioDTO> listarUsuarios() {
        return usuarioService.listarUsuarios();
    }

    //id
    @GetMapping("/{id}")
    public UsuarioDTO obtenerPorId(@PathVariable Long id) {
        return usuarioService.obtenerPorId(id);
    }

    //actualizar
    @PutMapping("/{id}")
    public UsuarioDTO actualizarUsuario(
            @PathVariable Long id,
            @RequestBody UsuarioDTO dto
    ) {
        return usuarioService.actualizarUsuario(id, dto);
    }

    //eliminar - estado
    @DeleteMapping("/{id}")
    public String eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
        return "Usuario eliminado correctamente";
    }
}