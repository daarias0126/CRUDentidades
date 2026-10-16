package com.example.demo.controller;

import com.example.demo.model.Facultad;
import com.example.demo.service.FacultadService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/facultades")
public class FacultadController {

    private final FacultadService service;

    public FacultadController(FacultadService service) {
        this.service = service;
    }

    // 1. Obtener todas las facultades (Read all)
    // GET /api/facultades
    @GetMapping
    public List<Facultad> listar() {
        return service.listar();
    }

    // 2. Obtener una facultad por ID (Read by ID)
    // GET /api/facultades/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Facultad> obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 3. Crear una nueva facultad (Create)
    // POST /api/facultades
    @PostMapping
    public ResponseEntity<Facultad> guardar(@RequestBody Facultad facultad) {
        Facultad nuevaFacultad = service.guardar(facultad);
        return new ResponseEntity<>(nuevaFacultad, HttpStatus.CREATED);
    }

    // 4. Actualizar una facultad existente (Update)
    // PUT /api/facultades/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Facultad> actualizar(@PathVariable Long id, @RequestBody Facultad facultad) {
        return service.actualizar(id, facultad)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 5. Eliminar una facultad (Delete)
    // DELETE /api/facultades/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (service.eliminar(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
