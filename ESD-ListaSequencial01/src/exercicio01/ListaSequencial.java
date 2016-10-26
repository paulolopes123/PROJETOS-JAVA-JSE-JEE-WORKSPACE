package exercicio01;

import java.util.Scanner;

public class ListaSequencial {

	private int pos;
	private int[] lista;

	Scanner entrada = new Scanner(System.in);

	public ListaSequencial(int tam) {
		lista = new int[tam];
		pos = -1;

	}

	public boolean vazia() {
		if (pos == -1)
			return true;
		return false;

	}

	public boolean cheia() {

		if (pos == lista.length - 1)
			return true;
		return false;

	}

	public void inserirOrdenado() {
		boolean troca = true;
		if (!cheia()) {
			int aux;
			for (int j = 0; j < lista.length; j++) {
				System.out.println("Digite o " + (j + 1) + "º Elemento");
				lista[++pos] = entrada.nextInt();

			}

			while (troca) {
				troca = false;
				for (int j = 0; j < pos; j++) {
					if (lista[j] < lista[j + 1]) {
						aux = lista[j];
						lista[j] = lista[j + 1];
						lista[j + 1] = aux;
						troca = true;
					}

				}
			}

		} else {
			System.out.println("Lista Cheia!!");
		}
	}

	public void remove(int v) {
		if (!vazia()) {
			for (int i = 0; i <= pos; i++) {
				if (lista[i] == v) {
					lista[v] = lista[pos--];
					System.out.println("Elemento removido com sucesso!!");
				}

			}
		} else {
			System.out.println("Lista Vazia!");
		}

	}

	public int busca(int n) {
		if (vazia()) {
			return -1;

		} else {
			int v;
			for (int i = 0; i <= pos; i++) {
				if (lista[i] == n) {
					v = n;

				}

			}
			return n;

		}

	}

	public void listar() {

		for (int i = 0; i <= pos; i++) {
			System.out.println(i + 1 + "º Elemento " + lista[i]);

		}
	}

}
