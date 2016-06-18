package dominio;

public class Pilha {

	private int info;
	private Pilha prox;

	public Pilha() {
		super();
	}

	public int getInfo() {
		return info;
	}

	public void setInfo(int info) {
		this.info = info;
	}

	public Pilha getProx() {
		return prox;
	}

	public void setProx(Pilha prox) {
		this.prox = prox;
	}

}
