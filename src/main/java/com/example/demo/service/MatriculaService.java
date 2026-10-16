package com.example.demo.service;

import com.example.demo.model.Matricula;
import com.example.demo.repository.MatriculaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MatriculaService {

    private final MatriculaRepository repository;

    public MatriculaService(MatriculaRepository repository) {
        this.repository = repository;
    }

    // 1. Obtener todas las matriculas (Read all)
    public List<Matricula> listar() {
        return repository.findAll();
    }

    // 2. Obtener una matricula por ID (Read by ID)
    public Optional<Matricula> obtenerPorId(Long id) {
        return repository.findById(id);
    }

    // 3. Crear o guardar una matricula (Create)
    public Matricula guardar(Matricula matricula) {
        return repository.save(matricula);
    }

    // 4. Actualizar una matricula existente (Update)
    public Optional<Matricula> actualizar(Long id, Matricula matriculaActualizada) {
        return repository.findById(id).map(matriculaExistente -> {
            matriculaExistente.setPeriodo(matriculaActualizada.getPeriodo());
            matriculaExistente.setFecha(matriculaActualizada.getFecha());
            matriculaExistente.setCosto(matriculaActualizada.getCosto());
            return repository.save(matriculaExistente);
        });
    }

    // 5. Eliminar una matricula por ID (Delete)
    public boolean eliminar(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
