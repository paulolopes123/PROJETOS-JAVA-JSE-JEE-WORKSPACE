package janelas;

import javax.swing.JOptionPane;

import controle.ControleException;
import controle.CtrlManterProjetos;
import dados.DadosException;

/**
 * Implementação da janela de confirmação de exclusão do Projeto
 * @author Alessandro Cerqueira
 *
 */
public class DialogExcluirProjeto {
	/**
	 * Referência para o controlador do caso de uso
	 */
	private CtrlManterProjetos ctrl;
	
	/**
	 * Opção escolhida pelo usuário
	 */
	private int opcao;
	
	
	/**
	 * Construtor que irá colocar uma janela modal perguntando
	 * se o usuário deseja ou não excluir o Projeto
	 * @param nome
	 */
	public DialogExcluirProjeto(CtrlManterProjetos ct, Object selecionado){
		// Guardo a referência para o controlador de caso de uso
		this.ctrl = ct;
		// Pergunto ao usuário o que ele deseja fazer
		this.opcao = JOptionPane.showConfirmDialog(null, "Deseja remover o Projeto " + selecionado);
		// Verifica o que o usuário indicou para ser feito
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
	 * Retorna a opção indicada pelo usuário
	 * @return
	 */
	public int getOpcao(){
		return this.opcao;
	}
}
