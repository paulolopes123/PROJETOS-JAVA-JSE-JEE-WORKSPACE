package face;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controle.dao.ClienteJpaDAO;
import controle.dao.CtrlFormulario;
import dominio.Cliente;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JanelaFormulario extends JFrame {

	private JPanel contentPane;
	private JTextField jTextFieldID;
	private JTextField jTextFieldCpf;
	private JTextField jTextFieldNome;
	private JTextField jTextFieldRg;
	private CtrlFormulario ctrlFormulario;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public JanelaFormulario(CtrlFormulario p) {
		this.ctrlFormulario = p;
		setTitle("Formulario");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 650, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblId = new JLabel("ID");
		lblId.setBounds(80, 36, 46, 14);
		contentPane.add(lblId);

		jTextFieldID = new JTextField();
		jTextFieldID.setBounds(82, 61, 180, 20);
		contentPane.add(jTextFieldID);
		jTextFieldID.setColumns(10);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Cliente cliente = new Cliente();
				cliente.setCpf(jTextFieldCpf.getText());
				cliente.setId(Integer.parseInt(jTextFieldID.getText()));
				cliente.setNome(jTextFieldNome.getText());
				cliente.setRg(jTextFieldRg.getText());
				ClienteJpaDAO.getInstance().merge(cliente);
				clearFields();
				JOptionPane.showMessageDialog(null, "Salvo com Sucesso!!");

			}
		});
		btnSalvar.setBounds(53, 208, 89, 23);
		contentPane.add(btnSalvar);

		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setBounds(80, 107, 46, 14);
		contentPane.add(lblCpf);

		jTextFieldCpf = new JTextField();
		jTextFieldCpf.setBounds(82, 132, 180, 20);
		contentPane.add(jTextFieldCpf);
		jTextFieldCpf.setColumns(10);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(378, 36, 46, 14);
		contentPane.add(lblNome);

		jTextFieldNome = new JTextField();
		jTextFieldNome.setBounds(378, 61, 180, 20);
		contentPane.add(jTextFieldNome);
		jTextFieldNome.setColumns(10);

		JLabel lblRg = new JLabel("RG");
		lblRg.setBounds(378, 107, 46, 14);
		contentPane.add(lblRg);

		jTextFieldRg = new JTextField();
		jTextFieldRg.setBounds(375, 132, 183, 20);
		contentPane.add(jTextFieldRg);
		jTextFieldRg.setColumns(10);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int id = Integer.parseInt(JOptionPane.showInputDialog("Digite o id do cliente"));
				Cliente cliente = ClienteJpaDAO.getInstance().getById(id);
				if (cliente == null) {

					JOptionPane.showMessageDialog(null, "Cliente Inexistente!!");
				} else {
					jTextFieldCpf.setText(cliente.getCpf());
					jTextFieldID.setText(String.valueOf(cliente.getId()));
					jTextFieldNome.setText(cliente.getNome());
					jTextFieldRg.setText(cliente.getRg());
				}
			}
		});
		btnBuscar.setBounds(203, 208, 89, 23);
		contentPane.add(btnBuscar);

		JButton btnRemover = new JButton("Remover");
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(JOptionPane.showInputDialog("Digite o id do cliente"));
				ClienteJpaDAO.getInstance().removeById(id);
				clearFields();

			}
		});
		btnRemover.setBounds(351, 208, 89, 23);
		contentPane.add(btnRemover);

		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				System.exit(0);
			}
		});
		btnSair.setBounds(491, 208, 89, 23);
		contentPane.add(btnSair);
		setVisible(true);
	}

	private void clearFields() {
		jTextFieldCpf.setText("");
		jTextFieldID.setText("");
		jTextFieldNome.setText("");
		jTextFieldRg.setText("");
	}
}
