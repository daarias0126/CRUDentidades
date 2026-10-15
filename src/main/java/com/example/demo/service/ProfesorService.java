package com.example.demo.service;

import com.example.demo.model.Profesor;
import com.example.demo.repository.ProfesorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfesorService {

    private final ProfesorRepository repository;

    public ProfesorService(ProfesorRepository repository) {
        this.repository = repository;
    }

    // 1. Obtener todos los profesores (Read all)
    public List<Profesor> listar() {
        return repository.findAll();
    }

    // 2. Obtener un profesor por ID (Read by ID)
    public Optional<Profesor> obtenerPorId(Long id) {
        return repository.findById(id);
    }

    // 3. Crear o guardar un profesor (Create)
    public Profesor guardar(Profesor profesor) {
        return repository.save(profesor);
    }

    // 4. Actualizar un profesor existente (Update)
    public Optional<Profesor> actualizar(Long id, Profesor profesorActualizado) {
        return repository.findById(id).map(profesorExistente -> {
            profesorExistente.setNombre(profesorActualizado.getNombre());
            profesorExistente.setEspecialidad(profesorActualizado.getEspecialidad());
            profesorExistente.setEmail(profesorActualizado.getEmail());
            return repository.save(profesorExistente);
        });
    }

    // 5. Eliminar un profesor por ID (Delete)
    public boolean eliminar(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
