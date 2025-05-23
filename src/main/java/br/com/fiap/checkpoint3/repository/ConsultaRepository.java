package br.com.fiap.checkpoint3.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.fiap.checkpoint3.model.Consulta;
import br.com.fiap.checkpoint3.model.StatusConsulta;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    List<Consulta> findByPacienteIdAndStatusConsultaAndDataConsultaBetween(
        Long pacienteId, StatusConsulta status, LocalDateTime dataInicio, LocalDateTime dataFim
    );

    List<Consulta> findByProfissionalIdAndStatusConsultaAndDataConsultaBetween(
        Long profissionalId, StatusConsulta status, LocalDateTime dataInicio, LocalDateTime dataFim
    );

    List<Consulta> findByStatusConsultaAndDataConsultaBetween(
        StatusConsulta status, LocalDateTime dataInicio, LocalDateTime dataFim
    );

    @Query("SELECT COUNT(c), SUM(c.valorConsulta) FROM Consulta c WHERE c.profissional.id = :id")
    Object[] findStatsByProfissionalId(@Param("id") Long id);
}

