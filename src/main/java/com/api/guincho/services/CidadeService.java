package com.api.guincho.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.guincho.models.Cidade;
import com.api.guincho.repositories.CidadeRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository cidadeRepository;
	
	public Cidade cadastrarCidade(Cidade cidade) {
		return cidadeRepository.save(cidade);
	}
	
	public List<Cidade> listarCidades() {
		return cidadeRepository.findAll();
	}
	
    public Cidade editarCidade(Long id, Cidade cidadeAtualizada) {
        return cidadeRepository.findById(id)
            .map(cidade -> {
                cidade.setCep(cidadeAtualizada.getCep());
                cidade.setNome(cidadeAtualizada.getNome());
                cidade.setEstado(cidadeAtualizada.getEstado());
                return cidadeRepository.save(cidade);
            })
            .orElseThrow(() -> new EntityNotFoundException("Cidade não encontrada."));
    }

    public void removerCidade(Long id) {
        Cidade cidade = cidadeRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Cidade não encontrada."));
        cidadeRepository.delete(cidade);
    }
    
}
