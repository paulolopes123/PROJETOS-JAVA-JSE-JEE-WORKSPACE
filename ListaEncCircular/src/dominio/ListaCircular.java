package dominio;

public class ListaCircular {

	private Nodo ptLista;

	public void ListaCircular() {
		//Null quando se inicializa
		ptLista = null;
	}

	public Nodo getPtLista() {
		return ptLista;
	}

	public void addNoLista(int info) {
		Nodo no = new Nodo();
		no.setInfo(info);
		if(ptLista == null){
			ptLista = no;
			ptLista.setProx(ptLista);
		}
		no.setProx(ptLista.getProx());
		ptLista.setProx(no);
	}

	public Nodo buscaNo(int info) {
		Nodo ptAux = ptLista;
		if (ptAux == null) {
			return null;
		} else {
			do {
				if (ptAux.getInfo() == info) {
					return ptAux;
				}
				ptAux = ptAux.getProx();
			} while (ptAux != ptLista);
			return null;
		}
	}
	public boolean removeNo(int info) {
		Nodo ptAnt = null;
		Nodo ptAux = ptLista;
		while(ptAux != null) {
			if (ptAux.getInfo() == info) {
				if (ptAnt == null) { //Removendo o primeiro nó
					ptLista = ptLista.getProx();
					return true;
				}
				//Remove um nó qualquer da lista diferente do primeiro
				ptAnt.setProx(ptAux.getProx());
				return true;
			}
			ptAnt = ptAux;
			ptAux = ptAux.getProx();
		}	
		return false;		
	}

}
