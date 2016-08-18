package controle;

import javax.swing.JOptionPane;

import dominio.DAO;
import dominio.IDAO;
import dominio.DadosException;
import dominio.Prova;
import face.JanelaElaborarProva;
import face.JanelaIncluirQuestao;
import face.JanelaPrincipalProfessor;

public class CtrlElaborarProva {
	//
	// ATRIBUTOS
	//
	public enum Status {
		EmElaboracao, Pronta;

		public static void validarTransicaoStatus(Status anterior, Status novo) throws ControleException {
			if (anterior == null && novo == EmElaboracao || anterior == EmElaboracao && novo == Pronta
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
	 * Refer�ncia para a janela elaborar Prova
	 */
	private JanelaElaborarProva jElaborarProva;
	/**
	 * Refer�ncia para a janela incluir Quest�o
	 */
	private JanelaIncluirQuestao jIncluirQuestao;

	/**
	 * Refer�ncia para o objeto Prova sendo manipulado
	 */
	private Prova atual;
	
	
	
	/**
	 * Refer�ncia para o objeto DaoProva
	 */
	private IDAO<Prova> dao = (IDAO<Prova>)DAO.getDAO(Prova.class);

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

	public CtrlElaborarProva(CtrlPrograma p) throws ControleException, DadosException {
		// Guardo a refer�ncia para o controlador do programa
		this.ctrlPrg = p;
		// Iniciando o caso de uso
		this.iniciar();
	}

	public void iniciar() throws ControleException, DadosException {
		// Crio e abro a janela de elabora��o da prova
		this.jElaborarProva = new JanelaElaborarProva(this);

		// N�o h� Prova em manipula��o
		this.atual = null;
		// Informo que o controlador de caso de uso est� dispon�vel
		this.setStatus(Status.EmElaboracao);
	}

	public void incluirQuestao() throws ControleException, DadosException {
		// Crio e abro a janela de inclus�o de quest�o
		this.jIncluirQuestao = new JanelaIncluirQuestao(this);
	}

	public void terminar() throws ControleException {
		if (this.status == Status.Pronta)
			return;
		// N�o h� Prova em manipula��o
		this.atual = null;
		// Fecho a janela
		this.jElaborarProva.setVisible(false);
		// Informo que o controlador est� encerrado
		this.setStatus(Status.Pronta);
	}

	public void terminarQuestao() throws ControleException {
		// if (this.status == Status.Pronta)
		// return;
		// N�o h� Prova em manipula��o
		// this.atual = null;
		// Fecho a janela
		this.jIncluirQuestao.setVisible(false);
		// Informo que o controlador est� encerrado
		// this.setStatus(Status.Pronta);
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

	public void incluir(String nome,String textoHtml) throws ControleException, DadosException {
		// Se o controlador n�o tinha ativado uma inclusao, n�o fa�o nada!
		if (this.status != Status.EmElaboracao)
			throw new ControleException(new ErroDeControle(3, "N�o � poss�vel concluir uma opera��o de inclus�o"));
		// Crio um novo objeto Prova
		this.atual = new Prova(nome,textoHtml);
		// Salvo o objeto prova usando o DAO
		dao.salvar(this.atual);
		JOptionPane.showMessageDialog(null,"Prova Elaborada com Sucesso!!");
		// Fecho a janela Departamento
		this.jElaborarProva.setVisible(false);
		
		// Indico que o controlador est� dispon�vel
		this.setStatus(Status.Pronta);
	}

}