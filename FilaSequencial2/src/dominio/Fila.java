package dominio;

public class Fila {

	private int[] fila;
	private int inicio, fim;

	public Fila(int tam) {

		fila = new int[tam];
		inicio = fim = -1;

	}

	public boolean tahVazia() {

		if (inicio == -1 && fim == -1)
			return true;

		return false;

	}

	public boolean tahCheia() {

		if (inicio == ((fim + 1) % fila.length))

			return true;

		return false;

	}

	public boolean insereFila(int v) {
		if (!tahCheia()) {

			fim = (fim + 1) % fila.length;
			fila[fim] = v;
			if (inicio == -1) {
				inicio = fim;
			}
			return true;
		} else
			return false;

	}

	public boolean removeFila() {
		if (!tahVazia()) {

			if (inicio == fim)
				inicio = fim = -1;
			else {
				inicio = (inicio + 1) % fila.length;

			}
			return true;

		} else
			return false;
	}

	public void imprimeFila() {

		while (inicio != fim) {

			System.out.println(fila[inicio]);
			inicio += ((inicio + 1) % fila.length);
		}

	}

	public boolean limpaFila() {
		if (!tahVazia()) {
			inicio = fim = -1;
			return true;

		} else
			return false;

	}

}
