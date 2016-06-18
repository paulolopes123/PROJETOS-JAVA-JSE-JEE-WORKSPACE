package programa;

import model.Lista;
import model.Pessoa;

public class Programa {

	public static void main(String[] args) {

		Lista l = new Lista();
		l.inserir("paulo", 27, "masc");
		l.inserir("souza", 29, "masc");
		l.inserir("lopes", 28, "masc");
		l.inserir("roberto", 37, "masc");

		// l.remover(p2);
		l.listar();
	}
}
