package com.api.guincho.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Motorista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome_motorista", nullable = false, length = 50)
    private String nomeMotorista;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status_motorista", nullable = false, length = 50)
    private StatusMotorista status;
    
    @JoinColumn(name = "id_cidade", nullable = false)
    @ManyToOne
    private Cidade cidade;
    
    @JoinColumn(name = "id_caminhao", nullable = false)
    @OneToOne
    private Caminhao caminhao;
  
    public Motorista() {
    }

	public Motorista(Long id, String nomeMotorista, StatusMotorista status, Cidade cidade, Caminhao caminhao) {
		this.id = id;
		this.nomeMotorista = nomeMotorista;
		this.status = status;
		this.cidade = cidade;
		this.caminhao = caminhao;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeMotorista() {
		return nomeMotorista;
	}

	public void setNomeMotorista(String nomeMotorista) {
		this.nomeMotorista = nomeMotorista;
	}

	public StatusMotorista getStatus() {
		return status;
	}

	public void setStatus(StatusMotorista status) {
		this.status = status;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public Caminhao getCaminhao() {
		return caminhao;
	}

	public void setCaminhao(Caminhao caminhao) {
		this.caminhao = caminhao;
	}

}
