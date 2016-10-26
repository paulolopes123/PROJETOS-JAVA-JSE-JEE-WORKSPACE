package exercicio04;

public class Principal {
	public static void main(String args[]) {
		FilaPrioridade fila = new FilaPrioridade(10);
		
		fila.inserir("P1", true);
		fila.inserir("P2", false);
		fila.inserir("P3", true);
		System.out.println("removido"+fila.remover());
		fila.inserir("P4", true);
		fila.inserir("P5", false);
		System.out.println("removido"+fila.remover());
		fila.inserir("P6", false);
		fila.inserir("P7", true);
		System.out.println("removido"+fila.remover());
		//fila.listarDecrescente();
		
		fila.listarCrescente();
		
	}
}
