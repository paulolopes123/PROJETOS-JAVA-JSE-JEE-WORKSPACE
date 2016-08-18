package controle;

import dominio.DadosException;
import dominio.Questao;
import face.JanelaAlterarQuestao;
import face.JanelaElaborarQuestao;

public class CtrlAlterarQuestao {

	/**
	 * Referência para o controlador do programa.
	 */
	private final CtrlPrograma ctrlPrg;

	/**
	 * Referência para a janela alterar Questão
	 */
	private JanelaAlterarQuestao jAlterarQuestao;

	/**
	 * Referência para o objeto questão sendo manipulado
	 */
	private Questao atual;

	/**
	 * Atributo que indica qual operação está em curso
	 */
	private Status status;

	private enum Status {
		EmAlteracao, Alterada;

		public static void validarTransicaoStatus(Status anterior, Status novo) throws ControleException {
			if (anterior == null && novo == EmAlteracao || anterior == EmAlteracao && novo == Alterada
					|| anterior == Alterada)
				return;
			throw new ControleException(new ErroDeControle(1, "Não se pode sair do estado "
					+ (anterior == null ? "NULO" : anterior) + " e ir para o estado " + novo));

		}

	};

	//
	// MÉTODOS
	//

	/**
	 * Construtor da classe CtrlElaborarQuestão
	 */

	public CtrlAlterarQuestao(CtrlPrograma p) throws ControleException, DadosException {
		// Guardo a referência para o controlador do programa
		this.ctrlPrg = p;
		// Iniciando o caso de uso
		this.iniciar();
	}

	public void iniciar() throws ControleException, DadosException {
		// Crio e abro a janela de alteração da questão
		this.jAlterarQuestao = new JanelaAlterarQuestao(this);
		// Não há Prova em manipulação
		this.atual = null;
		// Informo que o controlador de caso de uso está disponível
		this.setStatus(Status.EmAlteracao);

	}

	public void terminar() throws ControleException {

		// Não há questão em manipulação
		this.atual = null;
		// Fecho a janela
		this.jAlterarQuestao.setVisible(false);
		// Informo que o controlador está encerrado
		this.setStatus(Status.Alterada);
		// Notifico ao controlador do programa o término do caso de uso
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
