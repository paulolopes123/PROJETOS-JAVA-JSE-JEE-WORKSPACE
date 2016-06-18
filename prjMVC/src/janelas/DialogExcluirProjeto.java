package janelas;

import javax.swing.JOptionPane;

import controle.ControleException;
import controle.CtrlManterProjetos;
import dados.DadosException;

/**
 * Implementa��o da janela de confirma��o de exclus�o do Projeto
 * @author Alessandro Cerqueira
 *
 */
public class DialogExcluirProjeto {
	/**
	 * Refer�ncia para o controlador do caso de uso
	 */
	private CtrlManterProjetos ctrl;
	
	/**
	 * Op��o escolhida pelo usu�rio
	 */
	private int opcao;
	
	
	/**
	 * Construtor que ir� colocar uma janela modal perguntando
	 * se o usu�rio deseja ou n�o excluir o Projeto
	 * @param nome
	 */
	public DialogExcluirProjeto(CtrlManterProjetos ct, Object selecionado){
		// Guardo a refer�ncia para o controlador de caso de uso
		this.ctrl = ct;
		// Pergunto ao usu�rio o que ele deseja fazer
		this.opcao = JOptionPane.showConfirmDialog(null, "Deseja remover o Projeto " + selecionado);
		// Verifica o que o usu�rio indicou para ser feito
		try {
			if(this.opcao == JOptionPane.YES_OPTION)
				this.ctrl.excluir();
			else
				this.ctrl.cancelarExcluir();
		} catch(ControleException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		} catch(DadosException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * Retorna a op��o indicada pelo usu�rio
	 * @return
	 */
	public int getOpcao(){
		return this.opcao;
	}
}
