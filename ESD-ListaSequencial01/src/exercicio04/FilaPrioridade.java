package exercicio04;

public class FilaPrioridade {
	int ini;
	int fim;
	int quantP = 0;
	String[] fila;

	public FilaPrioridade(int n) {
		this.fila = new String[n];
		ini = -1;
		fim = ini;
	}

	public String remover() {
		String aux = fila[ini];
		if (ini == -1) {
			System.out.println("Fila Vazia");
			return null;
		}
		if (quantP > 0) {
			quantP--;
		}
		int i = 0;
		while (i < fim) {
			fila[i] = fila[i + 1];
			i++;
		}
		fim--;
		return aux;
	}

	public void inserir(String nome, boolean prioridade) {
		fim++;
		// fila com elementos
		if (ini != -1) {
			// sem prioridade
			if (!prioridade) {
				fila[fim] = nome;
			}
			// com prioridade
			else {
				int aux = fim - quantP;// calculo o número de elementos sem
										// prioridade
				for (int i = fim; i > quantP; i--) {
					fila[i] = fila[i - 1];
				}
				fila[quantP] = nome;
				quantP++;
			}
		}
		// fila vazia
		else {
			ini = 0;
			fila[fim] = nome;
			if (prioridade) {
				quantP++;
			}
		}
	}

	public void listarCrescente() {
		int i = 0;
		while (i <= fim) {
			System.out.println(fila[i]);
			i++;
		}
	}

	public void listarDecrescente() {
		int i = fim;
		while (i >= 0) {
			System.out.println(fila[i]);
			i--;
		}
	}

}
