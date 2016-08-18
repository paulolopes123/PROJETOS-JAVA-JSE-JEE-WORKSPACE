package controle;

import java.util.Scanner;

import dominio.ArvoreBinariaBusca;
import dominio.NoArvore;

public class Programa {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner entrada = new Scanner(System.in);
		NoArvore aluno;

		// vamos criar um novo objeto da classe ArvoreBinariaBusca
		ArvoreBinariaBusca arvore = new ArvoreBinariaBusca();
		char resp = 'S';
		// vamos inserir valores na �rvore enquanto o usu�rio desejar
		while (Character.toUpperCase(resp) == 'S') {
			System.out.print("Digite o nome do aluno:\n ");
			String nome = entrada.next();
			System.out.print("Digite o CR do aluno:\n ");
			int cr = entrada.nextInt();
			System.out.print("Digite a matr�cula do aluno:\n ");
			int matric = entrada.nextInt();

			// vamos inserir o n� e verificar o sucesso da opera��o
			if (!arvore.insereNo(nome, cr, matric)) {
				System.out.println("N�o foi poss�vel inserir os dados da aluno.j� cont�m um aluno com essa matr�cula.");
			} else {
				System.out.println("Os dados do aluno foi inserido com sucesso ");
			}
			System.out.println("Deseja inserir novamente um aluno?(S/N)");
			resp = entrada.next().charAt(0);

		}
		// Informa a Altura da �rvore com os dados do aluno
		System.out.println("\nAltura da �rvore com os dados do aluno:");
		System.out.println(arvore.alturaArvore());
		// Informa a Quantidade de n�s da �rvore
		System.out.println("\nQuantidade de n�s da �rvore:");
		System.out.println(arvore.qtdNoArvore());

		// vamos exibir os n�s da �rvore usando o percurso pr�-ordem
		System.out.println("\nImprimindo turma utilizando o Percurso em pr�-ordem com base na matr�cula:");
		arvore.preOrdem();

		System.out.println("\n");

		// vamos exibir os n�s da �rvore usando o percurso em-ordem
		System.out.println("\nImprimindo turma utilizando o Percurso em-ordem com base na matr�cula:");
		arvore.emOrdem();

		System.out.println("\n");

		// vamos exibir os n�s da �rvore usando o percurso p�s-ordem
		System.out.println("\nImprimindo turma utilizando o Percurso em p�s-ordem com base na matr�cula:");
		arvore.posOrdem();

		System.out.println("\n");

		// vamos remover valores na �rvore enquanto o usu�rio desejar
		while (Character.toUpperCase(resp) == 'S') {
			System.out.print("Digite a matricula do aluno para remo��o:\n ");
			int matricula = entrada.nextInt();
			//aluno = arvore.remover(matricula);
			// arvore.remover(aluno);
			System.out.println("Deseja remover mais um aluno?(S/N)");
			resp = entrada.next().charAt(0);

		}

		// vamos buscar um aluno pela matricula enquanto o usu�rio desejar
		while (Character.toUpperCase(resp) == 'S') {
			System.out.print("Digite a matricula do aluno para busca:\n ");
			int matricula = entrada.nextInt();
			arvore.buscaAluno(matricula);
			// arvore.remover(aluno);
			System.out.println("Deseja buscar mais um aluno?(S/N)");
			resp = entrada.next().charAt(0);

		}

	}

}
