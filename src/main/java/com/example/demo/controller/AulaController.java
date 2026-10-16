package com.example.demo.controller;

import com.example.demo.model.Aula;
import com.example.demo.service.AulaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/aulas")
public class AulaController {

    private final AulaService service;

    public AulaController(AulaService service) {
        this.service = service;
    }

    // 1. Obtener todas las aulas (Read all)
    // GET /api/aulas
    @GetMapping
    public List<Aula> listar() {
        return service.listar();
    }

    // 2. Obtener un aula por ID (Read by ID)
    // GET /api/aulas/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Aula> obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 3. Crear una nueva aula (Create)
    // POST /api/aulas
    @PostMapping
    public ResponseEntity<Aula> guardar(@RequestBody Aula aula) {
        Aula nuevaAula = service.guardar(aula);
        return new ResponseEntity<>(nuevaAula, HttpStatus.CREATED);
    }

    // 4. Actualizar un aula existente (Update)
    // PUT /api/aulas/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Aula> actualizar(@PathVariable Long id, @RequestBody Aula aula) {
        return service.actualizar(id, aula)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 5. Eliminar un aula (Delete)
    // DELETE /api/aulas/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (service.eliminar(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
