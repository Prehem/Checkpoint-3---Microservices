package br.com.fiap.checkpoint3.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.checkpoint3.model.Consulta;
import br.com.fiap.checkpoint3.model.StatusConsulta;
import br.com.fiap.checkpoint3.service.ConsultaService;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {
    
    @Autowired
    private ConsultaService service;

    @PostMapping
    public ResponseEntity<Consulta> create(@RequestBody Consulta consulta) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(consulta));
    }

    @GetMapping
    public List<Consulta> list() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Consulta get(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public Consulta update(@PathVariable Long id, @RequestBody Consulta consulta) {
        return service.update(id, consulta);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/filtrar")
    public List<Consulta> filtrarPorStatusEPeriodo(
        @RequestParam StatusConsulta status,
        @RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate data_de,
        @RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate data_ate
    ) {
        return service.buscarPorStatusEPeriodo(status, data_de, data_ate);
    }

    @GetMapping("/paciente/{id}")
    public List<Consulta> porPaciente(
        @PathVariable Long id,
        @RequestParam StatusConsulta status,
        @RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate data_de,
        @RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate data_ate
    ) {
        return service.buscarPorPacienteEPeriodo(id, status, data_de, data_ate);
    }

    @GetMapping("/profissional/{id}")
    public List<Consulta> porProfissional(
        @PathVariable Long id,
        @RequestParam StatusConsulta status,
        @RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate data_de,
        @RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate data_ate
    ) {
        return service.buscarPorProfissionalEPeriodo(id, status, data_de, data_ate);
    }
}
