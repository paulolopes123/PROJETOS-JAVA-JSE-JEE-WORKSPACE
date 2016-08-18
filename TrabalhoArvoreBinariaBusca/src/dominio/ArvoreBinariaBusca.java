package dominio;

/*
 * Implementar os métodos:
 * buscaAluno()
 * removeAluno()
 * imprimeTurma(int metodo) //1 - Pos-Ordem 2 - Pre-Ordem 3 - Simetrico(Em-Ordem)
 * insereAluno(int matric)
 */

public class ArvoreBinariaBusca {
	private NoArvore ptRaiz; // referência para a raiz da árvore

	// método usado para inserir um novo nó na árvore
	// retorna true se o nó for inserido com sucesso e false se o elemento
	// não puder ser inserido (no caso de já existir um elemento igual)
	public boolean insereNo(String nome, int cr, int matric) {
		// a árvore ainda está vazia?
		if (ptRaiz == null) {
			// vamos criar o primeiro nó e definí-lo como a raiz da árvore
			ptRaiz = new NoArvore(nome, cr, matric); // cria um novo nó
		}
		// árvore não tá vazia-> Insiro o nó na arvore se ele nao existir
		else {
			// localiza o nó pai
			NoArvore pai = null;
			NoArvore noAtual = ptRaiz; // começa a busca pela raiz

			// enquanto o nó atual for diferente de null
			while (noAtual != null) {
				if (matric < noAtual.getMatricula()) {
					pai = noAtual;
					noAtual = noAtual.getEsquerdo();
				} else if (matric > noAtual.getMatricula()) {
					pai = noAtual;
					noAtual = noAtual.getDireito();
				} else {
					return false; // um nó com este valor foi encontrado
				}
			}

			// cria o novo nó e o adiciona ao nó pai
			if (matric < pai.getMatricula()) {
				pai.setEsquerdo(new NoArvore(nome, cr, matric));
			} else {
				noAtual = new NoArvore(nome, cr, matric);
				pai.setDireito(noAtual);
			}
		}

		return true; // retorna true para indicar que o novo nó foi inserido
	}

	// --------------------------------------------------------------------

	// método que permite disparar a travessia pré-ordem
	public void preOrdem() {
		preOrdem(ptRaiz);
	}

	// sobrecarga do método pré-Ordem com um parâmetro (esta é a versão
	// recursiva do método)
	private void preOrdem(NoArvore raiz) {
		if (raiz == null) { // condição de parada
			return;
		}
		// visita o nó atual
		System.out.print(raiz.getNome() + " ");
		System.out.print(raiz.getMatricula() + " ");
		System.out.println("--------");
		// visita a sub-árvore da esquerda
		preOrdem(raiz.getEsquerdo());

		// visita a sub-árvore da direita
		preOrdem(raiz.getDireito());

	}

	// --------------------------------------------------------------------
	// método que permite disparar a travessia em-ordem
	public void emOrdem() {
		emOrdem(ptRaiz);
	}

	// sobrecarga do método emOrdem com um parâmetro (esta é a versão recursiva
	// do método)
	private void emOrdem(NoArvore raiz) {
		if (raiz == null) { // condição de parada
			return;
		}

		// visita a sub-árvore da esquerda
		emOrdem(raiz.getEsquerdo());
		// visita o nó atual
		System.out.print(raiz.getNome() + " ");
		System.out.print(raiz.getMatricula() + " ");
		System.out.println("--------");
		// visita a sub-árvore da direita
		emOrdem(raiz.getDireito());
	}

	// --------------------------------------------------------------------

	// método que permite disparar a travessia pós-ordem
	public void posOrdem() {
		posOrdem(ptRaiz);
	}

	// sobrecarga do método pós-Ordem com um parâmetro (esta é a versão
	// recursiva do método)
	private void posOrdem(NoArvore raiz) {
		if (raiz == null) { // condição de parada
			return;
		}

		// visita a sub-árvore da esquerda
		posOrdem(raiz.getEsquerdo());

		// visita a sub-árvore da direita
		posOrdem(raiz.getDireito());

		// visita o nó atual
		System.out.print(raiz.getNome() + " ");
		System.out.print(raiz.getMatricula() + " ");
		System.out.println("--------");

	}

	// Método que permite informar a altura da árvore
	public int alturaArvore() {
		return alturaArvore(ptRaiz);
	}

	// Sobrecarga do Método alturaArvore com um parâmetro(esta é a versão
	// recursiva do método
	private int alturaArvore(NoArvore raiz) {

		if (raiz == null) {
			return 0;

		} else {
			int altEsq = alturaArvore(raiz.getEsquerdo());
			int altDirei = alturaArvore(raiz.getDireito());
			if (altEsq < altDirei) {
				return altDirei + 1;
			} else {
				return altEsq + 1;
			}

		}
	}

	// Método que permite retornar a quantidade de nós da árvore
	public int qtdNoArvore() {
		return qtdNoArvore(ptRaiz);

	}

	// Sobrecarga do Método qtdNoArvore com um parâmetro(esta é a versão
	// recursiva do método
	private int qtdNoArvore(NoArvore raiz) {
		if (raiz == null) {
			return 0;

		} else {
			int noEsq = qtdNoArvore(raiz.getEsquerdo());
			int noDirei = qtdNoArvore(raiz.getDireito());
			return noEsq + noDirei + 1;
		}

	}

	// Método que permite buscar um aluno na árvore pela matricula
	public int buscaAluno(int matricula) {
		if (this.ptRaiz == null) {
			return 0;
		} else {
			if (matricula == this.ptRaiz.getMatricula()) {
				return this.ptRaiz.getMatricula();
			} else {
				if (matricula > this.ptRaiz.getMatricula()) {
					if (this.ptRaiz.getDireito() == null) {
						return 0;
					}
					return this.buscaAluno(matricula);
				} else {
					if (this.ptRaiz.getEsquerdo() == null) {
						return 0;
					}
					return this.buscaAluno(matricula);
				}
			}
		}

	}

	// Método que permite remmover um aluno

	public NoArvore remover(int matricula) throws Exception {
		return remover(this.ptRaiz,matricula);
	}

	private NoArvore remover(NoArvore node, int matricula) throws Exception {
		if (this.ptRaiz == null) {
			throw new Exception("Árvore vazia");
		} else {
			if (matricula < node.getMatricula()) {
				node.setEsquerdo(remover(node.getEsquerdo(), matricula));
			} else if (matricula > node.getMatricula()) {
				node.setDireito(remover(node.getDireito(), matricula));
			} else if (node.getEsquerdo() != null && node.getDireito() != null) {
				/* 2 filhos */
				System.out.println(" Removeu No " + node.getMatricula());
				node.setMatricula(encontraMinimo(node.getDireito()).getMatricula());
				node.setDireito(removeMinimo(node.getDireito()));
			} else {
				System.out.println(" Removeu No " + node.getMatricula());
				node = (node.getEsquerdo() != null) ? node.getEsquerdo() : node.getDireito();
			}
			return node;
		}
	}

	private NoArvore removeMinimo(NoArvore node) {
		if (node == null) {
			System.out.println(" ERRO ");
		} else if (node.getEsquerdo() != null) {
			node.setEsquerdo(removeMinimo(node.getEsquerdo()));
			return node;
		} else {
			return node.getDireito();
		}
		return null;
	}

	private NoArvore encontraMinimo(NoArvore node) {
		if (node != null) {
			while (node.getEsquerdo() != null) {
				node = node.getEsquerdo();
			}
		}
		return node;
	}

}