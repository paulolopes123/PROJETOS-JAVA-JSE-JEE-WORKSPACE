package dominio;

public class ListaDupEncCirc {
	private Pessoa ptL, ptAux;

	public ListaDupEncCirc() {
		this.ptL = null;
	}

	public void insereParticipante(String nome) {
		// cria o objeto novaPessoa
		Pessoa p = new Pessoa(nome);
		// paticularidades da inserção:
		// 1 - Se novaPessoa é o primeiro objeto da lista
		if (ptL == null) {
			// 1.1 - ptL deve ser atualizado
			// 1.2 - ptL vai referenciar a si próprio
			p.setAnt(ptL);
			p.setProx(ptL);
			ptL = p;
			ptAux = ptL;

		}

		// 2 - Vamos combinar inserir novaPessoa sempre após ptL
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

	// Retorna null se ainda não há vencedor após a remoção do
	// k-ésimo nó ou o endereço do vencedor
	public Pessoa removeKesimoParticipante(int k) {
		// Verificar se lista não vazia - Só continua se não vazia

		// Verificar se só tem um na lista. Se sim, este é o vencedor
		return ptL;

		/*
		 * Se existir mais de um participante na lista: 1- Percorrer a lista até
		 * contar k nós (mesmo que repita o nó) 1.1 - Começa contanto 1 de ptL e
		 * continua a contagem até o k-ésimo nó (pode usar for ou do...while)
		 * 1.2 - Achei o k-esimo nó para remover: - Se for ptL, não esquecer de
		 * atualizá-lo - Se não for ptL, remover o nó da lista - retornar null
		 * (return null;//não há vencedor)
		 */
	}

}
