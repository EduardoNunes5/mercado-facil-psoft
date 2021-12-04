package com.ufcg.psoft.mercadofacil.util;

import javax.persistence.EntityNotFoundException;

public class ClienteNaoEncontrado extends EntityNotFoundException {

    public ClienteNaoEncontrado(Long id) {
        super(String.format("Cliente com id %s não está cadastrado", id));
    }

    public ClienteNaoEncontrado(String message) {
        super(message);
    }
}
