package model;

import java.util.ArrayList;
import java.util.List;

public class Lista {
	private Pessoa ptLista, aux;

	public Lista() {
		ptLista = null;
		aux = ptLista;
	}

	public boolean inserir(String nome, int idade, String sexo) {
		Pessoa novo = new Pessoa(nome, idade, sexo);

		if (isVazio()) {
			novo.setAnt(null);
			novo.setProx(null);
			ptLista = novo;
			aux = ptLista;
			// Se existir um ou mais nós na lista
			// o novo nó será inserido como sucessor de ptL
		} else {
			// "pendura" o novo nó na lista
			novo.setAnt(aux);
			novo.setProx(aux.getProx());
			// Ajusta as referencias do(s) nó(s)
			// existente(s) na lista
			aux.setProx(novo);
			novo.getProx().setAnt(novo);
		}

		return true;
	}

	public String buscar(String nome) {
		Pessoa aux = ptLista;
		if (!isVazio()) {

			while (aux.getNome() != nome) {
				aux = aux.getProx();
			}
			return aux.getNome();
		}
		return null;
	}

	public boolean remover(Pessoa pessoa) {
		aux = ptLista;
		if (!isVazio()) {
			if (aux.getProx() == null) {
				ptLista = null;
			} else {
				while (aux != pessoa) {
					aux = aux.getProx();
				}
				aux.getProx().setAnt(aux.getAnt());
				aux.getAnt().setProx(aux.getProx());
			}
			return true;
		} else
			return false;
	}

	public void listar() {

		aux = ptLista;
		if (aux != null) {
			while (aux != null) {
				System.out.println(aux.getNome());
				System.out.println(aux.getIdade());
				System.out.println(aux.getSexo());
				aux = aux.getProx();
			}
		} else {

			System.out.println("Lista Vazia");
		}

	}

	private boolean isVazio() {
		if (ptLista == null)
			return true;
		else
			return false;
	}
}
