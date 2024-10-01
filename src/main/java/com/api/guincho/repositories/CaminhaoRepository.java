package com.api.guincho.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.guincho.models.Caminhao;

@Repository
public interface CaminhaoRepository extends JpaRepository<Caminhao, Long> {

}
