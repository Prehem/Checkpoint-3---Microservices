package br.com.fiap.checkpoint3.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.checkpoint3.model.Consulta;
import br.com.fiap.checkpoint3.model.StatusConsulta;
import br.com.fiap.checkpoint3.repository.ConsultaRepository;

@Service
public class ConsultaService {
    
    @Autowired
    private ConsultaRepository repository;

    public Consulta save(Consulta consulta) {
        return repository.save(consulta);
    }

    public List<Consulta> findAll() {
        return repository.findAll();
    }

    public Consulta findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Consulta não encontrada"));
    }

    public Consulta update(Long id, Consulta consultaAtualizada) {
        Consulta consulta = findById(id);
        BeanUtils.copyProperties(consultaAtualizada, consulta, "id", "createdAt");
        consulta.setUpdatedAt(LocalDateTime.now());
        return repository.save(consulta);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public List<Consulta> buscarPorStatusEPeriodo(StatusConsulta status, LocalDate dataInicio, LocalDate dataFim) {
        return repository.findByStatusConsultaAndDataConsultaBetween(
            status,
            dataInicio.atStartOfDay(),
            dataFim.atTime(LocalTime.MAX)
        );
    }

    public List<Consulta> buscarPorPacienteEPeriodo(Long pacienteId, StatusConsulta status, LocalDate dataInicio, LocalDate dataFim) {
        return repository.findByPacienteIdAndStatusConsultaAndDataConsultaBetween(
            pacienteId,
            status,
            dataInicio.atStartOfDay(),
            dataFim.atTime(LocalTime.MAX)
        );
    }

    public List<Consulta> buscarPorProfissionalEPeriodo(Long profissionalId, StatusConsulta status, LocalDate dataInicio, LocalDate dataFim) {
        return repository.findByProfissionalIdAndStatusConsultaAndDataConsultaBetween(
            profissionalId,
            status,
            dataInicio.atStartOfDay(),
            dataFim.atTime(LocalTime.MAX)
        );
    }
}
