package com.api.guincho.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Servico {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nome_cliente", nullable = false, length = 50)
	private String nome;
	@Column(name = "telefone", nullable = false, length = 14)
	private String telefone;
	@Column(name = "endereco", nullable = false, length = 200)
	private String endereco;
	@Column(name = "veiculo", nullable = false, length = 200)
	private String veiculo;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status_servico", nullable = false, length = 50)
	private StatusServico status;
	@Column(name = "data_solicitacao", nullable = false)
	private LocalDateTime dataSolicitacao;
	@Column(name = "data_inicio", nullable = true)
	private LocalDateTime dataInicio;
	@Column(name = "data_conclusao", nullable = true)
	private LocalDateTime DataConclusao;
	
	@ManyToOne
	@JoinColumn(name = "id_motorista", nullable = true)
	private Motorista motorista;
	
	public Servico() {
		
	}

	public Servico(Long id, String nome, String telefone, String endereco, String veiculo, StatusServico status, LocalDateTime dataSolicitacao, LocalDateTime dataInicio,
			Motorista motorista, LocalDateTime dataConclusao) {
		this.id = id;
		this.nome = nome;
		this.telefone = telefone;
		this.endereco = endereco;
		this.veiculo = veiculo;
		this.status = status;
		this.dataSolicitacao = dataSolicitacao;
		this.dataInicio = dataInicio;
		this.motorista = motorista;
		this.DataConclusao = dataConclusao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(String veiculo) {
		this.veiculo = veiculo;
	}

	public StatusServico getStatus() {
		return status;
	}

	public void setStatus(StatusServico status) {
		this.status = status;
	}

	public LocalDateTime getDataSolicitacao() {
		return dataSolicitacao;
	}

	public void setDataSolicitacao(LocalDateTime dataSolicitacao) {
		this.dataSolicitacao = dataSolicitacao;
	}

	public Motorista getMotorista() {
		return motorista;
	}

	public void setMotorista(Motorista motorista) {
		this.motorista = motorista;
	}

	public LocalDateTime getDataConclusao() {
		return DataConclusao;
	}

	public void setDataConclusao(LocalDateTime dataConclusao) {
		DataConclusao = dataConclusao;
	}

	public LocalDateTime getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDateTime dataInicio) {
		this.dataInicio = dataInicio;
	}
	
	

}
