package dominio;

public class SelectionSort {

	private int[] lista;
	private int pos;

	public SelectionSort(int tam) {

		lista = new int[tam];
		pos = -1;
	}

	public boolean tahVazia() {

		if (pos == -1)
			return true;
		return false;
	}

	public boolean tahCheia() {

		if (pos == lista.length - 1)
			return true;
		return false;
	}

	public boolean insereElemento(int valor) {
		if (tahCheia()) {

			return false;

		} else {
			lista[++pos] = valor;
			return true;

		}
	}

	public int buscarElemento(int v) {
		int n = 0;
		for (int i = 0; i < pos; i++) {
			if (lista[i] == v) {

				n = v;
			}

		}
		return n;

	}

	public void ordenarSelectionSort() {
		long tempoInicial = System.currentTimeMillis();
		for (int fixo = 0; fixo < pos; fixo++) {

			int menor = fixo;

			for (int i = menor + 1; i < pos; i++) {
				if (lista[i] < lista[menor]) {
					menor = i;
				}
			}
			if (menor != fixo) {
				int t = lista[fixo];
				lista[fixo] = lista[menor];
				lista[menor] = t;

			}

		}

		long tempoFinal = System.currentTimeMillis();
		System.out.println("Executado Em = " + (tempoFinal - tempoInicial) + " ms ");

	}

	public boolean removeElemento(int j) {
		if (tahVazia()) {
			return false;

		}

		else {
			for (int i = 0; i < pos; i++) {
				if (lista[i] == j) {

					lista[i] = lista[pos];
					pos--;
				}
			}
			return true;

		}
	}

	public boolean imprimiElementos() {
		if (tahVazia()) {

			return false;

		} else {

			for (int i = 0; i < pos; i++) {

				System.out.println(lista[i]);
			}
			return true;
		}

	}

}
