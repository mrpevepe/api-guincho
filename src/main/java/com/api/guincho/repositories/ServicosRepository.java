package com.api.guincho.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.guincho.models.Servico;
import com.api.guincho.models.StatusServico;
@Repository
public interface ServicosRepository extends JpaRepository<Servico, Long> {
	List<Servico> findByStatus(StatusServico status);
}
