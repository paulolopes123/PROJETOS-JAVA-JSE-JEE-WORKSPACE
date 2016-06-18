package controle;

import janelas.JanelaPrincipal;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import dados.DAO;
import dados.DadosException;
import dados.Departamento;
import dados.Empregado;
import dados.IDAOSerializavel;
import dados.Projeto;

/**
 * Este � o controlador que gerencia a execu��o do meu programa.
 * @author Alessandro Cerqueira
 */
public class CtrlPrograma {
	//
	// ATRIBUTOS
	// ---------
	// O controlador do programa deve ter um atributo para
	// cada controlador de caso de uso do programa.
	//
	// Tamb�m o controlador do programa deve ter um atributo 
	// para referenciar a janela principal do programa.
	//

	/**
	 * Refer�ncia para o controlador do caso de uso 
	 * Manter Departamentos
	 */
	private CtrlManterDepartamentos 	ctrlDepartamentos;
	/**
	 * Refer�ncia para o controlador do caso de uso 
	 * Manter Empregados
	 */
	private CtrlManterEmpregados    	ctrlEmpregados;
	/**
	 * Refer�ncia para o controlador do caso de uso 
	 * Manter Projetos
	 */
	private CtrlManterProjetos    		ctrlProjetos;
	/**
	 * Refer�ncia para a janela principal do programa
	 */
	private JanelaPrincipal          	jPrincipal;	
	//
	// M�TODOS
	//

	/**
	 * Construtor do CtrlPrograma
	 */
	public CtrlPrograma(){
	}

	/**
	 * 
	 */
	public void iniciar() {
		// Cria e apresenta a janela principal
		this.jPrincipal = new JanelaPrincipal(this);		
		
		// Recupera os DAOs do sistema
		IDAOSerializavel daoDepartamento = (IDAOSerializavel)DAO.getDAO(Departamento.class);
		IDAOSerializavel daoEmpregado = (IDAOSerializavel)DAO.getDAO(Empregado.class);
		IDAOSerializavel daoProjeto = (IDAOSerializavel)DAO.getDAO(Projeto.class);

		//
		// Recupera��o dos objetos serializados no arquivo c:/dados.dat
		//
		try {
			// Abrindo o arquivo para leitura bin�ria
			FileInputStream fis = new FileInputStream("dados.dat");
			ObjectInputStream ois = new ObjectInputStream(fis);
			// Solicita��o para os DAOs gerenciarem os objetos recuperados do arquivo
			daoDepartamento.recuperarObjetos(ois);
			daoEmpregado.recuperarObjetos(ois);
			daoProjeto.recuperarObjetos(ois);
			// Fechando o arquivo 
			ois.close();
		} catch (FileNotFoundException e) {
			System.out.println("Arquivo dados.dat n�o encontrado");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}		
	}

	/**
	 * 
	 */
	public void terminar(){
		// Recuperando os DAOs do sistema
		IDAOSerializavel daoDepartamento = (IDAOSerializavel)DAO.getDAO(Departamento.class);
		IDAOSerializavel daoEmpregado = (IDAOSerializavel)DAO.getDAO(Empregado.class);
		IDAOSerializavel daoProjeto = (IDAOSerializavel)DAO.getDAO(Projeto.class);

		try {
			// Abrindo o arquivo c:/dados.dat para escrita
			FileOutputStream fos = new FileOutputStream("dados.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			// Salvando os objetos gerenciados pelos DAOs
			daoDepartamento.salvarObjetos(oos);
			daoEmpregado.salvarObjetos(oos);
			daoProjeto.salvarObjetos(oos);
			// Fechando e salvando o arquivo
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 	
		// M�todo est�tico da classe System que encerra o programa
		System.exit(0);
	}
	
	/**
	 * 
	 */
	public void iniciarCasoDeUsoManterDepartamentos() throws ControleException, DadosException {
		this.ctrlDepartamentos = new CtrlManterDepartamentos(this);
	}
	
	/**
	 * 
	 */
	public void terminarCasoDeUsoManterDepartamentos() throws ControleException {
		this.ctrlDepartamentos.terminar();
		this.ctrlDepartamentos = null;
	}
	
	/** 
	 * 
	 */
	public void iniciarCasoDeUsoManterEmpregados() throws ControleException, DadosException{
		// Instanciando os controladores de caso de uso do sistema
		this.ctrlEmpregados = new CtrlManterEmpregados(this);
	}
	
	/**
	 *  
	 */
	public void terminarCasoDeUsoManterEmpregados() throws ControleException{
		this.ctrlEmpregados.terminar();
		this.ctrlEmpregados = null;
	}
	
	/** 
	 * 
	 */
	public void iniciarCasoDeUsoManterProjetos() throws ControleException, DadosException{
		// Instanciando os controladores de caso de uso do sistema
		this.ctrlProjetos = new CtrlManterProjetos(this);
	}
	
	/**
	 *  
	 */
	public void terminarCasoDeUsoManterProjetos() throws ControleException{
		this.ctrlProjetos.terminar();
		this.ctrlProjetos = null;
	}
	
	/**
	 * O m�todo main corresponde ao ponto inicial de execu��o
	 * de um programa em Java.
	 * @param args
	 */
	public static void main(String[] args) {
		CtrlPrograma prg = new CtrlPrograma();
		prg.iniciar();
	}
}
