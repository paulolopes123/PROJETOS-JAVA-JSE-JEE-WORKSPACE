package exercicio2;
import java.util.ArrayList;
import java.util.List;

public class Numeros {
	
	public static void main(String[] args) {
	List<Integer> numeros = new ArrayList<Integer>();
		numeros.add(8);
		numeros.add(3);
		numeros.add(1);
		numeros.add(4);
		numeros.add(2);
		numeros.add(6);
		numeros.add(0);
		numeros.add(5);
		numeros.add(7);
		numeros.add(9);
		
		List<Integer> pares = new ArrayList<>();
		List<Integer> impares = new ArrayList<>();
		
		for (int numero : numeros) {
			if (numero % 2 == 0) {
				pares.add(numero);
			} else {
				impares.add(numero);
			}
		}
		
		System.out.println("Pares: " + pares);
		System.out.println("Ímpares: " + impares);
	}
}
