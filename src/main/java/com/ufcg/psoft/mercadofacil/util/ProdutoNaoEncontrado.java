package com.ufcg.psoft.mercadofacil.util;

import javax.persistence.EntityNotFoundException;

public class ProdutoNaoEncontrado extends EntityNotFoundException {

    public ProdutoNaoEncontrado(Long id){
        super(String.format("Produto com id %d não está cadastrado", id));
    }
}
