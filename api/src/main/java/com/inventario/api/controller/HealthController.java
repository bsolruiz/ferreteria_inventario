package com.inventario.api.controller;

import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.Map;

@RestController
public class HealthController {

    private final DataSource dataSource;

    public HealthController(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @GetMapping("/api/health")
    public ResponseEntity<Map<String, Object>> health() {
        try (Connection conn = dataSource.getConnection()) {
            return ResponseEntity.ok(Map.of(
                    "estado", "OK",
                    "baseDatos", conn.isValid(3)
            ));
        } catch (DataAccessException | java.sql.SQLException e) {
            return ResponseEntity.status(503)
                    .body(Map.of(
                            "estado", "ERROR",
                            "baseDatos", false,
                            "detalle", "Base de datos no disponible"
                    ));
        }
    }
}
