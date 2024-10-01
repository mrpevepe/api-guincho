package com.api.guincho.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.guincho.models.Motorista;
import com.api.guincho.models.Servico;
import com.api.guincho.services.MotoristaService;
import com.api.guincho.services.ServicosService;

@RestController
@RequestMapping("/api/servico")
public class ServicosController {

    @Autowired
    private ServicosService servicosService;

    @Autowired
    private MotoristaService motoristaService;

    @GetMapping
    public ResponseEntity<List<Servico>> listarServicos() {
        List<Servico> servicos = servicosService.listarServicos();
        return ResponseEntity.ok(servicos);
    }
    
    @PostMapping("/novo")
    public ResponseEntity<Servico> criarServico(@RequestBody Servico servico) {
        Servico novoServico = servicosService.criarServico(servico);
        return ResponseEntity.ok(novoServico);
    }
    
    @GetMapping("/aguardando")
    public ResponseEntity<List<Servico>> servicosEmAguardo() {
    	List<Servico> servicosAguardando = servicosService.listarServicosAguardando();
    	return ResponseEntity.ok(servicosAguardando);
    }
    
    @GetMapping("/em-andamento")
    public ResponseEntity<List<Servico>> servicosEmAndamento() {
    	List<Servico> servicosAndamento = servicosService.listarServicosEmAndamento();
    	return ResponseEntity.ok(servicosAndamento);
    }
    

    @PutMapping("/{servicoId}/adicionar-motorista/{motoristaId}")
    public ResponseEntity<Servico> atrelarGuincho(@PathVariable Long servicoId, @PathVariable Long motoristaId) {
        Motorista guincho = motoristaService.buscarPorId(motoristaId);
        Servico servicoAtualizado = servicosService.atrelarGuincho(servicoId, guincho);
        return ResponseEntity.ok(servicoAtualizado);
    }

    @PutMapping("/concluir/{servicoId}")
    public ResponseEntity<Servico> concluirServico(@PathVariable Long servicoId) {
        Servico servicoConcluido = servicosService.concluirServico(servicoId);
        return ResponseEntity.ok(servicoConcluido);
    }
    
}
