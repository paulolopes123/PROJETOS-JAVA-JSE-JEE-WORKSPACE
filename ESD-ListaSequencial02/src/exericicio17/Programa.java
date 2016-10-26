package exericicio17;

import java.util.Scanner;

public class Programa {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner e = new Scanner(System.in);

		System.out.println("Digite um número:");
		int n = e.nextInt();
		ListaDivisores l = new ListaDivisores(n);
		System.out.println("Divisores do numero " + n);
		l.divisor(n);

	}

}
