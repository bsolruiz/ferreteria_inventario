package com.inventario.api.services;

import com.inventario.api.dtos.RolDTO;
import com.inventario.api.model.Rol;
import com.inventario.api.repository.RolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RolService {

    private final RolRepository rolRepository;

    public List<RolDTO> listarRoles() {
        return rolRepository.findAll()
                .stream()
                .map(rol -> new RolDTO(rol.getIdRol(), rol.getNombreRol()))
                .collect(Collectors.toList());
    }
}