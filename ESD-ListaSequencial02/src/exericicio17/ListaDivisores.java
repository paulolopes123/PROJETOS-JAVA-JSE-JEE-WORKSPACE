package exericicio17;

public class ListaDivisores {

	private int[] lista;

	public ListaDivisores(int v) {
		lista = new int[v];
	}

	public void divisor(int v) {
		int j = -1;
		for (int i = 1; i <= v; i++) {
			if (v % i == 0) {

				lista[++j] = i;
				System.out.println(lista[j]);
			}

		}

	}

}
