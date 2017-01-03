package com.unigranrio.projetofinal.controller;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.unigranrio.projetofinal.model.Usuario;
import com.unigranrio.projetofinal.service.GestaoLogin;

@Named("bean")
@ViewScoped
public class LoginMB implements Serializable {
	private static final long serialVersionUID = 1L;
	@Inject
	private GestaoLogin gestaoLogin;

	private Usuario usuario = new Usuario();

	public String acaoAutenticar() throws Exception {
		gestaoLogin.salvar(usuario);
		usuario = new Usuario();
		return "login";

	}

	public String acaoLogout() {

		return "login";
	}

	public Usuario getUsuario() {
		return usuario;
	}

}