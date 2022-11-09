package com.romario.os.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.romario.os.domain.Cliente;
import com.romario.os.domain.OS;
import com.romario.os.domain.Tecnico;
import com.romario.os.domain.enuns.Prioridade;
import com.romario.os.domain.enuns.Status;
import com.romario.os.repositories.ClienteRepository;
import com.romario.os.repositories.OSRepository;
import com.romario.os.repositories.TecnicoRepository;

@Service
public class BDService {

	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private OSRepository osRepository;

	
	public void runBanco() {
		Tecnico t1 = new Tecnico(null, "Romario Alves", "808.177.810-13", "(85)997296407");
		Tecnico t2 = new Tecnico(null, "Maria jose", "584.643.530-06", "(85)997296407");
		Cliente c1 = new Cliente(null, "Valdir Cesar", "356.696.330-59", "(85)997296407");
		OS os1 = new OS(null, Prioridade.ALTA, "Teste create OD", Status.ANDAMENTO, t1, c1);

		t1.getList().add(os1);
		
		c1.getList().add(os1);

		tecnicoRepository.saveAll(Arrays.asList(t1, t2));
		clienteRepository.saveAll(Arrays.asList(c1));
		osRepository.saveAll(Arrays.asList(os1));
	}
}
