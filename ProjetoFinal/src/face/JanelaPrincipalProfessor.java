package face;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controle.ControleException;
import controle.CtrlElaborarProva;
import controle.CtrlLogin;
import controle.CtrlPrograma;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import dominio.DadosException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JanelaPrincipalProfessor extends JFrame {

	private JPanel contentPane;
	private CtrlPrograma ctrlPrg;

	public JanelaPrincipalProfessor(CtrlPrograma c) {
		this.ctrlPrg = c;

		setTitle("Professor");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 650, 450);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnela = new JMenu("Elaborar");
		menuBar.add(mnela);

		JMenuItem mnproela = new JMenuItem("Prova(s)");
		mnproela.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					ctrlPrg.iniciarCasoDeUsoCtrlElaborarProva();
				} catch (ControleException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
					e.printStackTrace();
				} catch (DadosException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
					e.printStackTrace();
				}
			}
		});
		mnela.add(mnproela);

		JMenuItem mnquestela = new JMenuItem("Quest\u00E3o");
		mnquestela.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					ctrlPrg.iniciarCasoDeUsoCtrlElaborarQuestao();
				} catch (ControleException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
					e1.printStackTrace();
				} catch (DadosException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		mnela.add(mnquestela);

		JMenu mncorr = new JMenu("Corrigir/Consultar");
		menuBar.add(mncorr);

		JMenuItem mnprcorr = new JMenuItem("Prova(s) De Aluno");
		mnprcorr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					ctrlPrg.iniciarCasoDeUsoCtrlCorrigirProva();
				} catch (ControleException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
					e1.printStackTrace();
				} catch (DadosException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
					e1.printStackTrace();
				}

			}
		});
		mncorr.add(mnprcorr);

		JMenu mnNewMenu = new JMenu("Excluir");
		menuBar.add(mnNewMenu);

		JMenuItem mntmProvas = new JMenuItem("Prova(s)");
		mntmProvas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					ctrlPrg.iniciarCasoDeUsoCtrlExcluirProva();
				} catch (ControleException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
					e1.printStackTrace();
				} catch (DadosException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
					e1.printStackTrace();
				}

			}
		});
		mnNewMenu.add(mntmProvas);

		JMenuItem mntmQuesto = new JMenuItem("Quest\u00E3o");
		mntmQuesto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					ctrlPrg.iniciarCasoDeUsoCtrlExcluirQuestao();
				} catch (ControleException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
					e1.printStackTrace();
				} catch (DadosException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
					e1.printStackTrace();
				}

			}
		});
		mnNewMenu.add(mntmQuesto);

		JMenu mnalt = new JMenu("Alterar");
		menuBar.add(mnalt);

		JMenuItem mnpralt = new JMenuItem("Prova(s)");
		mnpralt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					ctrlPrg.iniciarCasoDeUsoCtrlAlterarProva();
				} catch (ControleException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
					e1.printStackTrace();
				} catch (DadosException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
					e1.printStackTrace();
				}

			}
		});
		mnalt.add(mnpralt);

		JMenuItem mnquestalt = new JMenuItem("Quest\u00E3o");
		mnquestalt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					ctrlPrg.iniciarCasoDeUsoCtrlAlterarQuestao();
				} catch (ControleException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
					e1.printStackTrace();
				} catch (DadosException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
					e1.printStackTrace();
				}

			}
		});
		mnalt.add(mnquestalt);

		JMenu mndupl = new JMenu("Duplicar");
		menuBar.add(mndupl);

		JMenuItem mnprdupl = new JMenuItem("Prova(s)");
		mnprdupl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					ctrlPrg.iniciarCasoDeUsoCtrlDuplicarProva();
				} catch (ControleException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
					e1.printStackTrace();
				} catch (DadosException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
					e1.printStackTrace();
				}
				
			}
		});
		mndupl.add(mnprdupl);

		JMenu mndispon = new JMenu("Disponibilizar");
		menuBar.add(mndispon);

		JMenuItem mnprdispon = new JMenuItem("Prova(s) Para Realiza\u00E7\u00E3o");
		mnprdispon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				try {
					ctrlPrg.iniciarCasoDeUsoCtrlDisponibilizarProva();
				} catch (ControleException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
					e1.printStackTrace();
				} catch (DadosException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
					e1.printStackTrace();
				}
				
			}
		});
		mndispon.add(mnprdispon);

		JMenu mnsair = new JMenu("Sair");
		menuBar.add(mnsair);

		JMenuItem mnsairsist = new JMenuItem("Sair Do Sistema");
		mnsairsist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				executarTerminar();
			}
		});
		mnsair.add(mnsairsist);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setLayout(null);
		this.setVisible(true);
	}

	public void executarTerminar() {
		ctrlPrg.terminar();
	}
}