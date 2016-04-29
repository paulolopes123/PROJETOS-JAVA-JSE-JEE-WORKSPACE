package dominio;

public class Lista {

	private Noh ptLista;
	private int total;

	public Lista() {
		super();
		ptLista = null;
		total = -1;
	}

	public boolean tahVazia() {

		if (ptLista == null)

			return true;
		return false;

	}

	public void insereNaoOrdenado(int info) {
		Noh novo = new Noh();
		novo.setProx(null);
		// caso seja o primeiro Noh
		if (tahVazia()) {
			novo.setInfo(info);
			ptLista = novo;
			novo.setProx(ptLista);
			total++;

		}
		// caso n�o seja o primeiro Noh
		ptLista.setProx(novo);
		ptLista = novo;
		novo.setProx(ptLista);
		total++;

	}

	public void removeNoh(int info) {
		boolean achou = false;
		if (!tahVazia()) {
			// remove o inicio
			if (ptLista.getInfo() == info) {
				ptLista = ptLista.getProx();
				achou = true;
				total--;
			}

			else {
				Noh antNoh = ptLista;
				// comeca a percorrer apartir do segundo elemento e caso
				// encontre ele remove
				for (Noh inicio = ptLista.getProx(); inicio != null; inicio = inicio.getProx()) {
					if (inicio.getInfo() == info) {
						antNoh.setProx(inicio.getProx());
						achou = true;
						System.out.println("elemento removido");
						total--;
					}
					antNoh = inicio;
				}

			}
		} else {
			System.out.println("lista vazia");
		}

		if (!achou) {
			System.out.println("nao achou");
		}
	}

	public int tamanhoLista() {
		return total + 1;

	}

	public void imprimeLista() {
		Noh noh = ptLista;
		while (noh != null) {
			System.out.println(noh.getInfo());
			noh.getProx();

		}

	}

	public int buscaElemento(int info) {
		for (Noh inicio = ptLista; inicio != null; inicio = inicio.getProx()) {

			if (inicio.getInfo() == info) {
				return info;

			}

		}
		return -1;

	}

	// insere ordenado
	public void insereOrdenado(int info) {

		Noh nodo;
		// objeto que guarda o elemento anterior
		Noh ant = null;
		// objeto que percorre a lista
		Noh pri = ptLista;

		// procura o elemento na lista guardando o anterior
		while (pri != null && pri.getInfo() < info) {
			ant = pri;
			pri = pri.getProx();

		}
		// cria um novo elemento
		nodo = new Noh();
		nodo.setInfo(info);

		// encadeia o elemento
		if (ant == null) {// caso seje o primeiro elemento na lista

			nodo.setInfo(info);
			ptLista = nodo;
			nodo.setProx(ptLista);// estabelecendo uma lista circular

		}
		// inseri no meio da lista
		else {

			nodo.setProx(ant.getProx());
			ant.setProx(nodo);
			nodo.setProx(ptLista);// estabelecendo uma lista circular

		}

	}

}
