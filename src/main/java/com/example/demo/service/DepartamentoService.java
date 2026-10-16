package com.example.demo.service;

import com.example.demo.model.Departamento;
import com.example.demo.repository.DepartamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartamentoService {

    private final DepartamentoRepository repository;

    public DepartamentoService(DepartamentoRepository repository) {
        this.repository = repository;
    }

    // 1. Obtener todos los departamentos (Read all)
    public List<Departamento> listar() {
        return repository.findAll();
    }

    // 2. Obtener un departamento por ID (Read by ID)
    public Optional<Departamento> obtenerPorId(Long id) {
        return repository.findById(id);
    }

    // 3. Crear o guardar un departamento (Create)
    public Departamento guardar(Departamento departamento) {
        return repository.save(departamento);
    }

    // 4. Actualizar un departamento existente (Update)
    public Optional<Departamento> actualizar(Long id, Departamento departamentoActualizado) {
        return repository.findById(id).map(departamentoExistente -> {
            departamentoExistente.setNombre(departamentoActualizado.getNombre());
            departamentoExistente.setPresupuesto(departamentoActualizado.getPresupuesto());
            return repository.save(departamentoExistente);
        });
    }

    // 5. Eliminar un departamento por ID (Delete)
    public boolean eliminar(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
