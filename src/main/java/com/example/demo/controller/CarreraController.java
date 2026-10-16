package com.example.demo.controller;

import com.example.demo.model.Carrera;
import com.example.demo.service.CarreraService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carreras")
public class CarreraController {

    private final CarreraService service;

    public CarreraController(CarreraService service) {
        this.service = service;
    }

    // 1. Obtener todas las carreras (Read all)
    // GET /api/carreras
    @GetMapping
    public List<Carrera> listar() {
        return service.listar();
    }

    // 2. Obtener una carrera por ID (Read by ID)
    // GET /api/carreras/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Carrera> obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 3. Crear una nueva carrera (Create)
    // POST /api/carreras
    @PostMapping
    public ResponseEntity<Carrera> guardar(@RequestBody Carrera carrera) {
        Carrera nuevaCarrera = service.guardar(carrera);
        return new ResponseEntity<>(nuevaCarrera, HttpStatus.CREATED);
    }

    // 4. Actualizar una carrera existente (Update)
    // PUT /api/carreras/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Carrera> actualizar(@PathVariable Long id, @RequestBody Carrera carrera) {
        return service.actualizar(id, carrera)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 5. Eliminar una carrera (Delete)
    // DELETE /api/carreras/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (service.eliminar(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
