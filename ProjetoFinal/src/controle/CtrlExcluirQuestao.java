package controle;

import controle.CtrlAlterarProva.Status;
import dominio.DadosException;
import dominio.Prova;
import face.JanelaAlterarProva;
import face.JanelaExcluirProva;
import face.JanelaExcluirQuestao;
import face.JanelaIncluirQuestaoAlteracao;

public class CtrlExcluirQuestao {

	//
	// ATRIBUTOS
	//
	public enum Status {
		EmExclusao, Excluida;

		public static void validarTransicaoStatus(Status anterior, Status novo) throws ControleException {
			if (anterior == null && novo == EmExclusao || anterior == EmExclusao && novo == Excluida
					|| anterior == Excluida)
				return;
			throw new ControleException(new ErroDeControle(1, "N�o se pode sair do estado "
					+ (anterior == null ? "NULO" : anterior) + " e ir para o estado " + novo));
		}
	}

	/**
	 * Refer�ncia para o controlador do programa.
	 */
	private final CtrlPrograma ctrlPrg;

	/**
	 * Refer�ncia para a janela alterar Prova
	 */
	private JanelaExcluirQuestao jExcluirQuestao;

	/**
	 * Refer�ncia para o objeto Prova sendo manipulado
	 */
	private Prova atual;

	/**
	 * Atributo que indica qual opera��o est� em curso
	 */
	private Status status;

	//
	// M�TODOS
	//

	/**
	 * Construtor da classe CtrlElaborarProva
	 */

	public CtrlExcluirQuestao(CtrlPrograma p) throws ControleException, DadosException {
		// Guardo a refer�ncia para o controlador do programa
		this.ctrlPrg = p;
		// Iniciando o caso de uso
		this.iniciar();
	}

	public void iniciar() throws ControleException, DadosException {
		// Crio e abro a janela de exclus�o da quest�o
		this.jExcluirQuestao = new JanelaExcluirQuestao(this);

		// N�o h� Prova em manipula��o
		this.atual = null;
		// Informo que o controlador de caso de uso est� dispon�vel
		this.setStatus(Status.EmExclusao);
	}

	public void terminar() throws ControleException {
		if (this.status == Status.Excluida)
			return;
		// N�o h� Prova em manipula��o
		this.atual = null;
		// Fecho a janela
		this.jExcluirQuestao.setVisible(false);
		// Informo que o controlador est� encerrado
		this.setStatus(Status.Excluida);
	}

	public Status getStatus() {
		return this.status;
	}

	/**
	 * 
	 * @param novo
	 * @throws ControleException
	 */
	public void setStatus(Status novo) throws ControleException {
		Status.validarTransicaoStatus(this.status, novo);
		this.status = novo;
	}

}
