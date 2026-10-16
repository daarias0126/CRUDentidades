package com.example.demo.service;

import com.example.demo.model.Horario;
import com.example.demo.repository.HorarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HorarioService {

    private final HorarioRepository repository;

    public HorarioService(HorarioRepository repository) {
        this.repository = repository;
    }

    // 1. Obtener todos los horarios (Read all)
    public List<Horario> listar() {
        return repository.findAll();
    }

    // 2. Obtener un horario por ID (Read by ID)
    public Optional<Horario> obtenerPorId(Long id) {
        return repository.findById(id);
    }

    // 3. Crear o guardar un horario (Create)
    public Horario guardar(Horario horario) {
        return repository.save(horario);
    }

    // 4. Actualizar un horario existente (Update)
    public Optional<Horario> actualizar(Long id, Horario horarioActualizado) {
        return repository.findById(id).map(horarioExistente -> {
            horarioExistente.setDia(horarioActualizado.getDia());
            horarioExistente.setHoraInicio(horarioActualizado.getHoraInicio());
            horarioExistente.setHoraFin(horarioActualizado.getHoraFin());
            return repository.save(horarioExistente);
        });
    }

    // 5. Eliminar un horario por ID (Delete)
    public boolean eliminar(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
