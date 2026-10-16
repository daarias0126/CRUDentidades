package com.example.demo.service;

import com.example.demo.model.Aula;
import com.example.demo.repository.AulaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AulaService {

    private final AulaRepository repository;

    public AulaService(AulaRepository repository) {
        this.repository = repository;
    }

    // 1. Obtener todas las aulas (Read all)
    public List<Aula> listar() {
        return repository.findAll();
    }

    // 2. Obtener un aula por ID (Read by ID)
    public Optional<Aula> obtenerPorId(Long id) {
        return repository.findById(id);
    }

    // 3. Crear o guardar un aula (Create)
    public Aula guardar(Aula aula) {
        return repository.save(aula);
    }

    // 4. Actualizar un aula existente (Update)
    public Optional<Aula> actualizar(Long id, Aula aulaActualizada) {
        return repository.findById(id).map(aulaExistente -> {
            aulaExistente.setNumero(aulaActualizada.getNumero());
            aulaExistente.setCapacidad(aulaActualizada.getCapacidad());
            aulaExistente.setTipo(aulaActualizada.getTipo());
            return repository.save(aulaExistente);
        });
    }

    // 5. Eliminar un aula por ID (Delete)
    public boolean eliminar(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
