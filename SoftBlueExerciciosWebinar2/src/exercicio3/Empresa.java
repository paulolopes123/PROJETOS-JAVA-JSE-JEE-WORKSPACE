package exercicio3;

import java.util.ArrayList;
import java.util.List;

import exercicio1.Funcionario;
import exercicio2.Analista;
import exercicio2.Gerente;

public class Empresa {
	public static final int FUNCIONARIO_GERENTE = 1;
	public static final int FUNCIONARIO_ANALISTA = 2;
	private List<Funcionario> funcionario = new ArrayList<Funcionario>();

	public static Funcionario criarFuncionario(int tipo,String nome, int idade) {
		Funcionario func = null;
		if (tipo == FUNCIONARIO_GERENTE) {
			func = new Gerente(nome, idade);

		} else if (tipo == FUNCIONARIO_ANALISTA) {

			func = new Analista(nome, idade);
		}
		return func;

	}

	public void adicionarFuncionario(Funcionario func) {

		funcionario.add(func);
	}

	public Funcionario procuraFunc(String nome) {
		for (Funcionario f : funcionario) {
			if (f.getNome().equals(nome)) {
				return f;
			}

		}
		return null;

	}

}
