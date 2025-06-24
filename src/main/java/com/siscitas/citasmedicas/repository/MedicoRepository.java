package com.siscitas.citasmedicas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.siscitas.citasmedicas.model.Medico;
@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {
	Optional<Medico> findByDni(Integer dni);
	List<Medico> findByNombreContainingIgnoreCase(String nombre);
}
