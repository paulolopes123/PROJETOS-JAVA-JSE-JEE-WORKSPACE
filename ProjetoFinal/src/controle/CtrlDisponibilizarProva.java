package controle;

import controle.CtrlAlterarProva.Status;
import dominio.DadosException;
import dominio.Prova;
import face.JanelaAlterarProva;
import face.JanelaDisponibilizarProva;
import face.JanelaExcluirProva;
import face.JanelaExcluirQuestao;
import face.JanelaIncluirQuestaoAlteracao;

public class CtrlDisponibilizarProva {

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
	private JanelaDisponibilizarProva jDisponibilizarProva;

	//
	// M�TODOS
	//

	/**
	 * Construtor da classe CtrlElaborarProva
	 */

	public CtrlDisponibilizarProva(CtrlPrograma p) throws ControleException, DadosException {
		// Guardo a refer�ncia para o controlador do programa
		this.ctrlPrg = p;
		// Iniciando o caso de uso
		this.iniciar();
	}

	public void iniciar() throws ControleException, DadosException {
		// Crio e abro a janela de disponibiliza��o da prova
		this.jDisponibilizarProva = new JanelaDisponibilizarProva(this);

	}

	public void terminar() throws ControleException {

		// Fecho a janela
		this.jDisponibilizarProva.setVisible(false);

	}

	/**
	 * 
	 * @param novo
	 * @throws ControleException
	 */

}
