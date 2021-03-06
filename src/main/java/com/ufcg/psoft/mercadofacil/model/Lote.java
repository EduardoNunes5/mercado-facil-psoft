package com.ufcg.psoft.mercadofacil.model;

import javax.persistence.*;

@Entity
public class Lote {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@OneToOne
    private Produto produto;

    private int numeroDeItens;

    public Lote() { }
    
    public Lote(Produto produto, int numeroDeItens) {
        this.produto = produto;
        this.numeroDeItens = numeroDeItens;
    }

    public Long getId() {
        return id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getNumeroDeItens() {
        return numeroDeItens;
    }

    public void setNumeroDeItens(int numeroDeItens) {
        this.numeroDeItens = numeroDeItens;
    }

    @Override
    public String toString() {
        return "Lote{" +
                "id=" + id +
                ", produto=" + produto.getId() +
                ", numeroDeItens=" + numeroDeItens + '\'' +
                '}';
    }
}
