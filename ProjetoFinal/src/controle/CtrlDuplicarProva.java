package controle;

import dominio.DadosException;
import face.JanelaDuplicarProva;

public class CtrlDuplicarProva {

	//
	// ATRIBUTOS
	//

	/**
	 * Refer�ncia para o controlador do programa.
	 */
	private final CtrlPrograma ctrlPrg;

	/**
	 * Refer�ncia para a janela alterar Prova
	 */
	private JanelaDuplicarProva jDuplicarProva;

	//
	// M�TODOS
	//

	/**
	 * Construtor da classe CtrlElaborarProva
	 */

	public CtrlDuplicarProva(CtrlPrograma p) throws ControleException, DadosException {
		// Guardo a refer�ncia para o controlador do programa
		this.ctrlPrg = p;
		// Iniciando o caso de uso
		this.iniciar();
	}

	public void iniciar() throws ControleException, DadosException {
		// Crio e abro a janela de duplica��o da prova
		this.jDuplicarProva = new JanelaDuplicarProva(this);

	}

	public void terminar() throws ControleException {

		// Fecho a janela
		this.jDuplicarProva.setVisible(false);

	}

	/**
	 * 
	 * @param novo
	 * @throws ControleException
	 */

}
