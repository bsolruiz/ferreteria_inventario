package com.inventario.api.controller;

import com.inventario.api.dtos.RolDTO;
import com.inventario.api.services.RolService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RolController {

    private final RolService rolService;

    @GetMapping
    public List<RolDTO> listarRoles() {
        return rolService.listarRoles();
    }
}