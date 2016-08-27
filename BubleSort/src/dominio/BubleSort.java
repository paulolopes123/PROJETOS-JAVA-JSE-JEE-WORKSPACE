package dominio;

import java.util.Scanner;

public class BubleSort {

	private int[] lista;
	private int pos;
	Scanner l = new Scanner(System.in);

	public BubleSort(int info) {
		lista = new int[info];
		pos = -1;

	}

	public boolean isVazia() {
		if (pos == -1)
			return true;
		return false;

	}

	public boolean isCheia() {
		if (pos == lista.length - 1)
			return true;
		return false;

	}

	public void Adiciona() {

		if (!isCheia()) {
			for (int i = 0; i <= lista.length; i++) {
				System.out.println("Digite o " + (i + 1) + " Elemento");
				lista[i] = l.nextInt();
				pos++;
			}

		}
	}

	public void Bublle() {
		boolean troca = true;
		int aux;
		while (troca) {
			troca = false;
			for (int i = 0; i <= lista.length; i++) {
				if (lista[i] < lista[i + 1]) {
					aux = lista[i];
					lista[i] = lista[i + 1];
					lista[i + 1] = aux;
					troca = true;

				}

			}

		}

	}

}
