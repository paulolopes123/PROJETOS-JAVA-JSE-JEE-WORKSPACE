package controle.dao;

import javax.persistence.Persistence;

public class CriaTabelas {

	private CtrlFormulario ctrlFormulario;

	public static void main(String[] args) {
		Persistence.createEntityManagerFactory("banco");
		CriaTabelas cria = new CriaTabelas();
		// cria.iniciar();
	}

	public CriaTabelas() {

		this.iniciar();
	}

	public void iniciar() {
		this.iniciarCadastro();

	}

	public void iniciarCadastro() {
		this.ctrlFormulario = new CtrlFormulario(this);

	}

}
