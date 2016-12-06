/** Produtos - com.wordpress.programandojava.model */
package com.wordpress.programandojava.model;

import java.io.Serializable;

import javax.persistence.*;

/**
 * @author - Felipe
 * @since - 08/08/2012
 */
@Entity
@Table(name = "PRODUTO")
public class Produto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PROD_ID")
	private long id;
	@Column(name = "PROD_NOME")
	private String nome;
	@Column(name = "PROD_PRECO")
	private double preco;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}
}