package com.api.guincho.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status_caminhao", nullable = false, length = 50)
	private StatusCaminhao status;
	
	public Caminhao() {
	}

	public Caminhao(Long id, String placa, String fabricante, String cor, StatusCaminhao status) {
		this.id = id;
		this.placa = placa;
		this.fabricante = fabricante;
		this.cor = cor;
		this.status = status;
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

	public StatusCaminhao getStatus() {
		return status;
	}

	public void setStatus(StatusCaminhao status) {
		this.status = status;
	}
	
}
