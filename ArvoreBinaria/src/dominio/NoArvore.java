package dominio;

/*
 * Escreva uma aplica��o Java que contenha uma �rvore bin�ria de busca cujos n�s guardar�o,
 *  al�m das refer�ncias para o filho esquerdo e o filho direito, apenas um valor inteiro.
 *   Forne�a um m�todo inserir() que permitir� inserir os valores na �rvore. Em seguida forne�a
 *    um m�todo recursivo que permitir� fazer a travessia in-order da �rvore.
 * 
 * 
 * 
 */
public class NoArvore {
	private int valor;// valor armazenado no n�
	private NoArvore esquerdo;// filho esquerdo
	private NoArvore direito;// filho direito

	public NoArvore() {
		super();
	}

	public NoArvore(int valor) {
		super();
		this.valor = valor;
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
