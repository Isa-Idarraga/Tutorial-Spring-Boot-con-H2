package com.eafit.nutrition.repository;

import com.eafit.nutrition.model.Nutricionista;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NutricionistaRepository extends JpaRepository<Nutricionista, Long> {

    // Opción A — JOIN FETCH (evita N+1 cargando pacientes en una sola query)
    @Query("SELECT n FROM Nutricionista n LEFT JOIN FETCH n.pacientes WHERE n.id = :id")
    Optional<Nutricionista> findByIdWithPacientes(@Param("id") Long id);

    // Opción B — EntityGraph (otra forma de indicar que cargue 'pacientes')
    @EntityGraph(attributePaths = {"pacientes"})
    @Query("SELECT n FROM Nutricionista n WHERE n.id = :id")
    Optional<Nutricionista> findByIdWithPacientesGraph(@Param("id") Long id);
}
