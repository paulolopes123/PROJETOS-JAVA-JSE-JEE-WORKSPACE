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
			throw new ControleException(new ErroDeControle(1, "Não se pode sair do estado "
					+ (anterior == null ? "NULO" : anterior) + " e ir para o estado " + novo));
		}
	}

	/**
	 * Referência para o controlador do programa.
	 */
	private final CtrlPrograma ctrlPrg;

	/**
	 * Referência para a janela elaborar Prova
	 */
	private JanelaElaborarProva jElaborarProva;
	/**
	 * Referência para a janela incluir Questão
	 */
	private JanelaIncluirQuestao jIncluirQuestao;

	/**
	 * Referência para o objeto Prova sendo manipulado
	 */
	private Prova atual;
	
	
	
	/**
	 * Referência para o objeto DaoProva
	 */
	private IDAO<Prova> dao = (IDAO<Prova>)DAO.getDAO(Prova.class);

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

	public CtrlElaborarProva(CtrlPrograma p) throws ControleException, DadosException {
		// Guardo a referência para o controlador do programa
		this.ctrlPrg = p;
		// Iniciando o caso de uso
		this.iniciar();
	}

	public void iniciar() throws ControleException, DadosException {
		// Crio e abro a janela de elaboração da prova
		this.jElaborarProva = new JanelaElaborarProva(this);

		// Não há Prova em manipulação
		this.atual = null;
		// Informo que o controlador de caso de uso está disponível
		this.setStatus(Status.EmElaboracao);
	}

	public void incluirQuestao() throws ControleException, DadosException {
		// Crio e abro a janela de inclusão de questão
		this.jIncluirQuestao = new JanelaIncluirQuestao(this);
	}

	public void terminar() throws ControleException {
		if (this.status == Status.Pronta)
			return;
		// Não há Prova em manipulação
		this.atual = null;
		// Fecho a janela
		this.jElaborarProva.setVisible(false);
		// Informo que o controlador está encerrado
		this.setStatus(Status.Pronta);
	}

	public void terminarQuestao() throws ControleException {
		// if (this.status == Status.Pronta)
		// return;
		// Não há Prova em manipulação
		// this.atual = null;
		// Fecho a janela
		this.jIncluirQuestao.setVisible(false);
		// Informo que o controlador está encerrado
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
		// Se o controlador não tinha ativado uma inclusao, não faço nada!
		if (this.status != Status.EmElaboracao)
			throw new ControleException(new ErroDeControle(3, "Não é possível concluir uma operação de inclusão"));
		// Crio um novo objeto Prova
		this.atual = new Prova(nome,textoHtml);
		// Salvo o objeto prova usando o DAO
		dao.salvar(this.atual);
		JOptionPane.showMessageDialog(null,"Prova Elaborada com Sucesso!!");
		// Fecho a janela Departamento
		this.jElaborarProva.setVisible(false);
		
		// Indico que o controlador está disponível
		this.setStatus(Status.Pronta);
	}

}
