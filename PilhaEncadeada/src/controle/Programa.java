package controle;

import dominio.PilhaDinamica;

public class Programa {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		PilhaDinamica pilha = new PilhaDinamica();
		pilha.Empilha(6);
		pilha.Empilha(10);
		pilha.Empilha(9);
		pilha.Empilha(1);
		pilha.Empilha(4);

		pilha.imprimePilha();
		//System.out.println(pilha.tamanhoPilha());
		//System.out.println(pilha.getTopo());
		pilha.Desempilha();
	}

}
