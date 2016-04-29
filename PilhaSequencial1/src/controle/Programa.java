package controle;

import dominio.Pilha;

public class Programa {

	public static void main(String[] args) {

		Pilha p = new Pilha(5);
		p.empilha();
		System.out.println("elementos desempilhados!!");
		while (p.pilhaVazia() == false) {
			System.out.println(p.desempilha());

		}
	}
}
