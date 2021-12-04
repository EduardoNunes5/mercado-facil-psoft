package com.ufcg.psoft.mercadofacil.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import com.ufcg.psoft.mercadofacil.DTO.ClienteDTO;
import com.ufcg.psoft.mercadofacil.model.Cliente;
import com.ufcg.psoft.mercadofacil.service.ClienteService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ClienteApiController {

	@Autowired
	private ClienteService clienteService;
	
	@GetMapping(value = "/clientes")
	public List<Cliente> listarClientes() {
		return clienteService.listarClientes();
	}
	
	@PostMapping(value = "/cliente/")
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente criarCliente(@RequestBody ClienteDTO clienteDTO, UriComponentsBuilder ucBuilder) {

		return clienteService.criaCliente(clienteDTO);
	}

	@GetMapping(value = "/cliente/{id}")
	public Cliente consultarCliente(@PathVariable("id") long id) {

		return clienteService.getClienteById(id).get();
	}
	
	@PutMapping(value = "/cliente/{id}")
	public Cliente atualizarCliente(@PathVariable("id") long id, @RequestBody ClienteDTO clienteDTO) {

		return clienteService.atualizaCliente(clienteDTO, id);
	}

	@DeleteMapping(value = "/cliente/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removerCliente(@PathVariable("id") long id) {
		clienteService.removerClienteCadastrado(id);
	}
}