package com.api.guincho.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.guincho.models.Motorista;
import com.api.guincho.models.StatusMotorista;

@Repository
public interface MotoristaRepository extends JpaRepository<Motorista, Long> {
	 List<Motorista> findByStatus(StatusMotorista status);
}

