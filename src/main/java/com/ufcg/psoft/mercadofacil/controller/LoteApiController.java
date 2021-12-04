package com.ufcg.psoft.mercadofacil.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ufcg.psoft.mercadofacil.model.Lote;
import com.ufcg.psoft.mercadofacil.service.LoteService;
import com.ufcg.psoft.mercadofacil.service.ProdutoService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class LoteApiController {

	@Autowired
	LoteService loteService;
	
	@Autowired
	ProdutoService produtoService;
	
	@RequestMapping(value = "/lotes", method = RequestMethod.GET)
	public ResponseEntity<?> listarLotes() {
		
		List<Lote> lotes = loteService.listarLotes();

		return new ResponseEntity<List<Lote>>(lotes, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/produto/{idProduto}/lote/", method = RequestMethod.POST)
	public ResponseEntity<?> criarLote(@PathVariable("idProduto") long id, @RequestBody int numItens) {
		Lote lote = loteService.criaLote(numItens, id);

		return new ResponseEntity<>(lote, HttpStatus.CREATED);
	}
}