package com.unigranrio.projetofinal.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class DAO<T extends IDados> implements IDAO<T>, IDAOSerializavel {
	//
	// CONSTANTES
	//
	/**
	 * Define o tamanho m�ximo de objetos que podem ser armazenados
	 */
	public static final int TAMANHO_MAXIMO = 20;
	
	//
	// ATRIBUTOS
	//
	/**
	 * Refer�ncia para os DAOs existentes. O Map vincula um DAO para cada classe (representada por seu nome)
	 */
	private static Map<Class,DAO> conjDaos = new HashMap<Class,DAO>();
	/**
	 * Refer�ncia para o Set que apontar� para todos os objetos 
	 * guardados pelo DAO
	 */
	private Set<T> listaObjs;
	
	//
	// M�TODOS
	//
	/**
	 * Construtor privado do DAO
	 */
	private DAO(Class classeObjetos) {		
		// Aloco mem�ria para o Set
		this.listaObjs = new TreeSet<T>();		
		conjDaos.put(classeObjetos, this);
	}
	
	/**
	 * M�todo para retornar a �nica inst�ncia existente do DAO
	 * @return
	 */
	public static IDAO getDAO(Class classeObjetos) {
		IDAO dao = conjDaos.get(classeObjetos);
		if(dao == null) 
			dao = new DAO(classeObjetos);
		return dao;
	}
	
	/**
	 * Salva um objeto 
	 * @param novo
	 * @return
	 */
	public synchronized void salvar(T novo) throws DadosException {
		if(!this.listaObjs.add(novo))
			throw new DadosException(new ErroDeDominio(1, "N�o foi poss�vel salvar os dados: " + novo));
	}
	
	/**
	 * Remove um objeto
	 * @param obj
	 * @return
	 */
	public synchronized void remover(T obj) throws DadosException {
		if(!this.listaObjs.remove(obj))
			throw new DadosException(new ErroDeDominio(2, "N�o foi poss�vel remover os dados: " + obj));
	}
	
	/**
	 * Promove a atualiza��o de um objeto
	 * @param obj
	 * @return
	 */
	public synchronized void atualizar(T obj) throws DadosException {
		// Nada a ser feito
	}
	
	/**
	 * Recupera um objeto pela posi��o
	 * @param i
	 * @return
	 */
	public T recuperar(int pos) throws DadosException {
		if(pos < 0)
			throw new DadosException(new ErroDeDominio(3, "Posi��o para recupera��o de objeto inv�lida: " + pos));			
		int i = 0;
		for(T e: this.listaObjs)
			if(i++ == pos)
				return e;
		throw new DadosException(new ErroDeDominio(4, "Posi��o para recupera��o de objeto inv�lida: " + pos));			
	}
	
	/**
	 * Recupera um objeto pela chave que � cpf
	 * @param chave
	 * @return
	 */
	public T recuperarPelaChave(Object chave) throws DadosException{
		for(T e : this.listaObjs)
			if(e.getChave().equals(chave))
				return e;
		throw new DadosException(new ErroDeDominio(5, "Chave para recupera��o de objeto inv�lida: " + chave));			
	}
	
	/**
	 * Retorna o n�mero de objetos sendo gerenciados pelo DAO
	 * @return
	 */
	public synchronized int getNumObjs(){
		return this.listaObjs.size();
	}

	/**
	 * Retorna uma c�pia da lista de objetos
	 * @return
	 */
	public synchronized Collection getListaObjs() {
		return new HashSet(this.listaObjs);
	}
	
	/**
	 * Recupera os objetos 
	 * @return
	 */
	public void recuperarObjetos(ObjectInputStream ois) 
			throws IOException, ClassNotFoundException {
		// Recupera o array de objetos
		this.listaObjs = (Set<T>)ois.readObject();
	}

	/**
	 * Salva os objetos 
	 * @return
	 */
	public void salvarObjetos(ObjectOutputStream oos) 
			throws IOException {
		// Salva o array de objetos
		oos.writeObject(this.listaObjs);
	}
}
