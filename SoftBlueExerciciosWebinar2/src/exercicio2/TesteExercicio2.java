package exercicio2;

import exercicio2.Analista;
import exercicio2.Gerente;

public class TesteExercicio2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Gerente f1 = new Gerente("José", 35);
		f1.setSalarioHora(1000);
		f1.setHoraTrabalhadaMes(160);
		f1.setValorBonus(100);
		f1.mostrarDados();
		f1.calculaSalario();

		Analista f2 = new Analista("Pedro", 28);
		f2.setSalarioHora(20);
		f2.setHoraTrabalhadaMes(160);
		f2.setSetor("compras");

		f2.mostrarDados();
		double salariof2 = f2.calcularSalario();
		System.out.println("Salário: R$ " + salariof2);

	}

}
