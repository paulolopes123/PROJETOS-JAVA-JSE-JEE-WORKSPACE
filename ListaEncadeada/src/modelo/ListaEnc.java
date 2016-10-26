package modelo;

public class ListaEnc {
	public No ini;

	public void inserir(int valor) {
		No novo = new No();
		novo.valor = valor;
		if (ini != null) {
			No aux = ini;
			while (aux.prox != null) {
				aux = aux.prox;
			}
			aux.prox = novo;
		} else {
			ini = novo;
		}
	}

	public void listar() {
		System.out.println("listando...");
		No aux = ini;

		while (aux != null) {
			System.out.println(aux.valor);
			aux = aux.prox;
		}
	}

	public void remover(int valor) {
		if (ini.valor == valor)
			ini = ini.prox;
		else {
			No ant = ini;
			No aux = ini.prox;
			while (aux != null) {
				if (aux.valor == valor)
					break;
				ant = aux;
				aux = aux.prox;
			}
			if (aux != null) {
				ant.prox = aux.prox;
				System.out.println("valor: \"" + valor
						+ "\" Removido com sucesso");
			} else {
				System.out.println("valor: \"" + valor + "\" Não encontrado");
			}

		}
	}

	public void remover() {
		System.out.println("Metodo nao suportado");
	}

	public void buscar(int elemento) {
		String mensagem = "Elemento nao encontrado";
		No aux = ini;
		while (aux != null) {
			if (aux.valor == elemento) {
				mensagem = "Elemento encontrado";
			}
			aux = aux.prox;
		}
		System.out.println(mensagem);
	}

	public void inverter() {
		No[] inverso = new No[getTamanho()];
		No aux = ini;
		for (int i = 0; i < inverso.length; i++) {
			inverso[i] = aux;
			aux = aux.prox;
		}

		int j = 0;
		int k = inverso.length - 1;
		while (j < k) {
			aux = inverso[j];
			inverso[j] = inverso[k];
			inverso[k] = aux;

			j++;
			k--;
		}

		ini = null;
		for (int i = 0; i < inverso.length; i++) {
			inserir(inverso[i].valor);
		}
	}

	public void ImprimirInvertido(No lista) {
		if (lista != null) {
			ImprimirInvertido(lista.prox);
			System.out.println(lista.valor);
		}
	}

	public int getTamanho() {
		int tamanho = 0;
		No aux = ini;
		while (aux != null) {
			tamanho++;
			aux = aux.prox;
		}
		return tamanho;
	}
}
