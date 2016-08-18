package controle;

import dominio.DadosException;
import dominio.Questao;
import face.JanelaAlterarQuestao;
import face.JanelaElaborarQuestao;

public class CtrlAlterarQuestao {

	/**
	 * Refer�ncia para o controlador do programa.
	 */
	private final CtrlPrograma ctrlPrg;

	/**
	 * Refer�ncia para a janela alterar Quest�o
	 */
	private JanelaAlterarQuestao jAlterarQuestao;

	/**
	 * Refer�ncia para o objeto quest�o sendo manipulado
	 */
	private Questao atual;

	/**
	 * Atributo que indica qual opera��o est� em curso
	 */
	private Status status;

	private enum Status {
		EmAlteracao, Alterada;

		public static void validarTransicaoStatus(Status anterior, Status novo) throws ControleException {
			if (anterior == null && novo == EmAlteracao || anterior == EmAlteracao && novo == Alterada
					|| anterior == Alterada)
				return;
			throw new ControleException(new ErroDeControle(1, "N�o se pode sair do estado "
					+ (anterior == null ? "NULO" : anterior) + " e ir para o estado " + novo));

		}

	};

	//
	// M�TODOS
	//

	/**
	 * Construtor da classe CtrlElaborarQuest�o
	 */

	public CtrlAlterarQuestao(CtrlPrograma p) throws ControleException, DadosException {
		// Guardo a refer�ncia para o controlador do programa
		this.ctrlPrg = p;
		// Iniciando o caso de uso
		this.iniciar();
	}

	public void iniciar() throws ControleException, DadosException {
		// Crio e abro a janela de altera��o da quest�o
		this.jAlterarQuestao = new JanelaAlterarQuestao(this);
		// N�o h� Prova em manipula��o
		this.atual = null;
		// Informo que o controlador de caso de uso est� dispon�vel
		this.setStatus(Status.EmAlteracao);

	}

	public void terminar() throws ControleException {

		// N�o h� quest�o em manipula��o
		this.atual = null;
		// Fecho a janela
		this.jAlterarQuestao.setVisible(false);
		// Informo que o controlador est� encerrado
		this.setStatus(Status.Alterada);
		// Notifico ao controlador do programa o t�rmino do caso de uso
	}

	public Status getStatus() {
		return this.status;
	}

	/**
	 * 
	 * @param novo
	 * @throws ControleException
	 * @throws DadosException
	 */
	public void setStatus(Status novo) throws ControleException {
		Status.validarTransicaoStatus(this.status, novo);
		this.status = novo;
	}

}
