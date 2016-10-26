package exercicio02;

public class FilaSequencial {
	

	
		private int[] fila;
		private int inicio;
		private int fim;

		public FilaSequencial(int tamanho) {
			this.fila = new int[tamanho];
			this.inicio = -1;// a fila come�a vazia
			this.fim = -1;// a fila come�a vazia
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

		//Insere na fila se ela n�o estiver cheia
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
				return false;//n�o tem espa�o para inserir
			}
		}

		//Remove da fila de esta n�o estiver vazia
		public boolean removeDaFila() {
			if (!tahVazia()){
				//se s� h� um elemento na fila, ela ficar� vazia
				if (inicio==fim){
					inicio=-1;
					fim=-1;
				}
				else{
					inicio=(inicio+1)% fila.length;//soh removo pelo inicio
				}
				return true;
			}else{
				return false;//n�o tem ninguem para remover
			}

		}

		// imprimir a fila, restaurando-a ap�s a impress�o :-)
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


