package com.romario.os.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.romario.os.domain.Cliente;
import com.romario.os.domain.OS;
import com.romario.os.domain.Tecnico;
import com.romario.os.domain.enuns.Prioridade;
import com.romario.os.domain.enuns.Status;
import com.romario.os.dtos.OsDTO;
import com.romario.os.repositories.OSRepository;
import com.romario.os.service.exceptions.ObjectNotFoundException;

@Service
public class OsService {
	
	@Autowired
	private OSRepository osRepository;
	
	@Autowired
	private TecnicoService tecnicoService;
	
	@Autowired
	private ClienteService clienteService;
	
	//get one
	public OS findById(Integer id) {
		Optional<OS> obj = osRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + OS.class.getName()));
	}
	
	//get all
	public List<OS> findAll(){
		return osRepository.findAll();
	}
	
	//create
	public OS create(@Valid OsDTO obj) {
		return fromDTO(obj);
	}
	
	public OS update(@Valid OsDTO obj) {
		findById(obj.getId());
		return fromDTO(obj);
	}
	
	private OS fromDTO(OsDTO obj) {
		OS newObj = new OS();
		newObj.setId(obj.getId());
		newObj.setObsevações(obj.getObsevacoes());
		newObj.setPrioridade(Prioridade.toEnum(obj.getPrioridade()));
		newObj.setStatus(Status.toEnum(obj.getStatus()));
		
		Tecnico tec = tecnicoService.findById(obj.getTecnico());
		Cliente cli = clienteService.findById(obj.getCliente());
		
		newObj.setTecnico(tec);
		newObj.setCliente(cli);
		
		if(newObj.getStatus().getCod().equals(2)) {
			newObj.setDataDeFechamento(LocalDateTime.now());
		}
		
		return osRepository.save(newObj);
		
	}

	
	
}
