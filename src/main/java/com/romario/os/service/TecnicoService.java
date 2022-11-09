package com.romario.os.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.romario.os.domain.Pessoa;
import com.romario.os.domain.Tecnico;
import com.romario.os.dtos.TecnicoDTO;
import com.romario.os.repositories.PessoaRepository;
import com.romario.os.repositories.TecnicoRepository;
import com.romario.os.service.exceptions.DataIntegratyViolationException;
import com.romario.os.service.exceptions.ObjectNotFoundException;

@Service
public class TecnicoService {
	
	@Autowired
	private TecnicoRepository repository;

	@Autowired
	private PessoaRepository pessoaRepository;
	
	//get one
	public Tecnico findById(Integer id){
		Optional<Tecnico> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado. Id: "+ id +", Tipo: "+ Tecnico.class.getName()));
	}
	//get All
	public List<Tecnico> findAll() {
		return repository.findAll();
	}
	//create
	public Tecnico create(TecnicoDTO objDTO) {
		if(findByCpf(objDTO) != null) {
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
		}
		return repository.save(new Tecnico(null, objDTO.getNome(), objDTO.getCpf(), objDTO.getTelefone()));
	}
	
	//update
		public Tecnico update(Integer id, @Valid TecnicoDTO objDTO) {
			Tecnico oldObj = findById(id);
			
			if(findByCpf(objDTO) != null && findByCpf(objDTO).getId() != id) {
				throw new DataIntegratyViolationException("CPF já cadastrado na base de dados");
			}
			
			oldObj.setNome(objDTO.getNome());
			oldObj.setCpf(objDTO.getCpf());
			oldObj.setTelefone(objDTO.getTelefone());
			
			return repository.save(oldObj);
		}
	//Delete 
		public void delete(Integer id) {
			Tecnico obj = findById(id);
			if(obj.getList().size() > 0) {
				throw new DataIntegratyViolationException("Técnico possui Ordem de Serviço, não pode ser deletado!");
			}
			repository.deleteById(id);
		}
		
	//Verifica CPF
	public Pessoa findByCpf(TecnicoDTO objDTO) {
		Pessoa obj = repository.findByCpf(objDTO.getCpf());
		
		if(obj != null) {
			return obj;
		}
		return null;
	}
	
	
}
