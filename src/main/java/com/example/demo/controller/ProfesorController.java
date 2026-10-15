package com.example.demo.controller;

import com.example.demo.model.Profesor;
import com.example.demo.service.ProfesorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profesores")
public class ProfesorController {

    private final ProfesorService service;

    public ProfesorController(ProfesorService service) {
        this.service = service;
    }

    // 1. Obtener todos los profesores (Read all)
    // GET /api/profesores
    @GetMapping
    public List<Profesor> listar() {
        return service.listar();
    }

    // 2. Obtener un profesor por ID (Read by ID)
    // GET /api/profesores/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Profesor> obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 3. Crear un nuevo profesor (Create)
    // POST /api/profesores
    @PostMapping
    public ResponseEntity<Profesor> guardar(@RequestBody Profesor profesor) {
        Profesor nuevoProfesor = service.guardar(profesor);
        return new ResponseEntity<>(nuevoProfesor, HttpStatus.CREATED);
    }

    // 4. Actualizar un profesor existente (Update)
    // PUT /api/profesores/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Profesor> actualizar(@PathVariable Long id, @RequestBody Profesor profesor) {
        return service.actualizar(id, profesor)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 5. Eliminar un profesor (Delete)
    // DELETE /api/profesores/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (service.eliminar(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
