package com.api.guincho.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.guincho.models.Caminhao;
import com.api.guincho.models.Cidade;
import com.api.guincho.models.Motorista;
import com.api.guincho.models.StatusMotorista;
import com.api.guincho.repositories.CaminhaoRepository;
import com.api.guincho.repositories.CidadeRepository;
import com.api.guincho.repositories.MotoristaRepository;

import jakarta.persistence.EntityNotFoundException;
@Service
public class MotoristaService {

    @Autowired
    private MotoristaRepository guinchoRepository;
    
    @Autowired
    private CidadeRepository cidadeRepository;
    
    @Autowired
    private CaminhaoRepository caminhaoRepository;
    
    public Motorista buscarPorId(Long motoristaId) {
        Optional<Motorista> motoristaOptional = guinchoRepository.findById(motoristaId);
        if (motoristaOptional.isPresent()) {
            return motoristaOptional.get();
        }
        throw new RuntimeException("Guincho não encontrado");
    }
    
    public List<Motorista> listarMotoristasDisponivel() {
    	return guinchoRepository.findByStatus(StatusMotorista.DISPONIVEL);
    }
    
    public List<Motorista> listarTodosMotoristas() {
        return guinchoRepository.findAll();
    }
    
    public Optional<Motorista> listarMotoristaPorId(Long id) {
    	return guinchoRepository.findById(id);
    }   
    
    public Motorista cadastrarMotorista(Motorista motorista, Long idCidade, Long idCaminhao) {
        Cidade cidade = cidadeRepository.findById(idCidade)
                .orElseThrow(() -> new RuntimeException("Cidade não encontrada"));
        Caminhao caminhao = caminhaoRepository.findById(idCaminhao)
        		.orElseThrow(() -> new RuntimeException("Caminhão nao encontrado"));
        
        motorista.setCidade(cidade);
        motorista.setStatus(StatusMotorista.DISPONIVEL);
        
        motorista.setCaminhao(caminhao);
        return guinchoRepository.save(motorista);
    }
    
    
    public Motorista editarMotorista(Long id, Motorista motoristaAtualizado, Long idCidade) {
    	Optional<Motorista> motoristaExistente = guinchoRepository.findById(id);
    	if (motoristaExistente.isPresent()) {
    		
    		Motorista motorista = motoristaExistente.get();
    		motorista.setNomeMotorista(motoristaAtualizado.getNomeMotorista());
    		
            Cidade cidade = cidadeRepository.findById(idCidade)
                    .orElseThrow(() -> new RuntimeException("Cidade não encontrada"));
    		motorista.setCidade(cidade);
    		
    		return guinchoRepository.save(motorista);
    	} else {
    		throw new EntityNotFoundException("Motorista nao encontrado.");
    	}
    }

    public void removerMotorista(Long id) {
    	Optional<Motorista> motoristaExistente = guinchoRepository.findById(id);
    	if (motoristaExistente.isPresent()) {
    		guinchoRepository.deleteById(id);
    	} else {
    		throw new EntityNotFoundException("Motorista nao encontrado.");
    	}
    }
}

