package com.example.demo.controller;

import com.example.demo.model.Departamento;
import com.example.demo.service.DepartamentoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departamentos")
public class DepartamentoController {

    private final DepartamentoService service;

    public DepartamentoController(DepartamentoService service) {
        this.service = service;
    }

    // 1. Obtener todos los departamentos (Read all)
    // GET /api/departamentos
    @GetMapping
    public List<Departamento> listar() {
        return service.listar();
    }

    // 2. Obtener un departamento por ID (Read by ID)
    // GET /api/departamentos/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Departamento> obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 3. Crear un nuevo departamento (Create)
    // POST /api/departamentos
    @PostMapping
    public ResponseEntity<Departamento> guardar(@RequestBody Departamento departamento) {
        Departamento nuevoDepartamento = service.guardar(departamento);
        return new ResponseEntity<>(nuevoDepartamento, HttpStatus.CREATED);
    }

    // 4. Actualizar un departamento existente (Update)
    // PUT /api/departamentos/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Departamento> actualizar(@PathVariable Long id, @RequestBody Departamento departamento) {
        return service.actualizar(id, departamento)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 5. Eliminar un departamento (Delete)
    // DELETE /api/departamentos/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (service.eliminar(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
