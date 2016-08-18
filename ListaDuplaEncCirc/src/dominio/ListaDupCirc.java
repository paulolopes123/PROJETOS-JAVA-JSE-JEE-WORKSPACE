package dominio;

public class ListaDupCirc {

	private Pessoa ptL,aux;

	public Pessoa getReferenciaLista() {
		return ptL;
	}

	public void inserirNaoOrdenada(String nome, int idade, String sexo) {
		// instacia o novo objeto
		Pessoa novo = new Pessoa(nome, idade, sexo);

		// Insere o novo objeto na lista
		if (ptL == null) { // primeiro na lista

			novo.setAnt(ptL);
			novo.setProx(ptL);
			ptL = novo;
			aux = ptL;
		}
		// Se existir um ou mais nós na lista
		// o novo nó será inserido como sucessor de ptL
		else {
			// "pendura" o novo nó na lista
			novo.setProx(aux.getProx());
			novo.setAnt(aux);
			
			// Ajusta as referencias do(s) nó(s)
			// existente(s) na lista
			aux.setProx(novo);
			novo.getProx().setAnt(novo);
		}
	}

	public void inserirOrdenadaCresc(String nome, int idade, String sexo) {
		// instacia o novo objeto
		Pessoa novo = new Pessoa(nome, idade, sexo);

		// Insere o novo objeto na lista
		if (ptL == null) { // primeiro na lista
			ptL = novo;
			novo.setAnt(ptL);
			novo.setProx(ptL);
		}
		// A lista tem pelo menos um nó
		else {
			Pessoa ptAux = ptL;
			int resp;
			do {
				// compareTo() retorna -1, 0 ou 1
				// -1: ptAux.getNome() < novo.getNome()
				// 0: ptAux.getNome() == novo.getNome()
				// 1: ptAux.getNome() > novo.getNome()
				resp = ptAux.getNome().compareTo(novo.getNome());
				// insere novo na lista
				if (resp >= 0) {
					// "Pendura" o novo nó
					novo.setAnt(ptAux.getAnt());
					novo.setProx(ptAux);
					// Altera a ref dos nós da lista
					ptAux.getAnt().setProx(novo);
					ptAux.setAnt(novo);
					// Se o novo nome foi inserido antes do nome de ptL
					if (ptAux == ptL) {
						ptL = novo;
					}
					break;
				} else {
					if (ptAux.getProx() == ptL) {
						// "Pendura" o novo nó
						novo.setProx(ptL);
						novo.setAnt(ptAux);
						// Altera as refs dos nós da lista
						ptAux.setProx(novo);
						ptL.setAnt(novo);
					}
				}
				ptAux = ptAux.getProx();

			} while (ptAux != ptL);
		}
	}

	public void listar() {
		Pessoa ptAux = ptL;
		if (ptL != null) {
			System.out.println("---------------");
			do {
				System.out.println(ptAux.getNome());
				System.out.println(ptAux.getIdade());
				System.out.println(ptAux.getSexo());
				System.out.println("---------------");
				ptAux = ptAux.getProx();
			} while (ptAux != ptL);
		} else {
			System.out.println("Lista Vazia!");
		}
	}

}
