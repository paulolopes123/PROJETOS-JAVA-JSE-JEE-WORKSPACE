package exercicio02;

public class FilaSequencial {
	

	
		private int[] fila;
		private int inicio;
		private int fim;

		public FilaSequencial(int tamanho) {
			this.fila = new int[tamanho];
			this.inicio = -1;// a fila começa vazia
			this.fim = -1;// a fila começa vazia
		}

		
		public int getInicio() {
			return inicio;
		}


		public int getFim() {
			return fim;
		}

		public int getConteudoInicio() {
			return fila[inicio];
		}


		public int getConteudoFim() {
			return fila[fim];
		}
		
		// Verifica se a fila tah vazia
		public boolean tahVazia() {
			if (inicio == -1 && fim == -1)
				return true;
			else
				return false;
		}

		// Verifica se a fila tah cheia
		// atualizo o fim fazendo:
		// fim=(fim+1)mod tamanho
		public boolean estahCheia() {
			if (((fim + 1) % fila.length) == inicio)
				return true;
			else
				return false;
		}

		//Insere na fila se ela não estiver cheia
		public boolean insereNaFila(int valor) {
			if (!estahCheia()){
				fim = (fim + 1) % fila.length;//soh insiro pelo fim
				fila[fim] = valor;
				//testa se eh o primeiro elemento a ser inserido
				if (inicio==-1){
					inicio=fim;//ou inicio=fim;
				}
				return true;
			} else {
				return false;//não tem espaço para inserir
			}
		}

		//Remove da fila de esta não estiver vazia
		public boolean removeDaFila() {
			if (!tahVazia()){
				//se só há um elemento na fila, ela ficará vazia
				if (inicio==fim){
					inicio=-1;
					fim=-1;
				}
				else{
					inicio=(inicio+1)% fila.length;//soh removo pelo inicio
				}
				return true;
			}else{
				return false;//não tem ninguem para remover
			}

		}

		// imprimir a fila, restaurando-a após a impressão :-)
		public void imprimeFilaeRestaura() {

			System.out.print("Fila: ");  
			for (int i = 0; i <= fim; i++) {  
				System.out.print(fila[i] + " - ");  
			}  
			if (fim == -1) {  
				System.out.print("Vazia");  
			}
		}
	}


