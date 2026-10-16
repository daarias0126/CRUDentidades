package com.example.demo.controller;

import com.example.demo.model.Matricula;
import com.example.demo.service.MatriculaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matriculas")
public class MatriculaController {

    private final MatriculaService service;

    public MatriculaController(MatriculaService service) {
        this.service = service;
    }

    // 1. Obtener todas las matriculas (Read all)
    // GET /api/matriculas
    @GetMapping
    public List<Matricula> listar() {
        return service.listar();
    }

    // 2. Obtener una matricula por ID (Read by ID)
    // GET /api/matriculas/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Matricula> obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 3. Crear una nueva matricula (Create)
    // POST /api/matriculas
    @PostMapping
    public ResponseEntity<Matricula> guardar(@RequestBody Matricula matricula) {
        Matricula nuevaMatricula = service.guardar(matricula);
        return new ResponseEntity<>(nuevaMatricula, HttpStatus.CREATED);
    }

    // 4. Actualizar una matricula existente (Update)
    // PUT /api/matriculas/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Matricula> actualizar(@PathVariable Long id, @RequestBody Matricula matricula) {
        return service.actualizar(id, matricula)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 5. Eliminar una matricula (Delete)
    // DELETE /api/matriculas/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (service.eliminar(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
