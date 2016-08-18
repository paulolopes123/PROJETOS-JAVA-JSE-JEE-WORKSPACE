package controle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JOptionPane;

import dominio.Aluno;
import dominio.DAO;
import dominio.DadosException;
import dominio.IDAOSerializavel;
import dominio.Professor;
import face.JanelaPrincipalAluno;
import face.JanelaPrincipalProfessor;

/**
 * Este é o controlador que gerencia a execução do meu programa.
 * 
 * @author Paulo Roberto
 */
public class CtrlPrograma {
	//
	// ATRIBUTOS
	// ---------
	// O controlador do programa deve ter um atributo para
	// cada controlador de caso de uso do programa.
	//
	// Também o controlador do programa deve ter um atributo
	// para referenciar a janela principal do programa.
	//

	/**
	 * Referência para o controlador do caso de uso Iniciar Prova
	 */
	private CtrlIniciarProva ctrlIniciarProva;

	/**
	 * Referência para o controlador do caso de uso Elaborar Prova
	 */
	private CtrlElaborarProva ctrlElaborarProva;

	/**
	 * Referência para o controlador do caso de uso Elaborar Questão
	 */
	private CtrlElaborarQuestao ctrlElaborarQuestao;

	/**
	 * Referência para o controlador do caso de uso Login
	 */
	private CtrlLogin ctrlLogin;

	/**
	 * Referência para o controlador do caso de uso Corrigir Prova
	 */
	private CtrlCorrigirConsultarProva ctrlCorrigirProva;

	/**
	 * Referência para o controlador do caso de uso Alterar Prova
	 */
	private CtrlAlterarProva ctrlAlterarProva;

	/**
	 * Referência para o controlador do caso de uso Alterar Questão
	 */
	private CtrlAlterarQuestao ctrlAlterarQuestao;

	/**
	 * Referência para o controlador do caso de uso Excluir Prova
	 */
	private CtrlExcluirProva ctrlExcluirProva;

	/**
	 * Referência para o controlador do caso de uso Excluir Questão
	 */
	private CtrlExcluirQuestao ctrlExcluirQuestao;

	/**
	 * Referência para o controlador do caso de uso Disponibilização de Prova
	 */
	private CtrlDisponibilizarProva ctrlDisponibilizarProva;

	/**
	 * Referência para o controlador do caso de uso Duplicar de Prova
	 */
	private CtrlDuplicarProva ctrlDuplicarProva;

	/**
	 * Referência para o controlador do caso de uso Consultar de Prova
	 */
	private CtrlConsultarProva ctrlConsultarProva;

	/**
	 * Referência para a janela principal do programa
	 */
	private JanelaPrincipalProfessor jPrincipalProfessor;
	private JanelaPrincipalAluno jPrincipalAluno;

	//
	// MÉTODOS
	//

	/**
	 * Construtor do CtrlPrograma
	 */
	public CtrlPrograma() {
	}

	public void iniciar() throws ControleException, DadosException {
		// Recupera os DAOs do sistema
		IDAOSerializavel daoProfessor = (IDAOSerializavel) DAO.getDAO(Professor.class);
		IDAOSerializavel daoAluno = (IDAOSerializavel) DAO.getDAO(Aluno.class);

		//
		// Recuperação dos objetos serializados no arquivo c:/dados.dat
		//
		try {
			// Abrindo o arquivo para leitura binária
			FileInputStream fis = new FileInputStream("dados.dat");
			ObjectInputStream ois = new ObjectInputStream(fis);
			// Solicitação para os DAOs gerenciarem os objetos recuperados do
			// arquivo
			daoProfessor.recuperarObjetos(ois);
			daoAluno.recuperarObjetos(ois);
			// Fechando o arquivo
			ois.close();
		} catch (FileNotFoundException e) {
			System.out.println("Arquivo dados.dat não encontrado");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		this.iniciarCasoDeUsoEfetuarLogin();
	}

	public void terminar() {
		// Recuperando os DAOs do sistema
		IDAOSerializavel daoProfessor = (IDAOSerializavel) DAO.getDAO(Professor.class);
		IDAOSerializavel daoAluno = (IDAOSerializavel) DAO.getDAO(Aluno.class);

		try {
			// Abrindo o arquivo c:/dados.dat para escrita
			FileOutputStream fos = new FileOutputStream("dados.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			// Salvando os objetos gerenciados pelos DAOs
			daoProfessor.salvarObjetos(oos);
			daoAluno.salvarObjetos(oos);
			// Fechando e salvando o arquivo
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Método estático da classe System que encerra o programa
		System.exit(0);
	}

	//
	// CASO DE USO - EfetuarLogin
	//

	public void iniciarCasoDeUsoEfetuarLogin() throws ControleException, DadosException {
		this.ctrlLogin = new CtrlLogin(this);
	}

	public void loginProfessorEfetuado(Professor usr) throws ControleException, DadosException {
		this.ctrlLogin.terminar();
		this.ctrlLogin = null;
		this.jPrincipalProfessor = new JanelaPrincipalProfessor(this);
	}

	public void loginAlunoEfetuado(Aluno usr) throws ControleException, DadosException {
		this.ctrlLogin.terminar();
		this.ctrlLogin = null;
		this.jPrincipalAluno = new JanelaPrincipalAluno(this);
	}

	public void erroFatalLogin() throws ControleException, DadosException {
		JOptionPane.showMessageDialog(null, "Login Abortado!");
		this.ctrlLogin.terminar();
		this.ctrlLogin = null;
		this.terminar();
	}

	//
	// CASO DE USO - IniciarProva
	//
	public void iniciarCasoDeUsoCtrlIniciarProva() throws ControleException, DadosException {
		// Instanciando os controladores de caso de uso do sistema
		this.ctrlIniciarProva = new CtrlIniciarProva(this);
	}

	public void terminarCasoDeUsoCtrlIniciarProva() throws ControleException {
		this.ctrlIniciarProva.terminar();
		this.ctrlIniciarProva = null;
	}

	//
	// CASO DE USO - ElaborarProva
	//
	public void iniciarCasoDeUsoCtrlElaborarProva() throws ControleException, DadosException {
		// Instanciando os controladores de caso de uso do sistema
		this.ctrlElaborarProva = new CtrlElaborarProva(this);
	}

	public void terminarCasoDeUsoCtrlElaborarProva() throws ControleException {
		this.ctrlElaborarProva.terminar();
		this.ctrlElaborarProva = null;
	}

	//
	// CASO DE USO - ElaborarQuestão
	//

	public void iniciarCasoDeUsoCtrlElaborarQuestao() throws ControleException, DadosException {
		// Instanciando os controladores de caso de uso do sistema
		this.ctrlElaborarQuestao = new CtrlElaborarQuestao(this);
	}

	public void terminarCasoDeUsoCtrlElaborarQuestao() throws ControleException {
		this.ctrlElaborarQuestao.terminar();
		this.ctrlElaborarQuestao = null;
	}

	//
	// CASO DE USO - Corrigir Prova
	//

	public void iniciarCasoDeUsoCtrlCorrigirProva() throws ControleException, DadosException {
		// Instanciando os controladores de caso de uso do sistema
		this.ctrlCorrigirProva = new CtrlCorrigirConsultarProva(this);
	}

	public void terminarCasoDeUsoCtrlCorrigirProva() throws ControleException {
		this.ctrlCorrigirProva.terminar();
		this.ctrlCorrigirProva = null;
	}

	//
	// CASO DE USO - Alterar Prova
	//

	public void iniciarCasoDeUsoCtrlAlterarProva() throws ControleException, DadosException {
		// Instanciando os controladores de caso de uso do sistema
		this.ctrlAlterarProva = new CtrlAlterarProva(this);
	}

	public void terminarCasoDeUsoCtrlAlterarProva() throws ControleException {
		this.ctrlAlterarProva.terminar();
		this.ctrlAlterarProva = null;
	}

	//
	// CASO DE USO - Alterar Questão
	//

	public void iniciarCasoDeUsoCtrlAlterarQuestao() throws ControleException, DadosException {
		// Instanciando os controladores de caso de uso do sistema
		this.ctrlAlterarQuestao = new CtrlAlterarQuestao(this);
	}

	public void terminarCasoDeUsoCtrlAlterarQuestao() throws ControleException {
		this.ctrlAlterarQuestao.terminar();
		this.ctrlAlterarQuestao = null;
	}

	//
	// CASO DE USO - Excluir Prova
	//

	public void iniciarCasoDeUsoCtrlExcluirProva() throws ControleException, DadosException {
		// Instanciando os controladores de caso de uso do sistema
		this.ctrlExcluirProva = new CtrlExcluirProva(this);
	}

	public void terminarCasoDeUsoCtrlExcluirProva() throws ControleException {
		this.ctrlExcluirProva.terminar();
		this.ctrlExcluirProva = null;
	}

	//
	// CASO DE USO - Excluir Questão
	//

	public void iniciarCasoDeUsoCtrlExcluirQuestao() throws ControleException, DadosException {
		// Instanciando os controladores de caso de uso do sistema
		this.ctrlExcluirQuestao = new CtrlExcluirQuestao(this);
	}

	public void terminarCasoDeUsoCtrlExcluirQuestao() throws ControleException {
		this.ctrlExcluirQuestao.terminar();
		this.ctrlExcluirQuestao = null;
	}

	//
	// CASO DE USO - Disponibilizar Prova
	//

	public void iniciarCasoDeUsoCtrlDisponibilizarProva() throws ControleException, DadosException {
		// Instanciando os controladores de caso de uso do sistema
		this.ctrlDisponibilizarProva = new CtrlDisponibilizarProva(this);
	}

	public void terminarCasoDeUsoCtrlDisponibilizarProva() throws ControleException {
		this.ctrlDisponibilizarProva.terminar();
		this.ctrlDisponibilizarProva = null;
	}

	//
	// CASO DE USO - Duplicar Prova
	//

	public void iniciarCasoDeUsoCtrlDuplicarProva() throws ControleException, DadosException {
		// Instanciando os controladores de caso de uso do sistema
		this.ctrlDuplicarProva = new CtrlDuplicarProva(this);
	}

	public void terminarCasoDeUsoCtrlDuplicarProva() throws ControleException {
		this.ctrlDuplicarProva.terminar();
		this.ctrlDuplicarProva = null;
	}

	//
	// CASO DE USO - Consultar Prova
	//

	public void iniciarCasoDeUsoCtrlConsultarProva() throws ControleException, DadosException {
		// Instanciando os controladores de caso de uso do sistema
		this.ctrlConsultarProva = new CtrlConsultarProva(this);
	}

	public void terminarCasoDeUsoCtrlConsultarProva() throws ControleException {
		this.ctrlConsultarProva.terminar();
		this.ctrlConsultarProva = null;
	}

	public static void main(String[] args) throws ControleException, DadosException {
		CtrlPrograma prg = new CtrlPrograma();
		prg.iniciar();
	}

}
