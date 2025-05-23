package br.com.fiap.checkpoint3.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.fiap.checkpoint3.model.Profissional;
import br.com.fiap.checkpoint3.repository.ProfissionalRepository;

@Service
public class ProfissionalService {
      @Autowired
    private ProfissionalRepository repository;

    public Profissional save(Profissional profissional) {
        return repository.save(profissional);
    }

    public List<Profissional> findAll(Sort sort) {
        return repository.findAll(sort);
    }

    public Profissional findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Profissional não encontrado"));
    }

    public Profissional update(Long id, Profissional profissionalAtualizado) {
        Profissional profissional = findById(id);
        BeanUtils.copyProperties(profissionalAtualizado, profissional, "id", "createdAt");
        profissional.setUpdatedAt(LocalDateTime.now());
        return repository.save(profissional);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Map<String, Object> getStats(Long id) {
        Object[] result = repository.findStatsByProfissionalId(id);
        Long totalConsultas = (Long) result[0];
        BigDecimal totalValor = (BigDecimal) result[1];
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalConsultas", totalConsultas);
        stats.put("valorTotalRecebido", totalValor);
        return stats;
    }
}
