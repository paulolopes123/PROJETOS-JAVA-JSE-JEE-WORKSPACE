package dominio;

public class PilhaDinamica {

	private Pilha topo;
	private int qtd;

	public PilhaDinamica() {

		topo = null;
		qtd = -1;
	}

	public boolean tahVazia() {

		if (topo == null)
			return true;
		return false;
	}

	public void Empilha(int info) {
		Pilha novo = new Pilha();
		novo.setInfo(info);
		if (tahVazia()) {

			novo.setProx(topo);
			topo = novo;
			qtd = qtd + 1;

		} else {

			novo.setProx(topo);
			topo = novo;
			qtd = qtd + 1;

		}
	}

	public void Desempilha() {
		if (qtd != 0) {
			topo = topo.getProx();
			qtd--;
		} else {

			System.out.println("Lista Vazia");
		}
	}

	public void imprimePilha() {

		if (qtd != 0) {
			while (topo != null) {
				System.out.println(topo.getInfo());
				topo = topo.getProx();

			}
		} else {
			System.out.println("Lista Vazia");

		}

	}

	public int tamanhoPilha() {
		return qtd + 1;

	}

	public int getTopo() {
		return topo.getInfo();

	}

}
