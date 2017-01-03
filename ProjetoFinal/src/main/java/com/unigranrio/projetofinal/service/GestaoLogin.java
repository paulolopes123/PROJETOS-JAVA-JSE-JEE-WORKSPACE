package com.unigranrio.projetofinal.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.unigranrio.projetofinal.model.Usuario;
import com.unigranrio.projetofinal.repository.LoginRepository;
import com.unigranrio.projetofinal.util.Transacional;

public class GestaoLogin implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private LoginRepository login;

	@Transacional
	public void salvar(Usuario usuario) {
		login.guardar(usuario);
	}

}