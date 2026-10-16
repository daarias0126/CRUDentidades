package com.example.demo.service;

import com.example.demo.model.Nota;
import com.example.demo.repository.NotaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotaService {

    private final NotaRepository repository;

    public NotaService(NotaRepository repository) {
        this.repository = repository;
    }

    // 1. Obtener todas las notas (Read all)
    public List<Nota> listar() {
        return repository.findAll();
    }

    // 2. Obtener una nota por ID (Read by ID)
    public Optional<Nota> obtenerPorId(Long id) {
        return repository.findById(id);
    }

    // 3. Crear o guardar una nota (Create)
    public Nota guardar(Nota nota) {
        return repository.save(nota);
    }

    // 4. Actualizar una nota existente (Update)
    public Optional<Nota> actualizar(Long id, Nota notaActualizada) {
        return repository.findById(id).map(notaExistente -> {
            notaExistente.setValor(notaActualizada.getValor());
            notaExistente.setPorcentaje(notaActualizada.getPorcentaje());
            notaExistente.setDescripcion(notaActualizada.getDescripcion());
            return repository.save(notaExistente);
        });
    }

    // 5. Eliminar una nota por ID (Delete)
    public boolean eliminar(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
