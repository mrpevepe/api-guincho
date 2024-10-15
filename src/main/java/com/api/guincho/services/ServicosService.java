package com.api.guincho.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.guincho.models.Motorista;
import com.api.guincho.models.Servico;
import com.api.guincho.models.StatusCaminhao;
import com.api.guincho.models.StatusMotorista;
import com.api.guincho.models.StatusServico;
import com.api.guincho.repositories.ServicosRepository;

@Service
public class ServicosService {

	@Autowired
	private ServicosRepository servicosRepository;
	
    public Servico criarServico(Servico servico) {
        servico.setStatus(StatusServico.AGUARDANDO);
        servico.setDataSolicitacao(LocalDateTime.now());
        return servicosRepository.save(servico);
    }

    public List<Servico> listarServicosAguardando() {
    	return servicosRepository.findByStatus(StatusServico.AGUARDANDO);
    }
    
    public List<Servico> listarServicosEmAndamento() {
    	return servicosRepository.findByStatus(StatusServico.EM_ANDAMENTO);
    }
    
    public List<Servico> listarServicos() {
        return servicosRepository.findAll();
    }
    
    public Optional<Servico> listarServicoPorId(Long id) {
    	return servicosRepository.findById(id);
    }
    
    public Servico atrelarGuincho(Long servicoId, Motorista motorista) {
        Optional<Servico> servicoOptional = servicosRepository.findById(servicoId);
        if (servicoOptional.isPresent()) {
            Servico servico = servicoOptional.get();
            // exceptions
            if (servico.getStatus() == StatusServico.CONCLUIDO) {
                throw new RuntimeException("Não é possível alterar um serviço já concluído.");
            }
            if (servico.getStatus() == StatusServico.EM_ANDAMENTO && servico.getMotorista() != null) {
                if (!servico.getMotorista().getId().equals(motorista.getId())) {
                    throw new RuntimeException("Não é permitido alterar o motorista de um serviço em andamento.");
                }
            }
            if (motorista.getStatus() == StatusMotorista.EM_SERVICO) {
            	throw new RuntimeException("O Motorista já esta em outro servico");
            }
            // caminhao indisponivel
            if (motorista.getCaminhao().getStatus() == StatusCaminhao.INDISPONIVEL) {
            	throw new RuntimeException("O Caminhão do motorista está Indisponível.");
            }
            
            servico.setMotorista(motorista);
            motorista.setStatus(StatusMotorista.EM_SERVICO);
            motorista.getCaminhao().setStatus(StatusCaminhao.EM_SERVICO);
            servico.setDataInicio(LocalDateTime.now());
            servico.setStatus(StatusServico.EM_ANDAMENTO);
            return servicosRepository.save(servico);
        }
        throw new RuntimeException("Serviço não encontrado");
    }

    public Servico concluirServico(Long servicoId) {
        Optional<Servico> servicoOptional = servicosRepository.findById(servicoId);
        if (servicoOptional.isPresent()) {
            Servico servico = servicoOptional.get();

            if (servico.getStatus() == StatusServico.CONCLUIDO) {
                throw new RuntimeException("Não é possível alterar um serviço já concluído.");
            }
            servico.setStatus(StatusServico.CONCLUIDO);
            servico.setDataConclusao(LocalDateTime.now());
            servico.getMotorista().setStatus(StatusMotorista.DISPONIVEL);
            servico.getMotorista().getCaminhao().setStatus(StatusCaminhao.DISPONIVEL);
            return servicosRepository.save(servico);
        }
        throw new RuntimeException("Serviço não encontrado");
    }
}
