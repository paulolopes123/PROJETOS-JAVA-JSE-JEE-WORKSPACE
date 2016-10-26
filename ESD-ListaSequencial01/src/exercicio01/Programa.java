package exercicio01;

import java.util.Scanner;

public class Programa {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner l = new Scanner(System.in);
		System.out.println("Digite o tamanho que a lista terá:");
		int tam = l.nextInt();
		ListaSequencial lista = new ListaSequencial(tam);
		
		lista.inserirOrdenado();
		lista.listar();

	}

}
