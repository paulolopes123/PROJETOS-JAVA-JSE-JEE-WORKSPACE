package modelo;

public class FilaPrioridade extends Fila {

	@Override
	public void inserir(int valor) {
		System.out.println("Nao suportado");
	}

	public void inserir(int valor, boolean emergencia) {
		No novo = new No();
		novo.valor = valor;
		novo.emergencia = emergencia;

		if (ini != null) {
			No aux = ini;
			if (emergencia) {
				while (aux.prox != null) {
					if (!aux.prox.emergencia) {
						break;
					}
					aux = aux.prox;
				}
				novo.prox = aux.prox;

			} else {
				while (aux.prox != null) {
					aux = aux.prox;
				}
			}
			aux.prox = novo;

		} else {
			ini = novo;
		}
	}

	public void atender() {
		No ant = ini;
		No aux = ini.prox;
		while (aux != null) {
			if (aux.emergencia) {
				break;
			}
			ant = aux;
			aux = aux.prox;
		}
		if (aux != null) {
			ant.prox = aux.prox;
		}
	}
}