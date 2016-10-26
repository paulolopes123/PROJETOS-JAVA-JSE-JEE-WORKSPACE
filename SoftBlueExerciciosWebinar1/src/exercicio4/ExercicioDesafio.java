package exercicio4;

import java.util.Scanner;

public class ExercicioDesafio {

	public static void main(String[] args) {
		Scanner e = new Scanner(System.in);

		System.out.println("Informe o valor que deseja investir:");
		double v = e.nextDouble();
		System.out.println("Informe o Prazo(Em Meses) que deseja saber o valor disponível:");
		double p = e.nextDouble();

		double m = v * Math.pow(1 + 0.01, p);

		System.out.println("Seu valor será de:\n " + m);

	}

}
