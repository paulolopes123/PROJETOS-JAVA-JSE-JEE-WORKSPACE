package controle;

import dominio.Pilha2;

public class Principal {

	public static void main(String[] args) {

		Pilha2 p = new Pilha2();
		System.out.println("elementos empilhados!!");
		p.Push(6);
		p.Push(3);
		p.Push(1);
		p.Push(9);
		p.Push(8);
		System.out.println("elementos desempilhados!!");
		while (p.pilhaVazia() == false) {
			System.out.println(p.Pop());

		}

	}

}
