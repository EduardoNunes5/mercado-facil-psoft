package com.ufcg.psoft.mercadofacil.util;

import javax.persistence.EntityExistsException;

public class ProdutoJaExisteCadastrado extends EntityExistsException {
    public ProdutoJaExisteCadastrado(String nome, String fabricante) {
        super(String.format("O produto %s do fabricante %s já esta cadastrado", nome, fabricante));
    }
}
