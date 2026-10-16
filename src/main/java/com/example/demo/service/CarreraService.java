package com.example.demo.service;

import com.example.demo.model.Carrera;
import com.example.demo.repository.CarreraRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarreraService {

    private final CarreraRepository repository;

    public CarreraService(CarreraRepository repository) {
        this.repository = repository;
    }

    // 1. Obtener todas las carreras (Read all)
    public List<Carrera> listar() {
        return repository.findAll();
    }

    // 2. Obtener una carrera por ID (Read by ID)
    public Optional<Carrera> obtenerPorId(Long id) {
        return repository.findById(id);
    }

    // 3. Crear o guardar una carrera (Create)
    public Carrera guardar(Carrera carrera) {
        return repository.save(carrera);
    }

    // 4. Actualizar una carrera existente (Update)
    public Optional<Carrera> actualizar(Long id, Carrera carreraActualizada) {
        return repository.findById(id).map(carreraExistente -> {
            carreraExistente.setNombre(carreraActualizada.getNombre());
            carreraExistente.setSemestres(carreraActualizada.getSemestres());
            carreraExistente.setTitulo(carreraActualizada.getTitulo());
            return repository.save(carreraExistente);
        });
    }

    // 5. Eliminar una carrera por ID (Delete)
    public boolean eliminar(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
