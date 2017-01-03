package com.unigranrio.projetofinal.controller;

/**
 * O conceito de interface em Java tem as seguintes caracter�sticas:
 * 	- Se assemelha a uma classe abstrata
 *  - Que n�o tem atributos
 *  - E todos os seus m�todos s�o abstratos (ou seja n�o tem implementa��o)
 * Uma interface � um CONTRATO DE PRESTA��O DE SERVI�OS que
 * pode ser REALIZADO por uma ou mais classes.
 * 
 * Neste classe as classes tabel�veis ser�o aquelas cujo estado 
 * pode ser apresentado em uma tabela
 * @author Alessandro Cerqueira
 */
public interface ITabelavel {
	/**
	 * Retorna um array de objects com os estados dos atributos 
	 * dos objetos
	 * @return
	 */
	public Object[] getData();
}
