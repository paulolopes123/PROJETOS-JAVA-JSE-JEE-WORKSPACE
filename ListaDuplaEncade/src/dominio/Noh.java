package dominio;

public class Noh {
	private int info;
	private Noh prox;
	private Noh ant;

	public int getInfo() {
		return info;
	}

	public void setInfo(int info) {
		this.info = info;
	}

	public Noh getProx() {
		return prox;
	}

	public void setProx(Noh prox) {
		this.prox = prox;
	}

	public Noh getAnt() {
		return ant;
	}

	public void setAnt(Noh ant) {
		this.ant = ant;
	}

}
