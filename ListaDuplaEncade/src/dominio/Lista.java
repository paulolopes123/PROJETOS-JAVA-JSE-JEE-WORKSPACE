package dominio;

public class Lista {

	private Noh inicio;
	private int qtd;

	public Lista() {
		super();

		criaLista();

	}

	public void criaLista() {
		inicio = null;
		qtd = -1;

	}

	public void insereInicio(int info) {

		Noh novo = new Noh();

		novo.setInfo(info);

		if (listaVazia()) {
			novo.setProx(null);
			novo.setAnt(null);
			novo = inicio;
			qtd += 1;

		} else {
			novo.setProx(inicio);
			inicio.setAnt(novo);
			novo.setAnt(null);
			inicio = novo;
			qtd += 1;

		}
	}

	public boolean listaVazia() {
		if (inicio == null)
			return true;
		return false;

	}

	public void imprimeLista() {
		if (!listaVazia()) {
			for (Noh lista = inicio; lista != null; lista = lista.getProx()) {
				System.out.println(lista.getInfo());
			}

		}

	}

	public int tamanhoLista() {

		return qtd + 1;
	}

	public Noh buscaNoh(int info) {

		if (listaVazia()) {

			System.out.println("Lista Vazia");
		} else {

			// caso seje a primeiro Noh da lista
			Noh busca = inicio;
			inicio = inicio.getProx();
			return busca;

		}

		for (Noh busca = inicio; busca != null; busca = busca.getProx()) {
			if (busca.getInfo() == info)
				return busca;
		}

		return null;
	}

}
