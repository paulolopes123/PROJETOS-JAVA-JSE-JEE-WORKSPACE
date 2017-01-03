package com.unigranrio.projetofinal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Usuario {
	@Id
	@GeneratedValue
	private Long id;
	@NotEmpty
	@Column(unique = true)
	private String senha;
	@NotEmpty
	@Column(unique = true)
	private String login;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	// valida��o dos atributos
	public static void validarSenha(String s) throws DadosException {
		if (s == null || s.length() == 0)
			throw new DadosException(new ErroDeDominio(1, "A senha n�o pode ser nula ou vazia"));

	}

	public static void validarLogin(String l) throws DadosException {
		if (l == null || l.length() == 0)
			throw new DadosException(new ErroDeDominio(2, "A conta n�o pode ser nula ou vazia"));
	}

}
