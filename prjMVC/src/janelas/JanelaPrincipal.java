package janelas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controle.ControleException;
import controle.CtrlPrograma;
import dados.DadosException;

/**
 * Classe que implementa a janela principal do sistema
 * @author Alessandro Cerqueira
 */
public class JanelaPrincipal extends JFrame {

	//
	// ATRIBUTOS
	//
	
	/**
	 * Refer�ncia para o painel de conte�do da janela
	 */
	private JPanel contentPane;
	/**
	 * Refer�ncia para o controlador do programa a quem a janela
	 * principal ir� sempre mandar a notifica��o.
	 */
	private CtrlPrograma ctrlPrg;
	
	//
	// M�TODOS
	//
	/**
	 * Create the frame.
	 */
	public JanelaPrincipal(CtrlPrograma p) {
		this.ctrlPrg = p;
		setTitle("Principal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 569, 150);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnDepartamentos = new JButton("Departamentos");
		btnDepartamentos.addActionListener(new ActionListener() {
			// M�todo acionado quando o bot�o "Departamentos" 
			// for pressionado (M�todo de Callback).
			public void actionPerformed(ActionEvent arg0) {
				try {
					ctrlPrg.iniciarCasoDeUsoManterDepartamentos();
				} catch(ControleException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
					e.printStackTrace();
				} catch(DadosException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
					e.printStackTrace();
				}
			}
		});
		btnDepartamentos.setBounds(10, 25, 124, 55);
		contentPane.add(btnDepartamentos);
		
		JButton btnEmpregados = new JButton("Empregados");
		btnEmpregados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// M�todo acionado quando o bot�o "Empregados" 
				// for pressionado (M�todo de Callback).
				try {
					ctrlPrg.iniciarCasoDeUsoManterEmpregados();
				} catch(ControleException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
					e.printStackTrace();
				} catch(DadosException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
					e.printStackTrace();
				}
			}
		});
		btnEmpregados.setBounds(144, 25, 124, 55);
		contentPane.add(btnEmpregados);
		
		JButton btnProjetos = new JButton("Projetos");
		btnProjetos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// M�todo acionado quando o bot�o "Projetos" 
				// for pressionado (M�todo de Callback).
				try {
					ctrlPrg.iniciarCasoDeUsoManterProjetos();
				} catch(ControleException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
					e.printStackTrace();
				} catch(DadosException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
					e.printStackTrace();
				}
			}
		});
		btnProjetos.setBounds(278, 25, 124, 55);
		contentPane.add(btnProjetos);

		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// M�todo acionado quando o bot�o "Sair" 
				// for pressionado (M�todo de Callback).
				ctrlPrg.terminar();
			}
		});
		btnSair.setBounds(412, 25, 124, 55);
		contentPane.add(btnSair);
		
		this.setVisible(true);
	}
}
