package ordenacao;

import java.util.Scanner;

public class BubbleSort {

	public static void main(String args[]) {
		int tam;
		int i;
		boolean trocou;
		Scanner in = new Scanner(System.in);
		System.out.println("Digite Quantidade de Elementos");
		tam=in.nextInt();
		int [] lista = new int[tam];
		for (i = 0; i < tam; i++) {
         lista[i]=in.nextInt();
		}
		int aux = 0;
		System.out.println("Elementos Lidos:");
		
		for (i = 0; i < tam; i++) {
			System.out.println(" " + lista[i]);
		}

		for (i = 0; i < tam; i++) {
			trocou =false;
			for (int j = 0; j < tam - 1; j++) {
				if (lista[j] > lista[j + 1]) {
					aux = lista[j];
					lista[j] = lista[j + 1];
					lista[j + 1] = aux;
					trocou = true;
				}
				
			}
			if(!trocou) break;
		}
		System.out.println("Elementos Ordenados:");
		for (i = 0; i < tam; i++) {
			System.out.println(" " + lista[i]);
		}
	}

}
