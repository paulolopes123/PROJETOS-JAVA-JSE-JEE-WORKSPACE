package controle;

import java.util.Scanner;

import dominio.ListaCircular;
import dominio.Nodo;

public class Programa {

	public static void main(String[] args) {

		ListaCircular listaSimpEnc = new ListaCircular();

		listaSimpEnc.addNoLista(5);
		listaSimpEnc.addNoLista(4);
		listaSimpEnc.addNoLista(3);
		listaSimpEnc.addNoLista(2);
		listaSimpEnc.addNoLista(1);

		Nodo ptAux = listaSimpEnc.getPtLista();

		while(ptAux != null) {
			System.out.println(ptAux.getInfo());
			ptAux = ptAux.getProx();		
		}

		Scanner input = new Scanner(System.in);

		System.out.println("Qual valor desejas buscar? ");
		int valor = input.nextInt();

		Nodo noAux = listaSimpEnc.buscaNo(valor);

		if (noAux != null) {
			System.out.println("Nó encontrado!");
		}
		else {
			System.out.println("Nó não encontrado!");
		}

		boolean resp = listaSimpEnc.removeNo(1);
		if (resp == true) {
			System.out.println("Nó removido com sucesso!");
		}
		else {
			System.out.println("Não foi possível remover o nó!");
		}
		resp = listaSimpEnc.removeNo(5);
		resp = listaSimpEnc.removeNo(3);		

		ptAux = listaSimpEnc.getPtLista();

		while(ptAux != null) {
			System.out.println(ptAux.getInfo());
			ptAux = ptAux.getProx();		
		}

	}

}
