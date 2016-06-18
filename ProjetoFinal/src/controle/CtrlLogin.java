package controle;

import dominio.Aluno;
import dominio.DadosException;
import dominio.Professor;
import dominio.Usuario;
import face.JanelaEfetuarLogin;

public class CtrlLogin {
	
	private CtrlPrograma ctrlPrg;
	
	private JanelaEfetuarLogin jLogin;
	
	private int numTentativas = 0;

	public CtrlLogin(CtrlPrograma p) throws ControleException, DadosException {
		// Guardo a referência para o controlador do programa
		this.ctrlPrg = p;
		// Iniciando o caso de uso
		this.iniciar();
	}
			
	public void iniciar() throws ControleException, DadosException{
		// Abre a janela de login
		this.jLogin = new JanelaEfetuarLogin(this);			
	}

	public void validarContaSenha(String conta, String senha) throws ControleException, DadosException {
		Usuario usr = null;
		
		// Codificar a regra de validação da conta e senha
		//
		// Pegar DAOUsuario
		// Recuperar o usuário com o login
		// Ver se não está vazio
		// Verificar a senha do usuário
		// Ver se não está vazio
		//
		this.numTentativas++;
		try {
			Usuario.validarLogin(conta);
			Usuario.validarSenha(senha);
		} catch (DadosException e) {
			if(this.numTentativas >= 3)
				ctrlPrg.erroFatalLogin();
			throw e;
		}
		
		if(conta.equals("5404")  && senha.equals("123"))
			ctrlPrg.loginAlunoEfetuado((Aluno)usr);
		else if(conta.equals("5405")  && senha.equals("456"))
			ctrlPrg.loginProfessorEfetuado((Professor)usr);		
		else {			
			if(this.numTentativas >= 3)
				ctrlPrg.erroFatalLogin();
			throw new ControleException(new ErroDeControle(1,"Conta inexistente ou senha inválida - Tentativa " + numTentativas));
		}		
	}
	
	public void terminar() throws ControleException, DadosException{
		this.jLogin.setVisible(false);
		this.jLogin = null;
	}
	
	public void terminarJanela() throws ControleException, DadosException{
		this.jLogin.setVisible(true);
		//this.jLogin = null;
	}
	
}
