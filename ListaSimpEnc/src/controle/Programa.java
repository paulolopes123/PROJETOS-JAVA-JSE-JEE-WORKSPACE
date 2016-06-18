package controle;

import java.util.Scanner;

import dominio.Lista;
import dominio.Nodo;

public class Programa {

	public static void main(String[] args) {

		Lista listaSimpEnc = new Lista();

		listaSimpEnc.addNoInicioLista(5);
		listaSimpEnc.addNoInicioLista(4);
		listaSimpEnc.addNoInicioLista(3);
		listaSimpEnc.addNoInicioLista(2);
		listaSimpEnc.addNoInicioLista(1);

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
			System.out.println("N� encontrado!");
		}
		else {
			System.out.println("N� n�o encontrado!");
		}

		boolean resp = listaSimpEnc.removeNo(1);
		if (resp == true) {
			System.out.println("N� removido com sucesso!");
		}
		else {
			System.out.println("N�o foi poss�vel remover o n�!");
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
