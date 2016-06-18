package dominio;

public class Pessoa {

		private Pessoa ant;
		private String nome;
		private Pessoa prox;
		
		public Pessoa(String novoNome){
			this.nome=novoNome;
			this.ant = null;
			this.prox = null;
		}

		public Pessoa getAnt() {
			return ant;
		}

		public void setAnt(Pessoa ant) {
			this.ant = ant;
		}

		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public Pessoa getProx() {
			return prox;
		}

		public void setProx(Pessoa prox) {
			this.prox = prox;
		}
		
		
}
