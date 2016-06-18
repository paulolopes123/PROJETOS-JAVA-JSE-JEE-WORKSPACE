package dominio;

public class RecursaoFibonacci {

	public int fibo(int i) {
		if (i < 2) {

			return i;
		} else {
			return fibo(i - 1) + fibo(i - 2);

		}

	}

}
