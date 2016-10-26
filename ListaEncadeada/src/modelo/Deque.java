package modelo;

public class Deque extends ListaEnc {

	@Override
	public void remover(int valor) {
		System.out.println("Nao suportado");
	}

	public void removerInicio() {
		ini = ini.prox;
	}

	public void removerFinal() {
		No ant = ini;
		No aux = ini.prox;
		while (aux.prox != null) {
			ant = aux;
			aux = aux.prox;
		}
		ant.prox = null;
	}

	public void inserirInicio(int valor) {
		No novo = new No();
		novo.valor = valor;
		novo.prox = ini;
		ini = novo;
	}
}
