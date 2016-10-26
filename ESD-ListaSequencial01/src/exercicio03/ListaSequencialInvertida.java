package exercicio03;

public class ListaSequencialInvertida {

	private int[] lista;
	private int pos;

	public ListaSequencialInvertida(int tam) {

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

	public void inserir(int valor) {
		if (!cheia()) {

			lista[++pos] = valor;

		} else {

			System.out.println("Lista Cheia!!");
		}

	}

	public void imprimeInvertida() {
		System.out.println("Elementos impressos na ordem inversa a da inserção!!");
		for (int i = pos; i >= 0; i--) {

			System.out.println(lista[i]);

		}

	}

}
