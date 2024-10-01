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

import com.api.guincho.models.Cidade;
import com.api.guincho.services.CidadeService;

@RestController
@RequestMapping("/api/cidade")
public class CidadeController {

	@Autowired
	private CidadeService cidadeService;
	
	@GetMapping
	public ResponseEntity<List<Cidade>> listarTodasCidades() {
		List<Cidade> cidades = cidadeService.listarCidades();
		return ResponseEntity.ok(cidades);
	}
	
	@PostMapping("/cadastrar")
	public ResponseEntity<Cidade> cadastrarCidade(@RequestBody Cidade cidade) {
		Cidade novaCidade = cidadeService.cadastrarCidade(cidade);
		return ResponseEntity.status(HttpStatus.CREATED).body(novaCidade);
	}
	
	@PutMapping("/editar/{id}")
    public ResponseEntity<Cidade> editarCidade(
            @PathVariable Long id,
            @RequestBody Cidade cidadeAtualizada) {

        Cidade cidadeEditada = cidadeService.editarCidade(id, cidadeAtualizada);
        return ResponseEntity.ok(cidadeEditada);
    }
	
	@DeleteMapping("/remover/{id}")
	public ResponseEntity<Void> removerCidade(@PathVariable Long id) {
		cidadeService.removerCidade(id);
		return ResponseEntity.noContent().build();
	}
	
}
