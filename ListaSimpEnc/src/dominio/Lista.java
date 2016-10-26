package dominio;

public class Lista {

	private Nodo ptLista, aux, ante;

	public void Lista() {
		// Null quando se inicializa
		ptLista = aux = ante = null;

	}

	public Nodo getPtLista() {
		return ptLista;
	}

	public void addNoInicioLista(int info) {
		Nodo no = new Nodo();
		no.setInfo(info);
		no.setProx(ptLista);
		ptLista = no;
	}

	public Nodo buscaNo(int info) {
		Nodo ptAux = ptLista;
		while (ptAux != null) {
			if (ptAux.getInfo() == info) {
				return ptAux;
			}
			ptAux = ptAux.getProx();
		}
		return null;
	}

	public boolean removeNo(int info) {
		Nodo ptAnt = null;
		Nodo ptAux = ptLista;
		while (ptAux != null) {
			if (ptAux.getInfo() == info) {
				if (ptAnt == null) { // Removendo o primeiro nó
					ptLista = ptLista.getProx();
					return true;
				}
				// Remove um nó qualquer da lista diferente do primeiro
				ptAnt.setProx(ptAux.getProx());
				return true;
			}
			ptAnt = ptAux;
			ptAux = ptAux.getProx();
		}
		return false;
	}

	public void adicionaOrdenado(int info) {
		aux = ptLista;
		Nodo novo = new Nodo();
		novo.setInfo(info);
		while (aux != null && aux.getInfo() < info) {
			ante = aux;
			aux = aux.getProx();

		}
		if (ante == null) {
			novo.setProx(ptLista);
			ptLista = novo;

		} else {
			novo.setProx(ante.getProx());
			ante.setProx(novo);

		}

	}

	public void imprimir() {
		Nodo aux = ptLista;
		while (aux != null) {
			System.out.println(aux.getInfo());
		}

	}

}
