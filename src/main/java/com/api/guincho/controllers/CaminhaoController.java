package com.api.guincho.controllers;

import java.util.List;

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

import com.api.guincho.models.Caminhao;
import com.api.guincho.services.CaminhaoService;

@RestController
@RequestMapping("/api/caminhao")
public class CaminhaoController {

	@Autowired
	private CaminhaoService caminhaoService;

	@GetMapping
	public ResponseEntity<List<Caminhao>> listarTodosCaminhoes() {
		List<Caminhao> caminhoes = caminhaoService.listarCaminhoes();
		return ResponseEntity.ok(caminhoes);
	} 
	
	@PostMapping("/cadastrar")
	public ResponseEntity<Caminhao> cadastrarCaminhao(@RequestBody Caminhao caminhao) {
		Caminhao novoCaminhao = caminhaoService.cadastrarCaminhao(caminhao);
		return ResponseEntity.status(HttpStatus.CREATED).body(novoCaminhao);
	}
	
	@PutMapping("/editar/{id}")
	public ResponseEntity<Caminhao> editarCaminhao(
			@PathVariable Long id,
			@RequestBody Caminhao caminhaoAtualizado) {
		
		Caminhao caminhaoEditado = caminhaoService.editarCaminhao(id, caminhaoAtualizado);
		return ResponseEntity.ok(caminhaoEditado);
	}
	
	@DeleteMapping("/remover/{id}")
	public ResponseEntity<Void> removerCaminhao(@PathVariable Long id) {
		caminhaoService.removerCaminhao(id);
		return ResponseEntity.noContent().build();
	}
	
}