package programa;

import java.util.Scanner;

import model.ListaEncadeada;
import model.Noh;

public class Programa {

	public static void main(String[] args) {

		Scanner out = new Scanner(System.in);
		ListaEncadeada l = new ListaEncadeada();
		int sn = 1;
		String nome, resultado;
		
		while (sn == 1) {
			System.out.println("Insira um nome:");
			nome = out.next();
			l.inserir(nome);
			System.out.println("Digite 1 para continuar inserindo");
			sn = out.nextInt();
		}
		
		sn = 1;
		while (sn == 1) {
			System.out.println("Digite um número inteiro para o sorteio");
			resultado = l.contagem(out.nextInt());
			System.out.println(resultado);
			System.out.println("Digite 1 para continuar inserindo");
			sn = out.nextInt();
		}

	}
}