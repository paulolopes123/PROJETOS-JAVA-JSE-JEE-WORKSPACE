package controle;

import dominio.DadosException;
import face.JanelaConsultarProva;

public class CtrlConsultarProva {

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
	private JanelaConsultarProva jConsultarProva;

	//
	// M�TODOS
	//

	/**
	 * Construtor da classe CtrlElaborarProva
	 */

	public CtrlConsultarProva(CtrlPrograma p) throws ControleException, DadosException {
		// Guardo a refer�ncia para o controlador do programa
		this.ctrlPrg = p;
		// Iniciando o caso de uso
		this.iniciar();
	}

	public void iniciar() throws ControleException, DadosException {
		// Crio e abro a janela de duplica��o da prova
		this.jConsultarProva = new JanelaConsultarProva(this);

	}

	public void terminar() throws ControleException {

		// Fecho a janela
		this.jConsultarProva.setVisible(false);

	}

	/**
	 * 
	 * @param novo
	 * @throws ControleException
	 */

}
