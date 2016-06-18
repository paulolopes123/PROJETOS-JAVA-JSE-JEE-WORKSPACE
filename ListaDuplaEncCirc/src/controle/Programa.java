package controle;

import dominio.ListaDupCirc;

public class Programa {

	public static void main(String[] args) {

		ListaDupCirc lstDupCirc = new ListaDupCirc();

	/*
	 	//Insere os nós na lista de forma não ordenada
		lstDupCirc.inserirNaoOrdenada("Ana", 29, "Feminino");
		lstDupCirc.inserirNaoOrdenada("Iury", 20, "Masculino");
		lstDupCirc.inserirNaoOrdenada("Vitor", 23, "Masculino");
	*/
		//Insere os nós na lista de forma ordenada crescente
		lstDupCirc.inserirOrdenadaCresc("Vitor", 23, "M");
		lstDupCirc.inserirOrdenadaCresc("Douglas", 30, "M");
		lstDupCirc.inserirOrdenadaCresc("Ana", 23, "F");
		lstDupCirc.inserirOrdenadaCresc("Zelia", 33, "F");
		lstDupCirc.inserirOrdenadaCresc("Ana", 23, "M");
		
		lstDupCirc.listar();
	}

}
