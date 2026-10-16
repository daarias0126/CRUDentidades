package com.example.demo.controller;

import com.example.demo.model.Horario;
import com.example.demo.service.HorarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/horarios")
public class HorarioController {

    private final HorarioService service;

    public HorarioController(HorarioService service) {
        this.service = service;
    }

    // 1. Obtener todos los horarios (Read all)
    // GET /api/horarios
    @GetMapping
    public List<Horario> listar() {
        return service.listar();
    }

    // 2. Obtener un horario por ID (Read by ID)
    // GET /api/horarios/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Horario> obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 3. Crear un nuevo horario (Create)
    // POST /api/horarios
    @PostMapping
    public ResponseEntity<Horario> guardar(@RequestBody Horario horario) {
        Horario nuevoHorario = service.guardar(horario);
        return new ResponseEntity<>(nuevoHorario, HttpStatus.CREATED);
    }

    // 4. Actualizar un horario existente (Update)
    // PUT /api/horarios/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Horario> actualizar(@PathVariable Long id, @RequestBody Horario horario) {
        return service.actualizar(id, horario)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 5. Eliminar un horario (Delete)
    // DELETE /api/horarios/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (service.eliminar(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
