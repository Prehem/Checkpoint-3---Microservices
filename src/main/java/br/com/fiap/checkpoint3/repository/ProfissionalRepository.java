package br.com.fiap.checkpoint3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.fiap.checkpoint3.model.Profissional;

public interface ProfissionalRepository extends JpaRepository<Profissional, Long> {
    @Query("SELECT COUNT(c), SUM(c.valorConsulta) FROM Consulta c WHERE c.profissional.id = :id")
    Object[] findStatsByProfissionalId(@Param("id") Long id);
}

