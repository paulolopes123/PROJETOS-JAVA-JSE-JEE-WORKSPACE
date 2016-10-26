package exercicio1;

import exercicio2.Gerente;

public class TesteExercicio1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Funcionario func = new Funcionario("paulo", 20);
		Funcionario funci = new Funcionario("lopes");

		func.setSalarioHora(399);

		func.setHoraTrabalhadaMes(15);

		func.mostrarDados();

		funci.setSalarioHora(30);

		funci.setHoraTrabalhadaMes(55);

		funci.mostrarDados();
	}

}
