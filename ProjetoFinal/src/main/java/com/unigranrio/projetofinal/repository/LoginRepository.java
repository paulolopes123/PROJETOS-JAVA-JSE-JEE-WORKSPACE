package com.unigranrio.projetofinal.repository;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.unigranrio.projetofinal.model.Usuario;

public class LoginRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Usuario guardar(Usuario usuario) {
		return manager.merge(usuario);
	}

}