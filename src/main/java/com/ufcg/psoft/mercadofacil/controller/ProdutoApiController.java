package com.ufcg.psoft.mercadofacil.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ufcg.psoft.mercadofacil.DTO.ProdutoDTO;
import com.ufcg.psoft.mercadofacil.model.Produto;
import com.ufcg.psoft.mercadofacil.service.ProdutoService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ProdutoApiController {

	@Autowired
	private ProdutoService produtoService;
	
	@GetMapping(value = "/produtos")
	public List<Produto> listarProdutos() {

		return produtoService.listarProdutos();
	}
	
	@PostMapping(value = "/produto/")
	@ResponseStatus(HttpStatus.CREATED)
	public Produto criarProduto(@RequestBody ProdutoDTO produtoDTO) {

		return produtoService.criaProduto(produtoDTO);
	}

	@GetMapping(value = "/produto/{id}")
	public Produto consultarProduto(@PathVariable("id") long id) {

		return produtoService.getProdutoById(id).get();
	}
	
	@PutMapping(value = "/produto/{id}")
	public Produto atualizarProduto(@PathVariable("id") long id, @RequestBody ProdutoDTO produtoDTO) {

		return produtoService.atualizaProduto(produtoDTO, id);
	}

	@DeleteMapping(value = "/produto/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removerProduto(@PathVariable("id") long id) {

		produtoService.removerProdutoCadastrado(id);
	}
}