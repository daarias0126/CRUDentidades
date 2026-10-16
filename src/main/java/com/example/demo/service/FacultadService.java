package com.example.demo.service;

import com.example.demo.model.Facultad;
import com.example.demo.repository.FacultadRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacultadService {

    private final FacultadRepository repository;

    public FacultadService(FacultadRepository repository) {
        this.repository = repository;
    }

    // 1. Obtener todas las facultades (Read all)
    public List<Facultad> listar() {
        return repository.findAll();
    }

    // 2. Obtener una facultad por ID (Read by ID)
    public Optional<Facultad> obtenerPorId(Long id) {
        return repository.findById(id);
    }

    // 3. Crear o guardar una facultad (Create)
    public Facultad guardar(Facultad facultad) {
        return repository.save(facultad);
    }

    // 4. Actualizar una facultad existente (Update)
    public Optional<Facultad> actualizar(Long id, Facultad facultadActualizada) {
        return repository.findById(id).map(facultadExistente -> {
            facultadExistente.setNombre(facultadActualizada.getNombre());
            facultadExistente.setUbicacion(facultadActualizada.getUbicacion());
            return repository.save(facultadExistente);
        });
    }

    // 5. Eliminar una facultad por ID (Delete)
    public boolean eliminar(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
