package exercicio1;

public class Funcionario {

	private String nome;
	private int idade;
	private double salarioHora;
	private double horaTrabalhadaMes;

	public Funcionario(String nome, int idade) {
		this.nome = nome;
		this.idade = idade;
	}

	public Funcionario(String nome) {
		this(nome, 18);
	}

	/*
	 * public Funcionario(String nome) { this.nome = nome; this.idade = 18; }
	 */
	public String getNome() {
		return nome;
	}

	public int getIdade() {
		return idade;
	}

	public double getSalarioHora() {
		return salarioHora;
	}

	public void setSalarioHora(double salarioHora) {
		if (salarioHora >= 10 && salarioHora <= 200) {

			this.salarioHora = salarioHora;
		} else {
			System.out.println("Salário inválido");
		}

	}

	public double getHoraTrabalhadaMes() {
		return horaTrabalhadaMes;
	}

	public void setHoraTrabalhadaMes(double horaTrabalhadaMes) {
		if (horaTrabalhadaMes <= 160) {
			this.horaTrabalhadaMes = horaTrabalhadaMes;
		} else {
			System.out.println("horas Trabalhadas inválida");
		}
	}

	public void mostrarDados() {
		System.out.println("Dados do Funcionario:");
		System.out.println(getNome());
		System.out.println(getIdade());
		System.out.println(getSalarioHora());
		System.out.println(getHoraTrabalhadaMes());

	}

	public double calcularSalario() {
		double sal = this.salarioHora * this.horaTrabalhadaMes;
		return sal;

	}

}
