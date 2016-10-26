package exercicio3;

import exercicio2.Analista;
import exercicio2.Gerente;

public class TesteExercicio3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Gerente f1 = (Gerente) Empresa.criarFuncionario(Empresa.FUNCIONARIO_GERENTE,"Paulo", 25);
		f1.setSalarioHora(100);
		f1.setHoraTrabalhadaMes(160);
		f1.setValorBonus(1000);

		f1.mostrarDados();
		double salariof1 = f1.calculaSalario();
		System.out.println("Salário: R$ " + salariof1);

		System.out.println();

		Analista f2 = (Analista) Empresa.criarFuncionario(Empresa.FUNCIONARIO_ANALISTA, "Pedro", 28);
		f2.setSalarioHora(1000);
		f2.setHoraTrabalhadaMes(160);
		f2.setSetor("compras");

		f2.mostrarDados();
		double salariof2 = f2.calcularSalario();
		System.out.println("Salário: R$ " + salariof2);

	}

}
