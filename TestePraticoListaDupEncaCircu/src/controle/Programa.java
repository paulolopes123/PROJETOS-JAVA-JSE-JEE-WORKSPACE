package controle;

import java.util.Scanner;

import dominio.Lista;

public class Programa {

	public static void main(String[] args) {

		Scanner entrada = new Scanner(System.in);
		Lista lista = new Lista();
		int num = 1;
		char resp = 'S';
		while (Character.toUpperCase(resp) == 'S') {
			
			//OBS: esta classe foi feita do zero pois não havia sido codificado nada nela.
			//Nesta classe acontece a chamada ao metodo inserirNaLista:
			//é pedido para o usuário digitar um nome enquanto ele quiser.
			
			
			
			System.out.println("Digite um nome para participar do jogo");
			String nome = entrada.next();
			lista.inserirNaLista(nome);

			System.out.println("Deseja inserir outro nome? S/N");
			resp = entrada.next().charAt(0);

		}

		while (num == 1) {
			
			// é pedido para que ele insira um valor inteiro enquanto ele quiser sendo que este valor 
			//devera ser maior que zero caso contrario nao executara o metodo sorteio
			//apos ser validado um valor digitado pelo usuario a chamada ao metodo sorteio
			//o jogo acabara quando somente restar um jogador na lista pois esse sera o vencedor.
			
			System.out.println("Digite um valor inteiro para o sorteio:");
			int res = entrada.nextInt();
			while(res < 1){
				System.out.println("Favor digite um valor inteiro acima de zero(0):");
				res = entrada.nextInt();
			}
			lista.sorteio(res);
			//System.out.println(lista.sorteio(res));
			System.out.println("Digite 1 para continuar a inserir valores para o sorteio:");
			num = entrada.nextInt();

		}

	}

}
