package controle;

import controle.CtrlElaborarProva.Status;
import dominio.DadosException;
import dominio.Prova;
import face.JanelaCorrigirProva;

public class CtrlCorrigirProva {

	//
	// ATRIBUTOS
	//
	public enum Status {
		EmCorrecao, Corrigida;

		public static void validarTransicaoStatus(Status anterior, Status novo) throws ControleException {
			if (anterior == null && novo == EmCorrecao || anterior == EmCorrecao && novo == Corrigida
					|| anterior == Corrigida)
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
	 * Referência para a janela corrigir Prova
	 */
	private JanelaCorrigirProva jCorrigirProva;

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
	 * Construtor da classe CtrlCorrigirProva
	 */

	public CtrlCorrigirProva(CtrlPrograma p) throws ControleException, DadosException {
		// Guardo a referência para o controlador do programa
		this.ctrlPrg = p;
		// Iniciando o caso de uso
		this.iniciar();
	}

	public void iniciar() throws ControleException, DadosException {
		// Crio e abro a janela de correção da prova
		this.jCorrigirProva = new JanelaCorrigirProva(this);

		// Não há Prova em manipulação
		this.atual = null;
		// Informo que o controlador de caso de uso está disponível
		this.setStatus(Status.EmCorrecao);
	}

	public void terminar() throws ControleException {
		if (this.status == Status.Corrigida)
			return;
		// Não há Prova em manipulação
		this.atual = null;
		// Fecho a janela
		this.jCorrigirProva.setVisible(false);
		// Informo que o controlador está encerrado
		this.setStatus(Status.Corrigida);
		// Notifico ao controlador do programa o término do caso de uso
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
