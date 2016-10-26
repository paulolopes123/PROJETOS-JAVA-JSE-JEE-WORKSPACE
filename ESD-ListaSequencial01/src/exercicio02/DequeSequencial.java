package exercicio02;

public class DequeSequencial {

	private int inicio;
	private int fim;

	private int[] lista;

	public DequeSequencial(int t) {
		inicio = -1;
		fim = -1;
		lista = new int[t];

	}

	public boolean cheia() {
		if (inicio == lista.length - 1 && fim == lista.length - 1)
			return true;
		return false;

	}

	public boolean vazia() {
		if (inicio == -1 && fim == -1)
			return true;
		return false;

	}

	public void insereInicio(int valor) {
		if (!cheia()) {

			lista[++inicio] = valor;

		} else {
			System.out.println("Lista Cheia!!");
		}

	}

	public void insereFim(int valor) {
		if (!cheia()) {

			lista[++fim] = valor;

		} else {
			System.out.println("Lista Cheia!!");
		}

	}

	public void imprimeDoInicio() {
		System.out.println("Imprimindo do inicio:");
		for (int i = 0; i <= inicio; i++) {
			System.out.println(lista[i]);

		}

	}

	public void imprimeDoFim() {
		System.out.println("Imprimindo do fim:");
		for (int i = 0; i <= fim ; i++) {
			System.out.println(lista[i]);

		}

	}

}
