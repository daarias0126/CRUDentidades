package com.example.demo.controller;

import com.example.demo.model.Nota;
import com.example.demo.service.NotaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notas")
public class NotaController {

    private final NotaService service;

    public NotaController(NotaService service) {
        this.service = service;
    }

    // 1. Obtener todas las notas (Read all)
    // GET /api/notas
    @GetMapping
    public List<Nota> listar() {
        return service.listar();
    }

    // 2. Obtener una nota por ID (Read by ID)
    // GET /api/notas/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Nota> obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 3. Crear una nueva nota (Create)
    // POST /api/notas
    @PostMapping
    public ResponseEntity<Nota> guardar(@RequestBody Nota nota) {
        Nota nuevaNota = service.guardar(nota);
        return new ResponseEntity<>(nuevaNota, HttpStatus.CREATED);
    }

    // 4. Actualizar una nota existente (Update)
    // PUT /api/notas/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Nota> actualizar(@PathVariable Long id, @RequestBody Nota nota) {
        return service.actualizar(id, nota)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 5. Eliminar una nota (Delete)
    // DELETE /api/notas/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (service.eliminar(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
