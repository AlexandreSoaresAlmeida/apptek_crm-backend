package com.apptek.crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.apptek.crm.model.Cliente;
import com.apptek.crm.repository.ClienteRepository;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteRepository clienteRepository;
		
	@GetMapping
	public List<Cliente> listar()  {
		return clienteRepository.findAll();
	}

	@GetMapping("/{id}")
	public Cliente getById(@PathVariable Long id)  {
		return clienteRepository.findById(id).get();
	}
	
	@DeleteMapping("/{id}")
	public void remover(@PathVariable Long id) {
		clienteRepository.deleteById(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente adicionar(@RequestBody Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	@PutMapping
	public Cliente atualizar(@RequestBody Cliente cliente) {
		Cliente newObj = clienteRepository.getById(cliente.getId());
		updateData(newObj, cliente);
		return clienteRepository.save(newObj);
	}
	
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
	}
}
