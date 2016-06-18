package controle;

import janelas.JanelaCadastroEmpregados;
import janelas.JanelaEmpregado;
import janelas.DialogExcluirEmpregado;

import java.util.Collection;

import dados.DAO;
import dados.DadosException;
import dados.Departamento;
import dados.Empregado;
import dados.IDAO;

public class CtrlManterEmpregados {
	//
	// ATRIBUTOS
	//
	public enum Status {
		DISPONIVEL, INCLUINDO, EXCLUINDO, ALTERANDO, ENCERRADO;

		public static void validarTransicaoStatus(Status anterior, Status novo) throws ControleException {
			if(anterior == null && novo == DISPONIVEL || 
			   anterior == DISPONIVEL && novo == INCLUINDO  || 
			   anterior == DISPONIVEL && novo == EXCLUINDO  ||
			   anterior == DISPONIVEL && novo == ALTERANDO  ||
			   anterior == DISPONIVEL && novo == ENCERRADO  ||
			   anterior == INCLUINDO  && novo == DISPONIVEL ||
			   anterior == EXCLUINDO  && novo == DISPONIVEL ||
			   anterior == ALTERANDO  && novo == DISPONIVEL)
				return;
			throw new ControleException(new ErroDeControle(1, "N�o se pode sair do estado " + (anterior==null?"NULO":anterior) + " e ir para o estado " + novo));
		}
	}
	
	/**
	 * Refer�ncia para o controlador do programa.
	 */
	private final CtrlPrograma ctrlPrg;

	/**
	 * Refer�ncia para a janela do cadastro de Empregados
	 */
	private JanelaCadastroEmpregados jCadastro;

	/**
	 * Refer�ncia para a janela Empregado que permitir� a inclus�o e
	 * altera��o do Empregado
	 */
	private JanelaEmpregado jEmpregado;

	/**
	 * Refer�ncia para o objeto Empregado sendo manipulado
	 */
	private Empregado atual;

	/**
	 * Refer�ncia para o objeto DaoEmpregado
	 */
	private IDAO<Empregado> dao = (IDAO<Empregado>)DAO.getDAO(Empregado.class);

	/**
	 * Atributo que indica qual opera��o est� em curso
	 */
	private Status status;

	//
	// M�TODOS
	//

	/**
	 * Construtor da classe CtrlManterEmpregados
	 */
	public CtrlManterEmpregados(CtrlPrograma p) throws ControleException, DadosException {
		// Guardo a refer�ncia para o controlador do programa
		this.ctrlPrg = p;
		// Iniciando o caso de uso
		this.iniciar();
	}

	/** 
	 * 
	 */
	public void iniciar() throws ControleException, DadosException {
		// Crio e abro a janela de cadastro
		this.jCadastro = new JanelaCadastroEmpregados(this);
		// Atualizo a interface
		this.atualizarInterface();
		// N�o h� empregado em manipula��o
		this.atual = null;
		// Informo que o controlador de caso de uso est� dispon�vel
		this.setStatus(Status.DISPONIVEL);
	}

	/** 
	 * 
	 */
	public void terminar() throws ControleException {
		if(this.status == Status.ENCERRADO)
			return;
		// N�o h� empregado em manipula��o
		this.atual = null;
		// Fecho a janela
		this.jCadastro.setVisible(false);
		// Informo que o controlador est� encerrado		
		this.setStatus(Status.ENCERRADO);
		// Notifico ao controlador do programa o t�rmino do caso de uso
		this.ctrlPrg.terminarCasoDeUsoManterEmpregados();
	}

	/** 
	 * 
	 */
	public void iniciarIncluir() throws ControleException {
		// Recupero os objetos Departamento do DAO
		IDAO<Departamento> dao = (IDAO<Departamento>)DAO.getDAO(Departamento.class);
		Collection<Departamento> deptos = dao.getListaObjs();
		// Abro a janela de empregado
		this.jEmpregado = new JanelaEmpregado(this, deptos);
		// Indico que o controlador de caso de uso est� incluindo
		this.setStatus(Status.INCLUINDO);
	}

	/** 
	 * 
	 */
	public void cancelarIncluir() throws ControleException {
		// Se o controlador n�o tinha ativado uma inclusao, n�o fa�o nada!
		if(this.status != Status.INCLUINDO)
			throw new ControleException(new ErroDeControle(2,"N�o � poss�vel cancelar uma opera��o de inclus�o"));		
		// Fecho a janela		
		this.jEmpregado.setVisible(false);
		// Informo que o controlador est� dispon�vel
		this.setStatus(Status.DISPONIVEL);
	}

	/** 
	 * 
	 */
	public void incluir(String cpf, String nome, Departamento depto) throws ControleException,
			ControleException, DadosException {
		// Se o controlador n�o tinha ativado uma inclusao, n�o fa�o nada!
		if(this.status != Status.INCLUINDO)
			throw new ControleException(new ErroDeControle(3,"N�o � poss�vel concluir uma opera��o de inclus�o"));
		// Crio um novo objeto Empregado
		this.atual = new Empregado(cpf, nome,depto);
		// Salvo o objeto Empregado usando o DAO
		dao.salvar(this.atual);
		// Fecho a janela Empregado
		this.jEmpregado.setVisible(false);
		// Atualizo a interface
		this.atualizarInterface();
		// Indico que o controlador est� dispon�vel
		this.setStatus(Status.DISPONIVEL);
	}

	/** 
	 * 
	 */
	public void iniciarAlterar(int pos) throws ControleException, DadosException {
		// Recupero o objeto Empregado
		this.atual = dao.recuperar(pos);
		// Abro a janela Empregado para altera��o
		IDAO<Departamento> daoDepartamento = (IDAO<Departamento>)DAO.getDAO(Departamento.class);
		Collection<Departamento> deptos = daoDepartamento.getListaObjs();
		// Abro a janela Empregado para altera��o
		this.jEmpregado = new JanelaEmpregado(this, deptos);
		this.jEmpregado.atualizarCampos(this.atual.getCpf(), 
				                        this.atual.getNome(),
				                        this.atual.getDepto());
		// Altero o status
		this.setStatus(Status.ALTERANDO);
	}

	/** 
	 * 
	 */
	public void cancelarAlterar() throws ControleException {
		// Se o controlador n�o tinha ativado uma inclusao, n�o fa�o nada!
		if(this.status != Status.ALTERANDO)
			throw new ControleException(new ErroDeControle(4,"N�o � poss�vel cancelar uma opera��o de altera��o"));
		// Fecho a janela
		this.jEmpregado.setVisible(false);
		// N�o guardo uma refer�ncia para o Empregado pois cancelei a altera��o
		this.atual = null;
		// Indico que o controlador est� dispon�vel
		this.setStatus(Status.DISPONIVEL);		
	}

	/** 
	 * 
	 */
	public void alterar(String cpf, String nome, Departamento depto) throws ControleException,
			DadosException, ControleException {
		// Se o controlador n�o tinha ativado uma inclusao, n�o fa�o nada!
		if(this.status != Status.ALTERANDO)
			throw new ControleException(new ErroDeControle(5,"N�o � poss�vel concluir uma opera��o de altera��o"));
		// Atualizo os campos
		this.atual.setCpf(cpf);
		this.atual.setNome(nome);
		this.atual.setDepto((Departamento)depto);
		// Salvo o objeto Empregado usando o DAO
		dao.atualizar(this.atual);
		// Fecho a janela Empregado
		this.jEmpregado.setVisible(false);
		// Atualizo a interface
		this.atualizarInterface();
		// Deixo de guardar a refer�ncia para o Empregado
		this.atual = null;
		// Indico que o controlador est� dispon�vel
		this.setStatus(Status.DISPONIVEL);
	}

	/** 
	 * 
	 */
	public void iniciarExcluir(int pos) throws ControleException, DadosException {
		// Recupero o objeto Empregado
		this.atual = dao.recuperar(pos);
		// Altero o status
		this.setStatus(Status.EXCLUINDO);
		// Abro a janela Empregado para perguntar sobre a exclus�o
		new DialogExcluirEmpregado(this, this.atual);
	}

	/** 
	 * 
	 */
	public void cancelarExcluir() throws ControleException {
		// Se o controlador n�o tinha ativado uma inclusao, n�o fa�o nada!
		if(this.status != Status.EXCLUINDO)
			throw new ControleException(new ErroDeControle(6,"N�o � poss�vel cancelar uma opera��o de exclus�o"));
		// N�o guardo uma refer�ncia para o Empregado pois cancelei a exclus�o
		this.atual = null;
		// Indico que o controlador est� dispon�vel
		this.setStatus(Status.DISPONIVEL);
	}

	/** 
	 * 
	 */
	public void excluir() throws ControleException, DadosException {
		// Se o controlador n�o tinha ativado uma exclus�o, n�o fa�o nada!
		if(this.status != Status.EXCLUINDO)
			throw new ControleException(new ErroDeControle(7,"N�o � poss�vel concluir uma opera��o de exclus�o"));
		// Salvo o objeto Empregado usando o DAO
		dao.remover(this.atual);
		// Atualizo a interface
		this.atualizarInterface();
		// Deixo de guardar a refer�ncia para o Empregado
		this.atual = null;
		// Indico que o controlador est� dispon�vel
		this.setStatus(Status.DISPONIVEL);
	}

	/**
	 * 
	 * @return
	 */
	public Status getStatus() {
		return this.status;
	}
	
	/**
	 * 
	 * @param novo
	 * @throws ControleException
	 */
	public void setStatus(Status novo) throws ControleException {
		Status.validarTransicaoStatus(this.status,novo);
		this.status = novo;
	}

	/** 
	 * 
	 */
	public void atualizarInterface() throws DadosException {
		// Limpa a tabela da janela
		this.jCadastro.limpar();
		// Para cada objeto Empregado presente no DAO
		for (int i = 0; i < dao.getNumObjs(); i++) {
			// Recupero um objeto Empregado
			Empregado depto = dao.recuperar(i);
			// Coloco uma linha na tabela
			this.jCadastro.incluirLinha(depto);
		}
	}
}
