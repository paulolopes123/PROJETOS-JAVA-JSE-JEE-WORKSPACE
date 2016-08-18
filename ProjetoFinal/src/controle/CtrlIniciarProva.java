package controle;

import dominio.DadosException;
import dominio.Prova;
import face.JanelaIniciarProva;
import face.JanelaIniciarProva;

public class CtrlIniciarProva {
	//
	// ATRIBUTOS
	//
	public enum Status {
		EmRealizacao, Pronta;

		public static void validarTransicaoStatus(Status anterior, Status novo) throws ControleException {
			if (anterior == null && novo == EmRealizacao || anterior == EmRealizacao && novo == Pronta
					|| anterior == Pronta)
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
	 * Refer�ncia para a janela Iniciar Prova
	 */
	private JanelaIniciarProva jIniciarProva;

	/**
	 * Refer�ncia para a janela Prova que permitir� o inicio de uma Prova
	 * 
	 */
	private JanelaIniciarProva jProva;

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
	 * Construtor da classe CtrlIniciarProva
	 */
	public CtrlIniciarProva(CtrlPrograma p) throws ControleException, DadosException {
		// Guardo a refer�ncia para o controlador do programa
		this.ctrlPrg = p;
		// Iniciando o caso de uso
		this.iniciar();
	}

	public void iniciar() throws ControleException, DadosException {
		// Crio e abro a janela de Inicio de Prova
		this.jIniciarProva = new JanelaIniciarProva(this);

		// N�o h� Prova em manipula��o
		this.atual = null;
		// Informo que o controlador de caso de uso est� dispon�vel
		this.setStatus(Status.EmRealizacao);
	}

	public void iniciarProva() throws ControleException, DadosException {
		// Crio e abro a janela de Inicio de Prova
		this.jProva = new JanelaIniciarProva(this);
		// Fecho a janela Iniciar prova
		this.jIniciarProva.setVisible(false);
		// N�o h� Prova em manipula��o
		this.atual = null;
		// Informo que o controlador de caso de uso est� dispon�vel
		// this.setStatus(Status.EmRealizacao);
	}

	public void terminar() throws ControleException {
		if (this.status == Status.Pronta)
			return;
		// N�o h� Prova em manipula��o
		this.atual = null;
		// Fecho a janela
		this.jIniciarProva.setVisible(false);
		// Informo que o controlador est� encerrado
		this.setStatus(Status.Pronta);
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