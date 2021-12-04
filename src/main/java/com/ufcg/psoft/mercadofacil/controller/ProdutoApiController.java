package com.ufcg.psoft.mercadofacil.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ufcg.psoft.mercadofacil.DTO.ProdutoDTO;
import com.ufcg.psoft.mercadofacil.model.Produto;
import com.ufcg.psoft.mercadofacil.service.ProdutoService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ProdutoApiController {

	@Autowired
	ProdutoService produtoService;
	
	@RequestMapping(value = "/produtos", method = RequestMethod.GET)
	public ResponseEntity<?> listarProdutos() {

		List<Produto> produtos = produtoService.listarProdutos();

		return new ResponseEntity<List<Produto>>(produtos, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/produto/", method = RequestMethod.POST)
	public ResponseEntity<?> criarProduto(@RequestBody ProdutoDTO produtoDTO) {

		Produto produto = produtoService.criaProduto(produtoDTO);

		return new ResponseEntity<Produto>(produto, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/produto/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> consultarProduto(@PathVariable("id") long id) {

		Optional<Produto> optionalProduto = produtoService.getProdutoById(id);

		return new ResponseEntity<Produto>(optionalProduto.get(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/produto/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> atualizarProduto(@PathVariable("id") long id, @RequestBody ProdutoDTO produtoDTO) {

		Produto produto = produtoService.atualizaProduto(produtoDTO, id);

		return new ResponseEntity<Produto>(produto, HttpStatus.OK);
	}

	@RequestMapping(value = "/produto/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> removerProduto(@PathVariable("id") long id) {

		produtoService.removerProdutoCadastrado(id);

		return new ResponseEntity<Produto>(HttpStatus.OK);
	}
}