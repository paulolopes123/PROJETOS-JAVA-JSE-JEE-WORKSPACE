package controle;

import java.util.Scanner;

import dominio.ArvoreBinariaBusca;

/*
 * Escreva uma aplica��o Java que contenha uma �rvore bin�ria de busca cujos n�s guardar�o,
 *  al�m das refer�ncias para o filho esquerdo e o filho direito, apenas um valor inteiro.
 *   Forne�a um m�todo inserir() que permitir� inserir os valores na �rvore. Em seguida forne�a
 *    um m�todo recursivo que permitir� fazer a travessia in-order da �rvore.
 * 
 * 
 * 
 */
public class ProgramaPrincipal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner entrada = new Scanner(System.in);
		// vamos criar um novo objeto da classe ArvoreBinariaBusca
		ArvoreBinariaBusca arvore = new ArvoreBinariaBusca();
		// vamos inserir 5 valores na �rvore
		for (int i = 0; i <= 4; i++) {
			System.out.println("Informe um valor inteiro:");
			int valor = entrada.nextInt();
			// vamos inserir o n� e verificar o sucesso da opera��o
			if (!arvore.inserir(valor)) {
				System.out.println("N�o foi poss�vel inserir. Um elemento j� cont�m este valor.");

			} else {
				System.out.println("Valor inserido com sucesso!!");

			}
		}

		// vamos exibir os n�s da �rvore usando o percurso em-ordem
		System.out.println("\nPercurso em-ordem");
		arvore.emOrdem();
		System.out.println("\n");

		// vamos exibir os n�s da �rvore usando o percurso pre-ordem
		System.out.println("\nPercurso pre-ordem");
		arvore.preOrdem();
		System.out.println("\n");

		// vamos exibir os n�s da �rvore usando o percurso pos-ordem
		System.out.println("\nPercurso pos-ordem");
		arvore.posOrdem();
		System.out.println("\n");

	}

}
