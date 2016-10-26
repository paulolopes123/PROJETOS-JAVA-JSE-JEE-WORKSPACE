package modelo;

public class Fila extends ListaEnc {

	@Override
	public void remover() {
		ini = ini.prox;
	}
	
	@Override
	public void remover(int valor) {
		System.out.println("Nao suportado");
	}
}
