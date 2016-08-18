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

	// Método de inserção corrigido
	public boolean inserirNaLista(String nome) {
		Pessoa novo = new Pessoa(nome);
		if (tahVazia()) {
			// caso seja o primeiro nó
			novo.setAnt(ptLista);
			novo.setProx(ptLista);
			ptLista = novo;
		} else {
			aux = ptLista;
			// pendura o nó
			novo.setAnt(aux);
			aux.setProx(novo);

			// atualiza as referênciais

			novo.setProx(ptLista);
			ptLista.setAnt(novo);

		}
		return true;
	}

	/*
	 * // de acordo com os ajustes feitos nesse método errado abaixo e nos
	 * testes realizados segue as correções: em vez de a variável aux receber o
	 * ptLista dentro do if essa variável deveria ter recebido o ptLista dentro
	 * do else assim como o novo nó ter setado o seu ant como aux e o Prox do aux como novo e assim pen
	 * durando o nó para posteriormente atualizar as referencias setando o Prox do novo nó passando como
	 * parametro o ptlista e setando o ant do ptlista com o novo nó. 
	 *  conforme descrito no codigo corrigido acima.
	 * 
	 * 
	 * 
	 * 
	 * Método errado
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

	// Método de remoção corrigido

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
				return "não temos um vencedor!";
			else
				return "vencedor: " + ptLista.getNome();

		} else {
			return "já temos um vencedor: " + aux.getNome();
	
		}
	}

	/*
	 * segue as correçoes que deveria ter sido feitas neste método de remocao que esta abaixo: neste
	 * deveria ter colocado um contador que varrese até o numero que foi passado
	 * como parametro e que nesse intervalo a variável aux recebesse seu proximo
	 * e caso fosse igual a ptLista atualizava as referenciais e caso
	 * ptLista.Prox fosse diferente de ptLista não haveria vencedor e o jogo
	 * continuava e caso ptLista.Prox fosse igual a ptLista retornava o
	 * vencedor. caso no começo do metodo a variável aux.getProx fosse igual a
	 * aux retornaria o vencedor porque nesse caso só haveria um jogoador na
	 * lista.
	 * 
	 * 
	 * Acima está o método de remocao corrigido.
	 * 
	 * 
	 * 
	 * 
	 * 
	 * //Método errado 
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
