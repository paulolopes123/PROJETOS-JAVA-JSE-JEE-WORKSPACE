package dominio;

/*
 * Implementar os m�todos:
 * buscaAluno()
 * removeAluno()
 * imprimeTurma(int metodo) //1 - Pos-Ordem 2 - Pre-Ordem 3 - Simetrico(Em-Ordem)
 * insereAluno(int matric)
 */

public class ArvoreBinariaBusca {
	private NoArvore ptRaiz; // refer�ncia para a raiz da �rvore

	// m�todo usado para inserir um novo n� na �rvore
	// retorna true se o n� for inserido com sucesso e false se o elemento
	// n�o puder ser inserido (no caso de j� existir um elemento igual)
	public boolean insereNo(String nome, int cr, int matric) {
		// a �rvore ainda est� vazia?
		if (ptRaiz == null) {
			// vamos criar o primeiro n� e defin�-lo como a raiz da �rvore
			ptRaiz = new NoArvore(nome, cr, matric); // cria um novo n�
		}
		// �rvore n�o t� vazia-> Insiro o n� na arvore se ele nao existir
		else {
			// localiza o n� pai
			NoArvore pai = null;
			NoArvore noAtual = ptRaiz; // come�a a busca pela raiz

			// enquanto o n� atual for diferente de null
			while (noAtual != null) {
				if (matric < noAtual.getMatricula()) {
					pai = noAtual;
					noAtual = noAtual.getEsquerdo();
				} else if (matric > noAtual.getMatricula()) {
					pai = noAtual;
					noAtual = noAtual.getDireito();
				} else {
					return false; // um n� com este valor foi encontrado
				}
			}

			// cria o novo n� e o adiciona ao n� pai
			if (matric < pai.getMatricula()) {
				pai.setEsquerdo(new NoArvore(nome, cr, matric));
			} else {
				noAtual = new NoArvore(nome, cr, matric);
				pai.setDireito(noAtual);
			}
		}

		return true; // retorna true para indicar que o novo n� foi inserido
	}

	// --------------------------------------------------------------------

	// m�todo que permite disparar a travessia pr�-ordem
	public void preOrdem() {
		preOrdem(ptRaiz);
	}

	// sobrecarga do m�todo pr�-Ordem com um par�metro (esta � a vers�o
	// recursiva do m�todo)
	private void preOrdem(NoArvore raiz) {
		if (raiz == null) { // condi��o de parada
			return;
		}
		// visita o n� atual
		System.out.print(raiz.getNome() + " ");
		System.out.print(raiz.getMatricula() + " ");
		System.out.println("--------");
		// visita a sub-�rvore da esquerda
		preOrdem(raiz.getEsquerdo());

		// visita a sub-�rvore da direita
		preOrdem(raiz.getDireito());

	}

	// --------------------------------------------------------------------
	// m�todo que permite disparar a travessia em-ordem
	public void emOrdem() {
		emOrdem(ptRaiz);
	}

	// sobrecarga do m�todo emOrdem com um par�metro (esta � a vers�o recursiva
	// do m�todo)
	private void emOrdem(NoArvore raiz) {
		if (raiz == null) { // condi��o de parada
			return;
		}

		// visita a sub-�rvore da esquerda
		emOrdem(raiz.getEsquerdo());
		// visita o n� atual
		System.out.print(raiz.getNome() + " ");
		System.out.print(raiz.getMatricula() + " ");
		System.out.println("--------");
		// visita a sub-�rvore da direita
		emOrdem(raiz.getDireito());
	}

	// --------------------------------------------------------------------

	// m�todo que permite disparar a travessia p�s-ordem
	public void posOrdem() {
		posOrdem(ptRaiz);
	}

	// sobrecarga do m�todo p�s-Ordem com um par�metro (esta � a vers�o
	// recursiva do m�todo)
	private void posOrdem(NoArvore raiz) {
		if (raiz == null) { // condi��o de parada
			return;
		}

		// visita a sub-�rvore da esquerda
		posOrdem(raiz.getEsquerdo());

		// visita a sub-�rvore da direita
		posOrdem(raiz.getDireito());

		// visita o n� atual
		System.out.print(raiz.getNome() + " ");
		System.out.print(raiz.getMatricula() + " ");
		System.out.println("--------");

	}

	// M�todo que permite informar a altura da �rvore
	public int alturaArvore() {
		return alturaArvore(ptRaiz);
	}

	// Sobrecarga do M�todo alturaArvore com um par�metro(esta � a vers�o
	// recursiva do m�todo
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

	// M�todo que permite retornar a quantidade de n�s da �rvore
	public int qtdNoArvore() {
		return qtdNoArvore(ptRaiz);

	}

	// Sobrecarga do M�todo qtdNoArvore com um par�metro(esta � a vers�o
	// recursiva do m�todo
	private int qtdNoArvore(NoArvore raiz) {
		if (raiz == null) {
			return 0;

		} else {
			int noEsq = qtdNoArvore(raiz.getEsquerdo());
			int noDirei = qtdNoArvore(raiz.getDireito());
			return noEsq + noDirei + 1;
		}

	}

	// M�todo que permite buscar um aluno na �rvore pela matricula
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

	// M�todo que permite remmover um aluno

	public NoArvore remover(int matricula) throws Exception {
		return remover(this.ptRaiz,matricula);
	}

	private NoArvore remover(NoArvore node, int matricula) throws Exception {
		if (this.ptRaiz == null) {
			throw new Exception("�rvore vazia");
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