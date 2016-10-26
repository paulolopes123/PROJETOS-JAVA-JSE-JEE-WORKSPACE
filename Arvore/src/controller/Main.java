package controller;

import model.ArvoreBinaria;

public class Main {
	public static void main(String[] args) {
		ArvoreBinaria arvore = new ArvoreBinaria();
		arvore.inserir(7);
		arvore.inserir(3);
		arvore.inserir(5);
		
		arvore.inserir(2);
		arvore.inserir(1);
		arvore.inserir(4);
		arvore.inserir(8);
		arvore.listarArv();
		System.out.println("Listar pos");
		arvore.listar(1);
		System.out.println("Listar pre");
		arvore.listar(2);
		System.out.println("Listar simétrica");
		arvore.listar(3);
		System.out.println();
		System.out.println("Altura: "+arvore.getAltura());
	}
}
