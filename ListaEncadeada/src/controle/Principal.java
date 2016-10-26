package controle;

import modelo.ListaEnc;

public class Principal {

	public static void main(String[] args) {
		ListaEnc lista = new ListaEnc();
		lista.inserir(0);
		lista.inserir(1);
		lista.inserir(4);
		lista.inserir(3);
		lista.listar();
		System.out.println();
		lista.ImprimirInvertido(lista.ini);
		// lista.inverter();
		// lista.listar();
		 lista.inserir(7);
		 lista.inserir(9);
		 lista.inserir(5);
		 lista.inserir(8);
		 lista.listar();
		 System.out.println();
		 lista.ImprimirInvertido(lista.ini);
		// lista.listar();
		// lista.inverter();
		// lista.listar();
	}

}
