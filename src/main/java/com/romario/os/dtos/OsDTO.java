package com.romario.os.dtos;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.romario.os.domain.OS;

public class OsDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime dataDeAbertura;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime dataDeFechamento;
	
	
	private Integer prioridade;
	
	@NotEmpty(message = "O campo OBSERVAÇÕES é requerido!")
	private String obsevacoes;
	private Integer status;
	
	
	private Integer tecnico;
	private Integer cliente;

	public OsDTO() {
		// TODO Auto-generated constructor stub
	}

	public OsDTO(OS obj) {
		super();
		this.id = obj.getId();
		this.dataDeAbertura = obj.getDataDeAbertura();
		this.dataDeFechamento = obj.getDataDeFechamento();
		this.prioridade = obj.getPrioridade().getCod();
		this.obsevacoes = obj.getObsevações();
		this.status = obj.getStatus().getCod();
		this.tecnico = obj.getTecnico().getId();
		this.cliente = obj.getCliente().getId();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getDataDeAbertura() {
		return dataDeAbertura;
	}

	public void setDataDeAbertura(LocalDateTime dataDeAbertura) {
		this.dataDeAbertura = dataDeAbertura;
	}

	public LocalDateTime getDataDeFechamento() {
		return dataDeFechamento;
	}

	public void setDataDeFechamento(LocalDateTime dataDeFechamento) {
		this.dataDeFechamento = dataDeFechamento;
	}

	public Integer getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(Integer prioridade) {
		this.prioridade = prioridade;
	}

	public String getObsevacoes() {
		return obsevacoes;
	}

	public void setObsevacoes(String obsevacoes) {
		this.obsevacoes = obsevacoes;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getTecnico() {
		return tecnico;
	}

	public void setTecnico(Integer tecnico) {
		this.tecnico = tecnico;
	}

	public Integer getCliente() {
		return cliente;
	}

	public void setCliente(Integer cliente) {
		this.cliente = cliente;
	}

}
