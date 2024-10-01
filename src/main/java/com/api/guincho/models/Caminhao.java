package com.api.guincho.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Caminhao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "placa", nullable = false, length = 10)
	private String placa;
	@Column(name = "fabricante", nullable = false, length = 40)
	private String fabricante;
	@Column(name = "cor", nullable = false, length = 25)
	private String cor;
	
	public Caminhao() {
	}

	public Caminhao(Long id, String placa, String fabricante, String cor) {
		this.id = id;
		this.placa = placa;
		this.fabricante = fabricante;
		this.cor = cor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}
	
}
