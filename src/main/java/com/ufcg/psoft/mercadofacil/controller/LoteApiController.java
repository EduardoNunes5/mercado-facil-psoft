package com.ufcg.psoft.mercadofacil.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ufcg.psoft.mercadofacil.model.Lote;
import com.ufcg.psoft.mercadofacil.service.LoteService;
import com.ufcg.psoft.mercadofacil.service.ProdutoService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class LoteApiController {

	@Autowired
	private LoteService loteService;

	@GetMapping(value = "/lotes")
	public List<Lote> listarLotes() {
		
		List<Lote> lotes = loteService.listarLotes();

		return lotes;
	}
	
	@PostMapping(value = "/produto/{idProduto}/lote/")
	@ResponseStatus(HttpStatus.CREATED)
	public Lote criarLote(@PathVariable("idProduto") long id, @RequestBody int numItens) {

		return loteService.criaLote(numItens, id);
	}
}