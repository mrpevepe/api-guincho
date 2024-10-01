package com.api.guincho.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.guincho.models.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {

}
