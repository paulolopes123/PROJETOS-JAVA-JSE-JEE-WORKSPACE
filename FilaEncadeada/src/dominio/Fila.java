package dominio;

public class Fila {

	private int info;
	private Fila prox;

	public Fila() {
		super();
	}

	public int getInfo() {
		return info;
	}

	public void setInfo(int info) {
		this.info = info;
	}

	public Fila getProx() {
		return prox;
	}

	public void setProx(Fila prox) {
		this.prox = prox;
	}

}
