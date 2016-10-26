package exercicio1;

public class DivisaoZero {

	public static void main(String[] args) {

		int x = 3;
		int y = 0;

		try {
			int r = x / y;
			System.out.println(r);

		} catch (ArithmeticException e) {
			System.out.println("Esta e uma divisao por zero!!");

		}

	}

}
