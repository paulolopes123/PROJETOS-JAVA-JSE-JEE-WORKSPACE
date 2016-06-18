package dominio;

/*
 * Escreva uma aplicação Java que contenha uma árvore binária de busca cujos nós guardarão,
 *  além das referências para o filho esquerdo e o filho direito, apenas um valor inteiro.
 *   Forneça um método inserir() que permitirá inserir os valores na árvore. Em seguida forneça
 *    um método recursivo que permitirá fazer a travessia in-order da árvore.
 * 
 * 
 * 
 */
public class NoArvore {
	private int valor;// valor armazenado no nó
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
