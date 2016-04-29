package dominio;

public class AlunoLista {
	private Aluno primeiro, ultimo;

	// inicializando uma lista vazia
	public AlunoLista() {
		criaLista();
	}

	public void criaLista() {

		primeiro = ultimo = null;
	}

	// verifica se a lista está vazia
	public boolean listaVazia() {
		if (primeiro == null && ultimo == null)

			return true;
		return false;

	}

	// insere no começo da lista - lista circular
	public void insereNoComeco(int matr) {

		Aluno aluno = new Aluno("paulo", 7, matr);
		aluno.setMatr(matr);
		aluno.setProx(null);
		if (listaVazia()) {

			aluno.setProx(primeiro);
			primeiro = aluno;
			aluno.setProx(primeiro);

		} else {

			primeiro.setProx(aluno);
			primeiro = aluno;
			aluno.setProx(primeiro);
		}

	}

	// insere no final da lista
	public void insereNoFim(int matr) {
		Aluno aluno = new Aluno("paulo", 7, matr);
		aluno.setMatr(matr);
		aluno.setProx(null);
		if (listaVazia()) {
			aluno.setProx(ultimo);
			ultimo = aluno;
			aluno.setProx(ultimo);

		} else {
			ultimo.setProx(aluno);
			ultimo = aluno;
			aluno.setProx(ultimo);

		}
	}

	// remove um aluno da lista
	public void removeAluno(Aluno aluno) {
		Aluno ante = null;
		Aluno alu = primeiro;

		while (alu != null && alu.getProx() != aluno) {
			ante = alu;
			alu.getProx();

			if (aluno == null) {
				return;
			}

			if (ante == null) {
				primeiro = aluno.getProx();
			} else {
				ante.setProx(aluno.getProx());
			}
		}

	}

	// busca um aluno na lista pela matricula
	public Aluno buscaAluno(int matr) {
		for (Aluno aluno = primeiro; aluno != null; aluno = aluno.getProx()) {
			if (aluno.getMatr() == matr) {

				return aluno;
			}
		}

		return null;

	}

	// imprime lista
	public void imprimeLista() {
		for (Aluno aluno = primeiro; aluno != null; aluno = aluno.getProx()) {
			System.out.println("Lista " + aluno.getNome());

		}

	}

}
