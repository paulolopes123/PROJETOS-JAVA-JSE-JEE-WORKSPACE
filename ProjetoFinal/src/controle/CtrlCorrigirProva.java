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
			throw new ControleException(new ErroDeControle(1, "N�o se pode sair do estado "
					+ (anterior == null ? "NULO" : anterior) + " e ir para o estado " + novo));
		}
	}

	/**
	 * Refer�ncia para o controlador do programa.
	 */
	private final CtrlPrograma ctrlPrg;

	/**
	 * Refer�ncia para a janela corrigir Prova
	 */
	private JanelaCorrigirProva jCorrigirProva;

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
	 * Construtor da classe CtrlCorrigirProva
	 */

	public CtrlCorrigirProva(CtrlPrograma p) throws ControleException, DadosException {
		// Guardo a refer�ncia para o controlador do programa
		this.ctrlPrg = p;
		// Iniciando o caso de uso
		this.iniciar();
	}

	public void iniciar() throws ControleException, DadosException {
		// Crio e abro a janela de corre��o da prova
		this.jCorrigirProva = new JanelaCorrigirProva(this);

		// N�o h� Prova em manipula��o
		this.atual = null;
		// Informo que o controlador de caso de uso est� dispon�vel
		this.setStatus(Status.EmCorrecao);
	}

	public void terminar() throws ControleException {
		if (this.status == Status.Corrigida)
			return;
		// N�o h� Prova em manipula��o
		this.atual = null;
		// Fecho a janela
		this.jCorrigirProva.setVisible(false);
		// Informo que o controlador est� encerrado
		this.setStatus(Status.Corrigida);
		// Notifico ao controlador do programa o t�rmino do caso de uso
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
