package com.api.guincho.controllers;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.guincho.models.Motorista;
import com.api.guincho.services.MotoristaService;

@RestController
@RequestMapping("/api/motorista")
public class MotoristaController {

    @Autowired
    private MotoristaService motoristaService;
    
    @GetMapping
    public ResponseEntity<List<Motorista>> listarTodosMotoristas() {
        List<Motorista> motoristas = motoristaService.listarTodosMotoristas();
        return ResponseEntity.ok(motoristas);
    }
    
    @GetMapping("/listar-por-id/{id}")
    public ResponseEntity<Optional<Motorista>> listarMotoristaPorId(@PathVariable Long id) {
    	Optional<Motorista> motorista = motoristaService.listarMotoristaPorId(id);
    	return ResponseEntity.ok(motorista);
    }
    
    @GetMapping("/disponiveis")
    public ResponseEntity<List<Motorista>> listarMotoristaDisponivel() {
    	List<Motorista> motoristasDisponivel = motoristaService.listarMotoristasDisponivel();
    	return ResponseEntity.ok(motoristasDisponivel);
    }
    
    @PostMapping("/cadastrar")
    public ResponseEntity<Motorista> cadastrarMotorista(
            @RequestBody Map<String, Object> motoristaData) {
        String nomeMotorista = motoristaData.get("nomeMotorista").toString();
        Long idCidade = Long.valueOf(motoristaData.get("id_cidade").toString());
        Long idCaminhao = Long.valueOf(motoristaData.get("id_caminhao").toString());
        
        Motorista motorista = new Motorista();
        motorista.setNomeMotorista(nomeMotorista);

        Motorista novoGuincho = motoristaService.cadastrarMotorista(motorista, idCidade, idCaminhao);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoGuincho);
    }
    
    @PutMapping("/editar/{id}")
    public ResponseEntity<Motorista> editarMotorista(
            @PathVariable Long id,
            @RequestBody Map<String, Object> motoristaData) {

        // extrai os dados do JSON
        String nomeMotorista = motoristaData.get("nomeMotorista").toString();
        Long idCidade = Long.valueOf(motoristaData.get("id_cidade").toString());

        Motorista motoristaAtualizado = new Motorista();
        motoristaAtualizado.setNomeMotorista(nomeMotorista);

        Motorista motoristaEditado = motoristaService.editarMotorista(id, motoristaAtualizado, idCidade);
        return ResponseEntity.ok(motoristaEditado);
    }
    
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> removerMotorista(@PathVariable Long id) {
    	motoristaService.removerMotorista(id);
    	return ResponseEntity.noContent().build();
    }
        
}
