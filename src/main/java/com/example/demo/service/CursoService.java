package com.example.demo.service;

import com.example.demo.model.Curso;
import com.example.demo.repository.CursoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    private final CursoRepository repository;

    public CursoService(CursoRepository repository) {
        this.repository = repository;
    }

    // 1. Obtener todos los cursos (Read all)
    public List<Curso> listar() {
        return repository.findAll();
    }

    // 2. Obtener un curso por ID (Read by ID)
    public Optional<Curso> obtenerPorId(Long id) {
        return repository.findById(id);
    }

    // 3. Crear o guardar un curso (Create)
    public Curso guardar(Curso curso) {
        return repository.save(curso);
    }

    // 4. Actualizar un curso existente (Update)
    public Optional<Curso> actualizar(Long id, Curso cursoActualizado) {
        return repository.findById(id).map(cursoExistente -> {
            cursoExistente.setNombre(cursoActualizado.getNombre());
            cursoExistente.setHoras(cursoActualizado.getHoras());
            cursoExistente.setCreditos(cursoActualizado.getCreditos());
            return repository.save(cursoExistente);
        });
    }

    // 5. Eliminar un curso por ID (Delete)
    public boolean eliminar(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
