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
			throw new ControleException(new ErroDeControle(1, "Não se pode sair do estado "
					+ (anterior == null ? "NULO" : anterior) + " e ir para o estado " + novo));
		}
	}

	/**
	 * Referência para o controlador do programa.
	 */
	private final CtrlPrograma ctrlPrg;

	/**
	 * Referência para a janela alterar Prova
	 */
	private JanelaExcluirQuestao jExcluirQuestao;

	/**
	 * Referência para o objeto Prova sendo manipulado
	 */
	private Prova atual;

	/**
	 * Atributo que indica qual operação está em curso
	 */
	private Status status;

	//
	// MÉTODOS
	//

	/**
	 * Construtor da classe CtrlElaborarProva
	 */

	public CtrlExcluirQuestao(CtrlPrograma p) throws ControleException, DadosException {
		// Guardo a referência para o controlador do programa
		this.ctrlPrg = p;
		// Iniciando o caso de uso
		this.iniciar();
	}

	public void iniciar() throws ControleException, DadosException {
		// Crio e abro a janela de exclusão da questão
		this.jExcluirQuestao = new JanelaExcluirQuestao(this);

		// Não há Prova em manipulação
		this.atual = null;
		// Informo que o controlador de caso de uso está disponível
		this.setStatus(Status.EmExclusao);
	}

	public void terminar() throws ControleException {
		if (this.status == Status.Excluida)
			return;
		// Não há Prova em manipulação
		this.atual = null;
		// Fecho a janela
		this.jExcluirQuestao.setVisible(false);
		// Informo que o controlador está encerrado
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
