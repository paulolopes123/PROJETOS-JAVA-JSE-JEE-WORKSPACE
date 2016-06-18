package dominio;

public class NoArvore {
	private int valor;
	private NoArvore esquerdo;
	private NoArvore direito;

	public NoArvore(int valor) {
		super();
		this.valor = valor;
	}

	public NoArvore() {
		super();
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public NoArvore getEsquerdo() {
		return esquerdo;
	}

	public void setEsquerdo(NoArvore esquerdo) {
		this.esquerdo = esquerdo;
	}

	public NoArvore getDireito() {
		return direito;
	}

	public void setDireito(NoArvore direito) {
		this.direito = direito;
	}

}
