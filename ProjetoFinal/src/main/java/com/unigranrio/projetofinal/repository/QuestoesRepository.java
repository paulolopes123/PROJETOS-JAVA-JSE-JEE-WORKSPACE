package com.unigranrio.projetofinal.repository;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.unigranrio.projetofinal.model.Questao;


public class QuestoesRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;
	
	public Questao guardar(Questao questao) {
		return manager.merge(questao);
	}

}