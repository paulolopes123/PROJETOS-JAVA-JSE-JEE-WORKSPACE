package dominio;

public class ListaDupEncCirc {
	private Pessoa ptL, ptAux;

	public ListaDupEncCirc() {
		this.ptL = null;
	}

	public void insereParticipante(String nome) {
		// cria o objeto novaPessoa
		Pessoa p = new Pessoa(nome);
		// paticularidades da inser��o:
		// 1 - Se novaPessoa � o primeiro objeto da lista
		if (ptL == null) {
			// 1.1 - ptL deve ser atualizado
			// 1.2 - ptL vai referenciar a si pr�prio
			p.setAnt(ptL);
			p.setProx(ptL);
			ptL = p;
			ptAux = ptL;

		}

		// 2 - Vamos combinar inserir novaPessoa sempre ap�s ptL
		else {

			// Pendura o objeto novaPessoa na lista atual
			p.setProx(ptAux.getProx());
			p.setAnt(ptAux);

			// Faz com que a lista "acesse" o objeto novaPessoa (insere
			// efetivamente o objeto na lista)
			ptAux.setProx(ptAux);
			p.getProx().setAnt(p);

		}
	}

	// Retorna null se ainda n�o h� vencedor ap�s a remo��o do
	// k-�simo n� ou o endere�o do vencedor
	public Pessoa removeKesimoParticipante(int k) {
		// Verificar se lista n�o vazia - S� continua se n�o vazia

		// Verificar se s� tem um na lista. Se sim, este � o vencedor
		return ptL;

		/*
		 * Se existir mais de um participante na lista: 1- Percorrer a lista at�
		 * contar k n�s (mesmo que repita o n�) 1.1 - Come�a contanto 1 de ptL e
		 * continua a contagem at� o k-�simo n� (pode usar for ou do...while)
		 * 1.2 - Achei o k-esimo n� para remover: - Se for ptL, n�o esquecer de
		 * atualiz�-lo - Se n�o for ptL, remover o n� da lista - retornar null
		 * (return null;//n�o h� vencedor)
		 */
	}

}
