package dominio;

public class Lista {

	private Pessoa ptLista, aux;

	public Lista() {

		ptLista = null;
	}

	public boolean tahVazia() {

		if (ptLista == null)
			return true;
		return false;

	}

	// M�todo de inser��o corrigido
	public boolean inserirNaLista(String nome) {
		Pessoa novo = new Pessoa(nome);
		if (tahVazia()) {
			// caso seja o primeiro n�
			novo.setAnt(ptLista);
			novo.setProx(ptLista);
			ptLista = novo;
		} else {
			aux = ptLista;
			// pendura o n�
			novo.setAnt(aux);
			aux.setProx(novo);

			// atualiza as refer�nciais

			novo.setProx(ptLista);
			ptLista.setAnt(novo);

		}
		return true;
	}

	/*
	 * // de acordo com os ajustes feitos nesse m�todo errado abaixo e nos
	 * testes realizados segue as corre��es: em vez de a vari�vel aux receber o
	 * ptLista dentro do if essa vari�vel deveria ter recebido o ptLista dentro
	 * do else assim como o novo n� ter setado o seu ant como aux e o Prox do aux como novo e assim pen
	 * durando o n� para posteriormente atualizar as referencias setando o Prox do novo n� passando como
	 * parametro o ptlista e setando o ant do ptlista com o novo n�. 
	 *  conforme descrito no codigo corrigido acima.
	 * 
	 * 
	 * 
	 * 
	 * M�todo errado
	 * 
	 * public void inserirNaLista(String nome) { 
	 * Pessoa novo = new Pessoa(nome);
	 * if (tahVazia()) { if (ptLista == null) {
	 *  novo.setAnt(ptLista);
	 * novo.setProx(ptLista);
	 *  ptLista = novo; 
	 *  aux = ptLista; 
	 *  qtd++; 
	 *  } else {
	 * novo.setProx(aux.getProx());
	 *  novo.setAnt(aux);
	 *aux.setProx(aux);
	 * novo.getProx().setAnt(novo);
	 * qtd++; }
	 * 
	 * }
	 * 
	 * }
	 */

	// M�todo de remo��o corrigido

	public String sorteio(int numero) {

		Pessoa aux = ptLista;
		if (aux.getProx() != aux) {

			for (int i = 0; i < numero; i++) {
				if (i != 0)
					aux = aux.getProx();
			}

			if (aux == ptLista) {
				ptLista = aux.getProx();
				aux.getAnt().setProx(aux.getProx());
			} else
				aux.getAnt().setProx(aux.getProx());
			aux.getProx().setAnt(aux.getAnt());

			if (ptLista.getProx() != ptLista)
				return "n�o temos um vencedor!";
			else
				return "vencedor: " + ptLista.getNome();

		} else {
			return "j� temos um vencedor: " + aux.getNome();
	
		}
	}

	/*
	 * segue as corre�oes que deveria ter sido feitas neste m�todo de remocao que esta abaixo: neste
	 * deveria ter colocado um contador que varrese at� o numero que foi passado
	 * como parametro e que nesse intervalo a vari�vel aux recebesse seu proximo
	 * e caso fosse igual a ptLista atualizava as referenciais e caso
	 * ptLista.Prox fosse diferente de ptLista n�o haveria vencedor e o jogo
	 * continuava e caso ptLista.Prox fosse igual a ptLista retornava o
	 * vencedor. caso no come�o do metodo a vari�vel aux.getProx fosse igual a
	 * aux retornaria o vencedor porque nesse caso s� haveria um jogoador na
	 * lista.
	 * 
	 * 
	 * Acima est� o m�todo de remocao corrigido.
	 * 
	 * 
	 * 
	 * 
	 * 
	 * //M�todo errado 
	 * public Pessoa removeNo(int info) {
	 *  aux = ptLista;
	 *   while(aux != null) { 
	 *   if (aux.getProx() == null) { 
	 *   return aux;
	 * 
	 * } else {
	 * 
	 * if (info == qtd) { 
	 * // return aux; 
	 * aux.getProx().setAnt(aux.getAnt());
	 * aux.getAnt().setProx(aux.getProx());
	 * 
	 * }
	 * 
	 * }
	 * 
	 * aux = aux.getProx(); }
	 * 
	 * return null;
	 * 
	 * }
	 */

}
