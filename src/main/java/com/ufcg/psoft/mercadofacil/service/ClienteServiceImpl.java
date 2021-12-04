package com.ufcg.psoft.mercadofacil.service;

import java.util.List;
import java.util.Optional;

import com.ufcg.psoft.mercadofacil.util.ClienteJaCadastrado;
import com.ufcg.psoft.mercadofacil.util.ClienteNaoEncontrado;
import com.ufcg.psoft.mercadofacil.util.EmptyResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.psoft.mercadofacil.DTO.ClienteDTO;
import com.ufcg.psoft.mercadofacil.model.Cliente;
import com.ufcg.psoft.mercadofacil.repository.ClienteRepository;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	public Optional<Cliente> getClienteById(Long id) {
		return Optional.of(verificaSeExistePeloId(id));
	}
	
	public Optional<Cliente> getClienteByCPF(Long cpf) {
		return clienteRepository.findByCPF(cpf);
	}
	
	public void removerClienteCadastrado(Long id) {
		Cliente c = verificaSeExistePeloId(id);
		clienteRepository.delete(c);
	}

	public void salvarClienteCadastrado(Cliente cliente) {
		clienteRepository.save(cliente);		
	}

	public List<Cliente> listarClientes() {
		List<Cliente> clientes = clienteRepository.findAll();

		if(clientes.isEmpty())
			throw new EmptyResponseException("Não há clientes cadastrados");
		return clientes;
	}

	public Cliente criaCliente(ClienteDTO clienteDTO) {
		verificaSeJaExistePeloCpf(clienteDTO);

		Cliente novoCliente = new Cliente(clienteDTO.getCPF(), clienteDTO.getNome(),
				clienteDTO.getIdade(), clienteDTO.getEndereco());
		return this.clienteRepository.save(novoCliente);
	}

	public Cliente atualizaCliente(ClienteDTO clienteDTO, Long id) {
		Cliente cliente = verificaSeExistePeloId(id);
		cliente.setIdade(clienteDTO.getIdade());
		cliente.setEndereco(clienteDTO.getEndereco());
		
		return this.clienteRepository.save(cliente);
	}

	private void verificaSeJaExistePeloCpf(ClienteDTO clienteDTO){
		this.getClienteByCPF(clienteDTO.getCPF())
				.ifPresent((cli) -> {
					throw new ClienteJaCadastrado(cli.getCpf(), cli.getNome());
				});
	}

	public Cliente verificaSeExistePeloId(Long id){
		return clienteRepository.findById(id)
				.orElseThrow(() -> new ClienteNaoEncontrado(id));
	}
}
