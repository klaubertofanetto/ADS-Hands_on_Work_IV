package br.com.lpcollection.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import br.com.lpcollection.controller.TelaMusicosController;

@SuppressWarnings("serial")
public class TelaMusicos extends JDialog {
	
	private JButton btnSalvar;
	private JButton btnAlterar;
	private JButton btnExcluir;
	private JButton btnCancelar;
	private JTable tableMusicos;
	private JLabel lblNome;
	private JLabel lblNacionalidade;
	private JLabel lblDataNasc;
	private JTextField txtNome;
	private JTextField txtNacionalidade;
	private JTextField txtDataNasc;
	private int id;

	public static void exibeTelaMusicos() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaMusicos dialog = new TelaMusicos();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public TelaMusicos() {
		setBounds(200, 200, 700, 500);
		setTitle("LP Collection - Cadastrar Músico");
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, false));
		panel.setBounds(0, 0, 700, 463);
		getContentPane().add(panel);
		panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 150, 640, 280);
		panel.add(scrollPane);
		tableMusicos = new JTable();
		tableMusicos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				id = TelaMusicosController.selecionaLinha(txtNome, txtNacionalidade, txtDataNasc, tableMusicos, btnExcluir, btnAlterar, btnSalvar, btnCancelar);
			}

		});
		tableMusicos.setModel(TelaMusicosController.listarTabelaMusicos());
		tableMusicos.getColumn(tableMusicos.getColumnName(0)).setMaxWidth(30);
		tableMusicos.setFont(new Font("Dialog", Font.PLAIN, 14));
		tableMusicos.setRowHeight(24);
		scrollPane.setViewportView(tableMusicos);

		lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblNome.setBounds(30, 30, 200, 20);
		panel.add(lblNome);

		txtNome = new JTextField();
		txtNome.setBounds(30, 55, 200, 30);
		panel.add(txtNome);
		txtNome.setColumns(10);

		lblNacionalidade = new JLabel("Nacionalidade");
		lblNacionalidade.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblNacionalidade.setBounds(250, 30, 200, 20);
		panel.add(lblNacionalidade);

		txtNacionalidade = new JTextField();
		txtNacionalidade.setBounds(250, 55, 200, 30);
		panel.add(txtNacionalidade);
		txtNacionalidade.setColumns(10);

		lblDataNasc = new JLabel("Data de Nascimento");
		lblDataNasc.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblDataNasc.setBounds(470, 30, 200, 20);
		panel.add(lblDataNasc);

		txtDataNasc = new JTextField();
		txtDataNasc.setBounds(470, 55, 200, 30);
		panel.add(txtDataNasc);
		txtDataNasc.setColumns(10);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaMusicosController.resetaMusicos(txtNome, txtNacionalidade, txtDataNasc, tableMusicos, btnExcluir, btnAlterar, btnSalvar, btnCancelar);
			}
		});
		btnCancelar.setBounds(30, 100, 145, 30);
		panel.add(btnCancelar);
		btnCancelar.setEnabled(false);
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaMusicosController.apagaMusico(txtNome, txtNacionalidade, txtDataNasc, tableMusicos, btnExcluir, btnAlterar, btnSalvar, btnCancelar, id);
			}
		});
		btnExcluir.setBounds(195, 100, 145, 30);
		panel.add(btnExcluir);
		btnExcluir.setEnabled(false);
		
		btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaMusicosController.atualizaMusico(txtNome, txtNacionalidade, txtDataNasc, tableMusicos, btnExcluir, btnAlterar, btnSalvar, btnCancelar, id);
			}
		});
		btnAlterar.setBounds(360, 100, 145, 30);
		panel.add(btnAlterar);
		btnAlterar.setEnabled(false);

		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaMusicosController.cadastraMusico(txtNome, txtNacionalidade, txtDataNasc, tableMusicos, btnExcluir, btnAlterar, btnSalvar, btnCancelar);
			}
		});
		btnSalvar.setBounds(525, 100, 145, 30);
		panel.add(btnSalvar);
	}

}
