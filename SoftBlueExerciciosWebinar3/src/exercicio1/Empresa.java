package exercicio1;

import java.util.ArrayList;
import java.util.List;


public class Empresa {

	private List<Funcionario> func = new ArrayList<Funcionario>();

	public void adicionaFuncionario(Funcionario funcionario) {
		func.add(funcionario);

	}

	public Funcionario procuraFuncionario(String nome) {
		for (Funcionario funci : func) {

			if (funci.getNome().equals(nome)) {

				return funci;
			}
		}
		return null;

	}

}
