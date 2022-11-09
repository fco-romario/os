package com.romario.os.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.romario.os.domain.Cliente;
import com.romario.os.domain.Pessoa;
import com.romario.os.dtos.ClienteDTO;
import com.romario.os.repositories.ClienteRepository;
import com.romario.os.repositories.PessoaRepository;
import com.romario.os.service.exceptions.DataIntegratyViolationException;
import com.romario.os.service.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;

	@Autowired
	private PessoaRepository pessoaRepository;

	// get one
	public Cliente findById(Integer id) {
		Optional<Cliente> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado. Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}

	// get All
	public List<Cliente> findAll() {
		return repository.findAll();
	}

	// create
	public Cliente create(ClienteDTO objDTO) {
		if (findByCpf(objDTO) != null) {
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
		}
		return repository.save(new Cliente(null, objDTO.getNome(), objDTO.getCpf(), objDTO.getTelefone()));
	}

	// update
	public Cliente update(Integer id, @Valid ClienteDTO objDTO) {
		Cliente oldObj = findById(id);

		if (findByCpf(objDTO) != null && findByCpf(objDTO).getId() != id) {
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados");
		}

		oldObj.setNome(objDTO.getNome());
		oldObj.setCpf(objDTO.getCpf());
		oldObj.setTelefone(objDTO.getTelefone());

		return repository.save(oldObj);
	}

	// Delete
	public void delete(Integer id) {
		Cliente obj = findById(id);
		if (obj.getList().size() > 0) {
			throw new DataIntegratyViolationException("Cliente possui Ordem de Serviço, não pode ser deletado!");
		}
		repository.deleteById(id);
	}

	// Verifica CPF
	public Pessoa findByCpf(ClienteDTO objDTO) {
		Pessoa obj = repository.findByCpf(objDTO.getCpf());

		if (obj != null) {
			return obj;
		}
		return null;
	}

}
