package com.ufcg.psoft.mercadofacil.service;

import java.util.List;
import java.util.Optional;

import com.ufcg.psoft.mercadofacil.util.EmptyResponseException;
import com.ufcg.psoft.mercadofacil.util.ProdutoJaExisteCadastrado;
import com.ufcg.psoft.mercadofacil.util.ProdutoNaoEncontrado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.psoft.mercadofacil.DTO.ProdutoDTO;
import com.ufcg.psoft.mercadofacil.model.Produto;
import com.ufcg.psoft.mercadofacil.repository.ProdutoRepository;

@Service
public class ProdutoServiceImpl implements ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	public Optional<Produto> getProdutoById(long id) {
		return Optional.of(verificaSeExistePeloId(id));
	}
	
	public Optional<Produto> getProdutoByCodigoBarra(String codigo) {
		return produtoRepository.findByCodigoBarra(codigo);
	}
	
	public void removerProdutoCadastrado(Long id) {
		Produto produto = verificaSeExistePeloId(id);
		produtoRepository.delete(produto);
	}

	public void salvarProdutoCadastrado(Produto produto) {

		produtoRepository.save(produto);		
	}

	public List<Produto> listarProdutos() {
		List<Produto> produtos = produtoRepository.findAll();

		if(produtos.isEmpty())
			throw new EmptyResponseException("Não há produtos cadastrados");
		return produtos;
	}

	public Produto criaProduto(ProdutoDTO produtoDTO) {
		verificaSeJaExisteCodigoBarra(produtoDTO);

		Produto produto = new Produto(produtoDTO.getNome(), produtoDTO.getCodigoBarra(), produtoDTO.getFabricante(),
				produtoDTO.getPreco(), produtoDTO.getCategoria());
		
		produto.tornaDisponivel();
		return produtoRepository.save(produto);
	}

	public Produto atualizaProduto(ProdutoDTO produtoDTO, Long idProduto) {
		Produto produto = verificaSeExistePeloId(idProduto);

		produto.setNome(produtoDTO.getNome());
		produto.setPreco(produtoDTO.getPreco());
		produto.setCodigoBarra(produtoDTO.getCodigoBarra());
		produto.mudaFabricante(produtoDTO.getFabricante());
		produto.mudaCategoria(produtoDTO.getCategoria());
		
		return produtoRepository.save(produto);
	}

	public Produto verificaSeExistePeloId(Long id){
		return this.produtoRepository.findById(id)
				.orElseThrow(() -> new ProdutoNaoEncontrado(id));
	}

	private void verificaSeJaExisteCodigoBarra(ProdutoDTO produtoDTO){
		System.out.println(produtoDTO.getCodigoBarra());
		this.produtoRepository.findByCodigoBarra(produtoDTO.getCodigoBarra())
				.ifPresent((prod) -> {
					System.out.println(prod.getNome());
					throw new ProdutoJaExisteCadastrado(produtoDTO.getNome(), produtoDTO.getFabricante());
				});

	}
}
