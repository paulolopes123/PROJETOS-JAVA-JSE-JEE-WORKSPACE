package com.unigranrio.projetofinal.model;

import java.util.Collection;

public interface IDAO<T> {

	//
	// CONSTANTES
	//
	/**
	 * Define o tamanho m�ximo de objetos que podem ser armazenados
	 */
	public static final int TAMANHO_MAXIMO = 20;

	/**
	 * Salva um objeto
	 * 
	 * @param novo
	 * @return
	 */
	public abstract void salvar(T novo) throws DadosException;

	/**
	 * Remove um objeto
	 * 
	 * @param obj
	 * @return
	 */
	public abstract void remover(T obj) throws DadosException;

	/**
	 * Promove a atualiza��o de um objeto
	 * 
	 * @param obj
	 * @return
	 */
	public abstract void atualizar(T obj) throws DadosException;

	/**
	 * Recupera um objeto pela posi��o
	 * 
	 * @param posicao
	 * @return
	 */
	public abstract T recuperar(int posicao) throws DadosException;

	/**
	 * Recupera um objeto pela chave
	 * 
	 * @param sigla
	 * @return
	 */
	public abstract T recuperarPelaChave(Object chave) throws DadosException;

	/**
	 * Retorna o n�mero de objetos sendo gerenciados pelo DAO
	 * 
	 * @return
	 */
	public abstract int getNumObjs();

	/**
	 * Retorna uma c�pia da lista de objetos
	 * 
	 * @return
	 */
	public abstract Collection getListaObjs();
}