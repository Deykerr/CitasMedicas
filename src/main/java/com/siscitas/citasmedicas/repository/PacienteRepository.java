package com.siscitas.citasmedicas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.siscitas.citasmedicas.model.Paciente;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long>{
	Optional<Paciente> findByDni(Integer dni);
	 List<Paciente> findByNombreContainingIgnoreCase(String nombre);
}