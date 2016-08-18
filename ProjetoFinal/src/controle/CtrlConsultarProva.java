package controle;

import dominio.DadosException;
import face.JanelaConsultarProva;

public class CtrlConsultarProva {

	//
	// ATRIBUTOS
	//

	/**
	 * Referência para o controlador do programa.
	 */
	private final CtrlPrograma ctrlPrg;

	/**
	 * Referência para a janela alterar Prova
	 */
	private JanelaConsultarProva jConsultarProva;

	//
	// MÉTODOS
	//

	/**
	 * Construtor da classe CtrlElaborarProva
	 */

	public CtrlConsultarProva(CtrlPrograma p) throws ControleException, DadosException {
		// Guardo a referência para o controlador do programa
		this.ctrlPrg = p;
		// Iniciando o caso de uso
		this.iniciar();
	}

	public void iniciar() throws ControleException, DadosException {
		// Crio e abro a janela de duplicação da prova
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
