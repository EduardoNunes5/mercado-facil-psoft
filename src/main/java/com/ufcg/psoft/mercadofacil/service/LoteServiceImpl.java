package com.ufcg.psoft.mercadofacil.service;

import java.util.List;

import com.ufcg.psoft.mercadofacil.util.EmptyResponseException;
import com.ufcg.psoft.mercadofacil.util.LoteJaCadastrado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.psoft.mercadofacil.model.Lote;
import com.ufcg.psoft.mercadofacil.model.Produto;
import com.ufcg.psoft.mercadofacil.repository.LoteRepository;

@Service
public class LoteServiceImpl implements LoteService {
	
	@Autowired
	private LoteRepository loteRepository;

	@Autowired
	private ProdutoServiceImpl produtoService;
	
	public List<Lote> listarLotes() {
		List<Lote> lotes =  loteRepository.findAll();

		if(lotes.isEmpty())
			throw new EmptyResponseException("Não há lotes cadastrados");
		return lotes;
	}

	public void salvarLote(Lote lote) {
		loteRepository.save(lote);		
	}

	public Lote criaLote(int numItens, Long idProduto) {
		Produto prod = produtoService.verificaSeExistePeloId(idProduto);
		verificaSeJaExisteLote(prod);

		Lote lote = new Lote(prod, numItens);
		if (numItens > 0 && !prod.isDisponivel())
			prod.tornaDisponivel();

		return loteRepository.save(lote);
	}

	private void verificaSeJaExisteLote(Produto produto){
		loteRepository.findByProduto(produto)
				.ifPresent((lote) -> {
					throw new LoteJaCadastrado(produto.getId(), produto.getNome());
				});
	}
}
