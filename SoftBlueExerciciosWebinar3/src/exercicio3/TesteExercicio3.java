package exercicio3;

public class TesteExercicio3 {

	public static void main(String[] args) {

		Leao leao = new Leao();
		Papagaio papagaio = new Papagaio();

		Veterinario veterinario = new Veterinario();

		veterinario.Curar(leao);
		veterinario.Curar(papagaio);

	}
}