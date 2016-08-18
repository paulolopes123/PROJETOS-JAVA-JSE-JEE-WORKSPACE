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
		// vamos inserir valores na árvore enquanto o usuário desejar
		while (Character.toUpperCase(resp) == 'S') {
			System.out.print("Digite o nome do aluno:\n ");
			String nome = entrada.next();
			System.out.print("Digite o CR do aluno:\n ");
			int cr = entrada.nextInt();
			System.out.print("Digite a matrícula do aluno:\n ");
			int matric = entrada.nextInt();

			// vamos inserir o nó e verificar o sucesso da operação
			if (!arvore.insereNo(nome, cr, matric)) {
				System.out.println("Não foi possível inserir os dados da aluno.já contém um aluno com essa matrícula.");
			} else {
				System.out.println("Os dados do aluno foi inserido com sucesso ");
			}
			System.out.println("Deseja inserir novamente um aluno?(S/N)");
			resp = entrada.next().charAt(0);

		}
		// Informa a Altura da Árvore com os dados do aluno
		System.out.println("\nAltura da Árvore com os dados do aluno:");
		System.out.println(arvore.alturaArvore());
		// Informa a Quantidade de nós da árvore
		System.out.println("\nQuantidade de nós da Árvore:");
		System.out.println(arvore.qtdNoArvore());

		// vamos exibir os nós da árvore usando o percurso pré-ordem
		System.out.println("\nImprimindo turma utilizando o Percurso em pré-ordem com base na matrícula:");
		arvore.preOrdem();

		System.out.println("\n");

		// vamos exibir os nós da árvore usando o percurso em-ordem
		System.out.println("\nImprimindo turma utilizando o Percurso em-ordem com base na matrícula:");
		arvore.emOrdem();

		System.out.println("\n");

		// vamos exibir os nós da árvore usando o percurso pós-ordem
		System.out.println("\nImprimindo turma utilizando o Percurso em pós-ordem com base na matrícula:");
		arvore.posOrdem();

		System.out.println("\n");

		// vamos remover valores na árvore enquanto o usuário desejar
		while (Character.toUpperCase(resp) == 'S') {
			System.out.print("Digite a matricula do aluno para remoção:\n ");
			int matricula = entrada.nextInt();
			//aluno = arvore.remover(matricula);
			// arvore.remover(aluno);
			System.out.println("Deseja remover mais um aluno?(S/N)");
			resp = entrada.next().charAt(0);

		}

		// vamos buscar um aluno pela matricula enquanto o usuário desejar
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
