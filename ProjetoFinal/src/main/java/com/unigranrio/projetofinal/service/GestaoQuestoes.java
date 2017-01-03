package com.unigranrio.projetofinal.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.unigranrio.projetofinal.model.Questao;
import com.unigranrio.projetofinal.repository.QuestoesRepository;
import com.unigranrio.projetofinal.util.Transacional;

public class GestaoQuestoes implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private QuestoesRepository questoes;
	
	@Transacional
	public void salvar(Questao questao) {
		questoes.guardar(questao);
	}
	
}