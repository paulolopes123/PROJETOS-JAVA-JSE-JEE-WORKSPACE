package controle;

import janelas.JanelaCadastroProjetos;
import janelas.DialogExcluirProjeto;
import janelas.JanelaProjeto;

import java.util.Collection;
import java.util.List;

import dados.DAO;
import dados.DadosException;
import dados.Departamento;
import dados.Empregado;
import dados.IDAO;
import dados.Projeto;

public class CtrlManterProjetos {
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
			throw new ControleException(new ErroDeControle(1, "Não se pode sair do estado " + (anterior==null?"NULO":anterior) + " e ir para o estado " + novo));
		}
	}
	
	/**
	 * Referência para o controlador do programa.
	 */
	private final CtrlPrograma ctrlPrg;

	/**
	 * Referência para a janela do cadastro de Projetos
	 */
	private JanelaCadastroProjetos jCadastro;

	/**
	 * Referência para a janela Projeto que permitirá a inclusão e
	 * alteração do Projeto
	 */
	private JanelaProjeto jProjeto;

	/**
	 * Referência para o objeto Projeto sendo manipulado
	 */
	private Projeto atual;

	/**
	 * Referência para o objeto DaoProjeto
	 */
	private IDAO<Projeto> dao = (IDAO<Projeto>)DAO.getDAO(Projeto.class);

	/**
	 * Atributo que indica qual operação está em curso
	 */
	private Status status;

	//
	// MÉTODOS
	//

	/**
	 * Construtor da classe CtrlManterProjetos
	 */
	public CtrlManterProjetos(CtrlPrograma p) throws ControleException, DadosException {
		// Guardo a referência para o controlador do programa
		this.ctrlPrg = p;
		// Iniciando o caso de uso
		this.iniciar();
	}

	/** 
	 * 
	 */
	public void iniciar() throws ControleException, DadosException {
		// Crio e abro a janela de cadastro
		this.jCadastro = new JanelaCadastroProjetos(this);
		// Atualizo a interface
		this.atualizarInterface();
		// Não há Projeto em manipulação
		this.atual = null;
		// Informo que o controlador de caso de uso está disponível
		this.setStatus(Status.DISPONIVEL);
	}

	/** 
	 * 
	 */
	public void terminar() throws ControleException {
		if(this.status == Status.ENCERRADO)
			return;
		// Não há Projeto em manipulação
		this.atual = null;
		// Fecho a janela
		this.jCadastro.setVisible(false);
		// Informo que o controlador está encerrado		
		this.setStatus(Status.ENCERRADO);
		// Notifico ao controlador do programa o término do caso de uso
		this.ctrlPrg.terminarCasoDeUsoManterProjetos();
	}

	/** 
	 * 
	 */
	public void iniciarIncluir() throws ControleException {
		// Recupero os objetos Departamento do DAO
		IDAO<Departamento> daoDepto = (IDAO<Departamento>)DAO.getDAO(Departamento.class);
		Collection<Departamento> deptos = daoDepto.getListaObjs();
		IDAO<Empregado> daoEmp = (IDAO<Empregado>)DAO.getDAO(Empregado.class);
		Collection<Empregado> emps = daoEmp.getListaObjs();
		// Abro a janela de Projeto
		this.jProjeto = new JanelaProjeto(this, deptos, emps);
		// Indico que o controlador de caso de uso está incluindo
		this.setStatus(Status.INCLUINDO);
	}

	/** 
	 * 
	 */
	public void cancelarIncluir() throws ControleException {
		// Se o controlador não tinha ativado uma inclusao, não faço nada!
		if(this.status != Status.INCLUINDO)
			throw new ControleException(new ErroDeControle(2,"Não é possível cancelar uma operação de inclusão"));		
		// Fecho a janela		
		this.jProjeto.setVisible(false);
		// Informo que o controlador está disponível
		this.setStatus(Status.DISPONIVEL);
	}

	/** 
	 *
	 */
	public void incluir(String nome, Departamento depto, List<Empregado> emps) throws ControleException,
			ControleException, DadosException {
		// Se o controlador não tinha ativado uma inclusao, não faço nada!
		if(this.status != Status.INCLUINDO)
			throw new ControleException(new ErroDeControle(3,"Não é possível concluir uma operação de inclusão"));
		// Crio um novo objeto Projeto
		this.atual = new Projeto(nome,depto,emps);
		// Salvo o objeto Projeto usando o DAO
		dao.salvar(this.atual);
		// Fecho a janela Projeto
		this.jProjeto.setVisible(false);
		// Atualizo a interface
		this.atualizarInterface();
		// Indico que o controlador está disponível
		this.setStatus(Status.DISPONIVEL);
	}

	/** 
	 * 
	 */
	public void iniciarAlterar(int pos) throws ControleException, DadosException {
		// Recupero o objeto Projeto
		this.atual = dao.recuperar(pos);
		// Abro a janela Projeto para alteração
		IDAO<Departamento> daoDepto = (IDAO<Departamento>)DAO.getDAO(Departamento.class);
		Collection<Departamento> deptos = daoDepto.getListaObjs();
		IDAO<Empregado> daoEmp = (IDAO<Empregado>)DAO.getDAO(Empregado.class);
		Collection<Empregado> emps = daoEmp.getListaObjs();
		// Abro a janela Projeto para alteração
		this.jProjeto = new JanelaProjeto(this, deptos, emps);
		this.jProjeto.atualizarCampos(this.atual.getNome(),
				                      this.atual.getDepto(),
				                      this.atual.getConjEmpregados());
		// Altero o status
		this.setStatus(Status.ALTERANDO);
	}

	/** 
	 * 
	 */
	public void cancelarAlterar() throws ControleException {
		// Se o controlador não tinha ativado uma inclusao, não faço nada!
		if(this.status != Status.ALTERANDO)
			throw new ControleException(new ErroDeControle(4,"Não é possível cancelar uma operação de alteração"));
		// Fecho a janela
		this.jProjeto.setVisible(false);
		// Não guardo uma referência para o Projeto pois cancelei a alteração
		this.atual = null;
		// Indico que o controlador está disponível
		this.setStatus(Status.DISPONIVEL);		
	}

	/** 
	 * 
	 */
	public void alterar(String nome, Departamento depto, List<Empregado> emps) throws ControleException,
			DadosException, ControleException {
		// Se o controlador não tinha ativado uma inclusao, não faço nada!
		if(this.status != Status.ALTERANDO)
			throw new ControleException(new ErroDeControle(5,"Não é possível concluir uma operação de alteração"));
		// Atualizo os campos
		this.atual.setNome(nome);
		this.atual.setDepto(depto);
		this.atual.removerTodosEmpregados();
		this.atual.adicionarTodosEmpregados(emps);
		// Salvo o objeto Projeto usando o DAO
		dao.atualizar(this.atual);
		// Fecho a janela Projeto
		this.jProjeto.setVisible(false);
		// Atualizo a interface
		this.atualizarInterface();
		// Deixo de guardar a referência para o Projeto
		this.atual = null;
		// Indico que o controlador está disponível
		this.setStatus(Status.DISPONIVEL);
	}

	/** 
	 * 
	 */
	public void iniciarExcluir(int pos) throws ControleException, DadosException {
		// Recupero o objeto Projeto
		this.atual = dao.recuperar(pos);
		// Altero o status
		this.setStatus(Status.EXCLUINDO);
		// Abro a janela Projeto para perguntar sobre a exclusão
		new DialogExcluirProjeto(this, this.atual);
	}

	/** 
	 * 
	 */
	public void cancelarExcluir() throws ControleException {
		// Se o controlador não tinha ativado uma inclusao, não faço nada!
		if(this.status != Status.EXCLUINDO)
			throw new ControleException(new ErroDeControle(6,"Não é possível cancelar uma operação de exclusão"));
		// Não guardo uma referência para o Projeto pois cancelei a exclusão
		this.atual = null;
		// Indico que o controlador está disponível
		this.setStatus(Status.DISPONIVEL);
	}

	/** 
	 * 
	 */
	public void excluir() throws ControleException, DadosException {
		// Se o controlador não tinha ativado uma exclusão, não faço nada!
		if(this.status != Status.EXCLUINDO)
			throw new ControleException(new ErroDeControle(7,"Não é possível concluir uma operação de exclusão"));
		// Salvo o objeto Projeto usando o DAO
		dao.remover(this.atual);
		// Atualizo a interface
		this.atualizarInterface();
		// Deixo de guardar a referência para o Projeto
		this.atual = null;
		// Indico que o controlador está disponível
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
		// Para cada objeto Projeto presente no DAO
		for (int i = 0; i < dao.getNumObjs(); i++) {
			// Recupero um objeto Projeto
			Projeto depto = dao.recuperar(i);
			// Coloco uma linha na tabela
			this.jCadastro.incluirLinha(depto);
		}
	}
}
