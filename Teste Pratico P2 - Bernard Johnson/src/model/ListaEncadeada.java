package model;

public class ListaEncadeada {

	private Noh ptLista, ptl, aux;
	private int qtd;

	public ListaEncadeada() {
		super();
		this.ptLista = null;
	}

	public boolean inserir(String nome) {

		Noh novo = new Noh(nome);

		if (listaVazia()) {
			ptl = novo;
			ptl.setProx(ptl);
			ptl.setAnt(ptl);
		} else {
			Noh aux = ptl;

			while (aux.getProx() != ptl) {
				aux = aux.getProx();
			}
			novo.setAnt(aux);
			aux.setProx(novo);

			novo.setProx(ptl);
			ptl.setAnt(novo);
		}
		return true;
	}

	public String contagem(int numero) {

		Noh aux = ptl;
		if (aux.getProx() != aux) {

			for (int i = 0; i < numero; i++) {
				if (i != 0)
					aux = aux.getProx();
			}

			if (aux == ptl) {
				ptl = aux.getProx();
				aux.getAnt().setProx(aux.getProx());
			} else
				aux.getAnt().setProx(aux.getProx());
			aux.getProx().setAnt(aux.getAnt());

			if (ptl.getProx() != ptl)
				return "Continue contando, ainda não temos um vencedor!";
			else
				return "Opa, temos um vencedor: " + ptl.getNome();

		} else {
			return "Opa, já temos um vencedor: " + aux.getNome();
		}
	}

	public boolean listaVazia() {

		if (ptLista == null)
			return true;
		return false;
	}
}
