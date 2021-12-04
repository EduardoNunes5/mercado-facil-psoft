package com.ufcg.psoft.mercadofacil.util;

import javax.persistence.EntityExistsException;

public class ClienteJaCadastrado extends EntityExistsException {

    public ClienteJaCadastrado(Long cpf, String nome){
        super(String.format("O cliente %s nome %s já esta cadastrado", cpf, nome));
    }

    public ClienteJaCadastrado(String message) {
        super(message);
    }
}
