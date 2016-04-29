package dominio;

import java.util.Scanner;

public class Pilha {

	private int topo, item;
	private int[] vet;

	Scanner t = new Scanner(System.in);

	/**
	 * Construtor da classe Pilha que incializa a campo topo com zero, recebe um
	 * valor que representa o tamanho da pilha.
	 * 
	 * @param max
	 *            é um valor que representa o tamanho da pilha.
	 */
	public Pilha(int max) {

		vet = new int[max];
		topo = -1;

	}

	/**
	 * Metodo que verifica a condição de pilha cheia.
	 * 
	 * @return true caso o campo topo da pilha for igual ao tamanho do vetor.
	 */
	public boolean pilhaCheia() {

		return topo == (vet.length - 1);

	}

	/**
	 * Metodo que verifica a condição de pilha vazia.
	 * 
	 * @return true caso o campo topo da pilha for igual a zero.
	 */
	public boolean pilhaVazia() {

		return topo == -1;

	}

	/**
	 * Metodo responsavel por empilhar elementos na pilha.
	 * 
	 * @param item
	 *            é o elemento que será empilhado.
	 */
	public void empilha() {
		if (!pilhaCheia()) {
			for (int i = 0; i < vet.length; i++) {
				System.out.println("digite o " + (i + 1) + " º elemento ");

				vet[i] = t.nextInt();
				topo++;

			}

			System.out.println("Elementos empilhados!!");
			for (int i = 0; i < vet.length; i++) {

				System.out.println(vet[i]);
			}

		} else {

			System.out.println("Impossível inserir elementos - lista - cheia!!");

		}
	}

	/**
	 * Metodo responsavel por desempilhar elementos da pilha.
	 * 
	 * @return o elemento retirado da pilha.
	 */
	public int desempilha() {
		if(!pilhaVazia()){
			item = vet[topo];
			topo--;
		}
		else{
			System.out.println("Impossível desempilhar elementos - lista vazia!!");
			
		}
		return item;
		}
	

	

	/**
	 * Metodo de acesso ao topo da pilha.
	 * 
	 * @return posição do topo da pilha
	 */
	public int getTopo() {

		return topo;

	}

	/**
	 * Metodo que retorna o valor contido no topo da pilha.
	 * 
	 * @return valor do lemento do topo da pilha.
	 */
	public int topoPilha() {

		if (!pilhaVazia()) {

			return vet[topo];

		} else {

			return -2;

		}

	}

}
