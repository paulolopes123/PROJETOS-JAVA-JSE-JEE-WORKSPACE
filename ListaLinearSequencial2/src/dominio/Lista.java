package dominio;

public class Lista {

	private int[] lista;
	private int index;

	public Lista(int tam) {

		lista = new int[tam];
		index = -1;

	}

	public boolean tahVazia() {
		if (index == -1)
			return true;

		return false;

	}
}