package dominio;

public class Pilha2 {

	private int topo;
	private int[] vetor;

	public Pilha2() {

		vetor = new int[10];
		topo = -1;
	}

	public boolean pilhaVazia() {

		if (topo == -1)
			return true;

		return false;

	}

	public boolean pilhaCheia() {

		if (topo == vetor.length - 1)
			return true;
		return false;
	}

	public int tam() {
		if (pilhaVazia())
			return 0;
		return topo++;

	}

	public int ultiValor() {
		if (pilhaVazia())
			return 0;
		return vetor[topo];

	}

	public void Push(int val) {
		if (!pilhaCheia()) {

			if (topo < vetor.length - 1) {
				vetor[++topo] = val;
				System.out.println(vetor[topo]);
			}
		} else
			System.out.println("Lista cheia");
	}

	public int Pop() {
		if (pilhaVazia())
			System.out.println("Lista vazia");
		return vetor[topo--];

	}

}
