package modelo;

public class Pilha extends ListaEnc {

	@Override
	public void remover(int valor) {
		System.out.println("Nao suportado");
	}

	public int pop() {
		int elemento;
		No ant = ini;
		No aux = ini.prox;
		while (aux.prox != null) {
			ant = aux;
			aux = aux.prox;
		}
		elemento = aux.valor;
		ant.prox = null;
		return elemento;
	}

	public void push(int valor) {
		inserir(valor);
	}
}
