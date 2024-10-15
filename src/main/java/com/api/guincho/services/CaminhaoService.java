package com.api.guincho.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.guincho.models.Caminhao;
import com.api.guincho.models.StatusCaminhao;
import com.api.guincho.repositories.CaminhaoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CaminhaoService {

	@Autowired
	private CaminhaoRepository caminhaoRepository;
	
	public List<Caminhao> listarCaminhoes() {
		return caminhaoRepository.findAll();
	}

	public Caminhao cadastrarCaminhao(Caminhao caminhao) {
		caminhao.setStatus(StatusCaminhao.DISPONIVEL);
		return caminhaoRepository.save(caminhao);
	}
	
	public Caminhao editarCaminhao(Long id, Caminhao caminhaoAtualizado) {
		return caminhaoRepository.findById(id)
				.map(caminhao -> {
					caminhao.setCor(caminhaoAtualizado.getCor());
					caminhao.setFabricante(caminhaoAtualizado.getFabricante());
					caminhao.setPlaca(caminhaoAtualizado.getPlaca());
					caminhao.setStatus(caminhaoAtualizado.getStatus());
					return caminhaoRepository.save(caminhao);
		}).orElseThrow(() -> new EntityNotFoundException("Caminhão não encontrado."));
	}

	public void removerCaminhao(Long id) {
		Caminhao caminhao = caminhaoRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Caminhão não encontrado."));
		caminhaoRepository.delete(caminhao);
	}
	
}
