package dominio;

public class FilaDinamica {

	private Fila inicio;
	private Fila fim;

	public FilaDinamica() {
		inicio = null;
		fim = inicio;

	}

	public boolean tahVazia() {
		if (inicio == null && fim == null)
			return true;
		return false;

	}

	public void inserirNaFila(int info) {
		Fila novo = new Fila();
		novo.setInfo(info);
		novo.setProx(null);
		if (tahVazia()) {
			inicio = fim = novo;

		} else {

			fim.setProx(novo);
			fim = novo;
		}

	}

	public int removeDaFila() {
		if (!tahVazia()) {
			if (inicio == fim) {
				int valor = inicio.getInfo();
				inicio = fim = null;
				return valor;
			} else {
				int valor = inicio.getInfo();
				inicio = inicio.getProx();
				return valor;

			}

		} else {

			System.out.println("Lista Vazia");
		}
		return -1;
	}

	public void imprimeInicio() {
		while (inicio != null) {

			System.out.println("Inicio " + inicio.getInfo());
			inicio = inicio.getProx();
		}

	}

	public void imprimeFim() {
		while (fim != null) {

			System.out.println("Fim " + fim.getInfo());
			fim = fim.getProx();
		}

	}

	public int buscaNaFila(int info) {
		if (!tahVazia()) {
			if (inicio.getInfo() == info) {
				return info;

			} else {
				while (inicio.getInfo() != info) {
					inicio = inicio.getProx();

				}
				return info;

			}

		}

		else {

			System.out.println("Lista Vazia");
		}
		return -1;
	}
}
