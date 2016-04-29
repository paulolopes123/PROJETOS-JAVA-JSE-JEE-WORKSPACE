package dominio;

import java.awt.font.LayoutPath;

public class ListaLinearSequencial {
	private int[] lista;
	private int lastPos;// indice da ultima posi��o preenchida na lista

	public ListaLinearSequencial(int tamanho) {
		this.lista = new int[tamanho];
		lastPos = -1;// lista vazia
	}

	// verifica se a lista est� cheia
	public boolean listaTahCheia() {
		if (lastPos == lista.length - 1)
			return true;
		else
			return false;
	}

	// verifica se a lista est� vazia
	public boolean listaTahVazia() {
		if (lastPos == -1)
			return true;
		else
			return false;
	}

	// inserir um elemento na lista N�O ORDENADA
	// insere ap�s o �ltimo inserido, se n�o estiver cheia
	public boolean insereNaLista(int valor) {
		if (listaTahCheia()) {
			return false;
		} else {
			lista[++lastPos] = valor;
			return true;
		}
	}

	// remove um elemento ESPECIFICO da lista, no caso, VALOR
	// a remo��o s� ocorre se VALOR for encontrado na lista
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
	// retorno a posi��o (indice no vetor) se o elemento for encontrado
	// ou -1 caso contrario
	public int buscaValorNaLista(int valor) {
		if (listaTahVazia()) {
			return -1;// n�o achou - lista vazia
		} else {
			for (int i = 0; i <= lastPos; i++) {
				if (valor == lista[i]) {
					return i;
				}
			}
			return -1;// n�o achou
		}
	}

	// inserir em uma lista ordenada (crescente/decrescente)
	public boolean insereNaListaOrdenada(int valor, char ordem) {
		int aux;
		if (listaTahCheia()) {
			return false;
		} else {
			// TO DO
			for (int i = 0; i <= lastPos; i++) {
				if (valor < lista[i]) {
					aux = lista[i];
					lista[i] = valor;
					valor = aux;
				}
			}
			return true;
		}

	}

	// ordenar a lista (crescente/decrescente)
	// Sugiro implementar o BUBBLE SORT
	// ----- TO DO AT HOME
	public boolean ordenaLista() {
		int aux;
		if (listaTahVazia()) {

			return false;
		} else {
			for (int i = 0; i <= lastPos; i++) {
				if (lista[i] < lista[i + 1]) {
					aux = lista[i + 1];
					lista[i + 1] = lista[i];
					lista[i] = aux;

				}

			}
			return true;
		}
	}

	// imprimir a lista
	public void imprimeListaLinearSeq() {
		System.out.println("\n------- Imprindo a Lista Linear Sequencial -------");
		for (int i = 0; i <= lastPos; i++) {
			System.out.println(lista[i]);
		}
	}
}
