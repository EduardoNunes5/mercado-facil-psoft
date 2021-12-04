package com.ufcg.psoft.mercadofacil.util;

import javax.persistence.EntityExistsException;

public class LoteJaCadastrado extends EntityExistsException {
    public LoteJaCadastrado(Long id, String nome){
        super(String.format("Lote já cadastrado para produto: %d de nome: %s", id, nome));

    }
}
