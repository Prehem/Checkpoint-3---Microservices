package br.com.fiap.checkpoint3.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.checkpoint3.model.Paciente;
import br.com.fiap.checkpoint3.repository.PacienteRepository;

@Service
public class PacienteService {
    @Autowired
    private PacienteRepository repository;

    public Paciente save(Paciente paciente) {
        return repository.save(paciente);
    }

    public List<Paciente> findAll(org.springframework.data.domain.Sort sort) {
        return repository.findAll(sort);
    }

    public Paciente findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Paciente não encontrado"));
    }

    public Paciente update(Long id, Paciente pacienteAtualizado) {
        Paciente paciente = findById(id);
        BeanUtils.copyProperties(pacienteAtualizado, paciente, "id", "createdAt");
        paciente.setUpdatedAt(LocalDateTime.now());
        return repository.save(paciente);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
