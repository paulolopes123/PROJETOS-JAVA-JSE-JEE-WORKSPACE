package controle;

import dominio.RecursaoFibonacci;

public class Programa {

	public static void main(String[] args) {

		RecursaoFibonacci f = new RecursaoFibonacci();
		
		//imprimir os 30 primeiros termos da sequencia de Fibonacci
		for(int i = 0 ; i < 30 ; i++){
			System.out.println(f.fibo(i));
			
			
		}
		
		
		
	}

}
