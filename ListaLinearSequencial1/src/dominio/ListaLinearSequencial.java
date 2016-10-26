package dominio;

import java.awt.font.LayoutPath;

public class ListaLinearSequencial {
	private int[] lista;
	private int lastPos;// indice da ultima posição preenchida na lista

	public ListaLinearSequencial(int tamanho) {
		this.lista = new int[tamanho];
		lastPos = -1;// lista vazia
	}

	// verifica se a lista está cheia
	public boolean listaTahCheia() {
		if (lastPos == lista.length - 1)
			return true;
		else
			return false;
	}

	// verifica se a lista está vazia
	public boolean listaTahVazia() {
		if (lastPos == -1)
			return true;
		else
			return false;
	}

	// inserir um elemento na lista NÃO ORDENADA
	// insere após o último inserido, se não estiver cheia
	public boolean insereNaLista(int valor) {
		if (listaTahCheia()) {
			return false;
		} else {
			lista[++lastPos] = valor;
			return true;
		}
	}

	// remove um elemento ESPECIFICO da lista, no caso, VALOR
	// a remoção só ocorre se VALOR for encontrado na lista
	public boolean removeDaLista(int valor) {
		int index = buscaValorNaLista(valor);
		if (index != -1) {
			lista[index] = lista[lastPos--];
			return true;
		} else {
			return false;
		}
	}

	// procurar um elemento na lista
	// retorno a posição (indice no vetor) se o elemento for encontrado
	// ou -1 caso contrario
	public int buscaValorNaLista(int valor) {
		if (listaTahVazia()) {
			return -1;// não achou - lista vazia
		} else {
			for (int i = 0; i <= lastPos; i++) {
				if (valor == lista[i]) {
					return i;
				}
			}
			return -1;// não achou
		}
	}
	/*
	 * // inserir em uma lista ordenada (crescente/decrescente) public boolean
	 * insereNaListaOrdenada(int valor) { int aux; if (listaTahCheia()) { return
	 * false; } else { // TO DO
	 * 
	 * for (int i = 0; i < lastPos; i++) { if (lista[valor] < lista[i]) { aux =
	 * lista[valor]; lista[valor] = lista[i]; lista[i] = aux; } lista[++lastPos]
	 * = valor;
	 * 
	 * }
	 * 
	 * return true; }
	 * 
	 * }
	 */

	// ordenar a lista (crescente/decrescente)
	// Sugiro implementar o BUBBLE SORT
	// ----- TO DO AT HOME
	public void ordenaLista() {
		boolean troca = true;
		int aux;
		while (troca) {
			troca = false;
			for (int j = 0; j < lastPos; j++) {
				if (lista[j] < lista[j + 1]) {
					aux = lista[j];
					lista[j] = lista[j + 1];
					lista[j + 1] = aux;
					troca = true;
				}

			}
		}
	}

	// imprimir a lista
	public void imprimeListaLinearSeq() {
		System.out.println("\n------- Imprindo a Lista Linear Sequencial -------");
		for (int i = 0; i <= lastPos; i++) {
			System.out.println(lista[i]);
		}
	}

	// tamanho da lista
	public int getTamanho() {
		return lastPos + 1;

	}

	// retorna o elemento em uma posição especifica
	public int retornaPos(int pos) {
		if (listaTahVazia()) {
			System.out.println("Lista Vazia");
			return 0;
		}

		if (pos < 0 || pos > lastPos) {
			System.out.println("Indice invalido");
			return 0;
		}

		return lista[pos];
	}

	// insere um elemento em determinada posição
	public boolean inserePos(int elemento, int pos) {
		if (listaTahVazia()) {
			System.out.println("Lista vazia");
			return false;
		}
		if (pos < 0 || pos > lastPos) {
			System.out.println("erro indice invalido");
			return false;
		}

		lastPos++;
		lista[pos] = elemento;
		return true;
	}

	// retorna o elemento da ultima posicao
	public int ultimoElemento() {
		return lista[lastPos];

	}
}
