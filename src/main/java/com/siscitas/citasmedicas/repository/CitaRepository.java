package com.siscitas.citasmedicas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.siscitas.citasmedicas.model.Cita;


@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {

}
