package br.com.lpcollection.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import br.com.lpcollection.controller.TelaPrincipalController;

public class TelaPrincipal {

	private JFrame frame;
	private JTextField txtDisco;
	private JTextField txtMusico;
	private JTextField txtBanda;
	private JTextField txtGravadora;
	private JTextField txtAnoLancamento;
	private JTable tableMusicos;
	private JTable tableDiscos;
	private JTextField txtPesquisar;
	private JLabel lblDisco;
	private JLabel lblBanda;
	private JLabel lblGravadora;
	private JLabel lblAnoDeLancamento;
	private JLabel lblMusico;
	private JButton btnAcrescentarMusico;
	private JButton btnExcluir;
	private JButton btnSalvar;
	private JButton btnCancelar;
	private JButton btnAlterar;
	private JButton btnCadastrarMusico;
	private JButton btnPesquisarMusico;
	private JButton btnListar;
	private JButton btnPesquisar;
	private JScrollPane scrollPaneDiscos;
	private JScrollPane scrollPaneMusicos;
	private JComboBox<String> filtroPesquisa;
	private int idDisco;
	private int idMusico;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal window = new TelaPrincipal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("LP Collection");
		frame.setBounds(100, 100, 900, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		// SEÇÃO CADASTRAR
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, false));
		panel.setBounds(30, 30, 840, 220);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		lblDisco = new JLabel("Disco");
		lblDisco.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblDisco.setBounds(10, 15, 205, 20);
		panel.add(lblDisco);

		txtDisco = new JTextField();
		txtDisco.setBounds(10, 40, 190, 30);
		panel.add(txtDisco);
		txtDisco.setColumns(10);

		lblBanda = new JLabel("Banda");
		lblBanda.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblBanda.setBounds(215, 15, 205, 20);
		panel.add(lblBanda);

		txtBanda = new JTextField();
		txtBanda.setBounds(215, 40, 190, 30);
		panel.add(txtBanda);
		txtBanda.setColumns(10);

		lblGravadora = new JLabel("Gravadora");
		lblGravadora.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblGravadora.setBounds(420, 15, 205, 20);
		panel.add(lblGravadora);

		txtGravadora = new JTextField();
		txtGravadora.setBounds(420, 40, 190, 30);
		panel.add(txtGravadora);
		txtGravadora.setColumns(10);

		lblAnoDeLancamento = new JLabel("Ano de Lançamento");
		lblAnoDeLancamento.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblAnoDeLancamento.setBounds(625, 15, 205, 20);
		panel.add(lblAnoDeLancamento);

		txtAnoLancamento = new JTextField();
		txtAnoLancamento.setBounds(625, 40, 205, 30);
		panel.add(txtAnoLancamento);
		txtAnoLancamento.setColumns(10);

		lblMusico = new JLabel("Músico");
		lblMusico.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblMusico.setBounds(10, 80, 205, 20);
		panel.add(lblMusico);

		txtMusico = new JTextField();
		txtMusico.setBounds(10, 105, 190, 30);
		panel.add(txtMusico);
		txtMusico.setColumns(10);

		scrollPaneMusicos = new JScrollPane();
		scrollPaneMusicos.setBounds(215, 80, 395, 125);
		panel.add(scrollPaneMusicos);
		tableMusicos = new JTable();
		tableMusicos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				idMusico = TelaPrincipalController.selecionaLinhaMusico(txtMusico, tableMusicos, btnCancelar,
						btnPesquisarMusico, btnAcrescentarMusico);

			}

		});
		tableMusicos.setFont(new Font("Dialog", Font.PLAIN, 12));
		tableMusicos.setRowHeight(22);
		scrollPaneMusicos.setViewportView(tableMusicos);

		btnPesquisarMusico = new JButton("Buscar / Listar todos");
		btnPesquisarMusico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableMusicos.setModel(TelaPrincipalController.listarMusicos(txtMusico));
				tableMusicos.getColumn(tableMusicos.getColumnName(0)).setMaxWidth(30);
			}
		});
		btnPesquisarMusico.setBounds(10, 145, 190, 20);
		panel.add(btnPesquisarMusico);

		btnAcrescentarMusico = new JButton("Add músico ao disco");
		btnAcrescentarMusico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaPrincipalController.cadastraMusicoAoDisco(txtDisco, txtBanda, txtGravadora, txtAnoLancamento, txtMusico,
						tableDiscos, tableMusicos, btnExcluir, btnAlterar, btnSalvar, btnCancelar, btnPesquisarMusico,
						btnAcrescentarMusico, idMusico, idDisco);
			}
		});
		btnAcrescentarMusico.setBounds(10, 175, 190, 20);
		panel.add(btnAcrescentarMusico);
		btnAcrescentarMusico.setEnabled(false);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaPrincipalController.resetaDiscos(txtDisco, txtBanda, txtGravadora, txtAnoLancamento, txtMusico,
						tableDiscos, tableMusicos, btnExcluir, btnAlterar, btnSalvar, btnCancelar, btnPesquisarMusico,
						btnAcrescentarMusico);
			}
		});
		btnCancelar.setBounds(630, 81, 195, 24);
		panel.add(btnCancelar);
		btnCancelar.setEnabled(false);

		btnExcluir = new JButton("Excluir Disco");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaPrincipalController.apagaDisco(txtDisco, txtBanda, txtGravadora, txtAnoLancamento, txtMusico,
						tableDiscos, btnExcluir, btnAlterar, btnSalvar, btnCancelar, btnPesquisarMusico,
						btnAcrescentarMusico, idDisco);
			}
		});
		btnExcluir.setBounds(630, 114, 195, 24);
		panel.add(btnExcluir);
		btnExcluir.setEnabled(false);

		btnAlterar = new JButton("Alterar Disco");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaPrincipalController.alteraDisco(txtDisco, txtBanda, txtGravadora, txtAnoLancamento, txtMusico,
						tableDiscos, btnExcluir, btnAlterar, btnSalvar, btnCancelar, btnPesquisarMusico,
						btnAcrescentarMusico, idDisco);
			}
		});
		btnAlterar.setBounds(630, 147, 195, 24);
		panel.add(btnAlterar);
		btnAlterar.setEnabled(false);
		btnSalvar = new JButton("Salvar Disco");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaPrincipalController.cadastraDisco(txtDisco, txtBanda, txtGravadora, txtAnoLancamento, txtMusico,
						tableDiscos, btnExcluir, btnAlterar, btnSalvar, btnCancelar, btnPesquisarMusico,
						btnAcrescentarMusico);
			}
		});
		btnSalvar.setBounds(630, 180, 195, 24);
		panel.add(btnSalvar);

		// SEÇÃO LISTAR
		JPanel panel2 = new JPanel();
		panel2.setBounds(30, 260, 840, 520);
		frame.getContentPane().add(panel2);
		panel2.setLayout(null);

		btnCadastrarMusico = new JButton("Cadastrar músico");
		btnCadastrarMusico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaMusicos.exibeTelaMusicos();
			}
		});
		btnCadastrarMusico.setBounds(500, 0, 340, 30);
		panel2.add(btnCadastrarMusico);
		txtPesquisar = new JTextField();
		txtPesquisar.setText("Pesquisar");
		txtPesquisar.setForeground(Color.GRAY);
		txtPesquisar.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtPesquisar.getText().equals("Pesquisar")) {
					txtPesquisar.setText("");
					txtPesquisar.setForeground(Color.BLACK);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtPesquisar.getText().isEmpty()) {
					txtPesquisar.setForeground(Color.GRAY);
					txtPesquisar.setText("Pesquisar");
				}
			}
		});

		txtPesquisar.setBounds(0, 40, 290, 30);
		panel2.add(txtPesquisar);
		txtPesquisar.setColumns(10);

		filtroPesquisa = new JComboBox<String>();
		String disco = "por nome do disco";
		String banda = "por banda";
		String musico = "por músico";
		filtroPesquisa.setModel(new DefaultComboBoxModel<String>(new String[] { disco, banda, musico }));
		filtroPesquisa.setBounds(310, 40, 160, 30);
		panel2.add(filtroPesquisa);

		btnListar = new JButton("Listar todos");
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableDiscos.setModel(TelaPrincipalController.listarTabelaDiscos());
				tableDiscos.getColumn(tableDiscos.getColumnName(0)).setMaxWidth(40);
			}
		});
		btnListar.setBounds(680, 40, 160, 30);
		panel2.add(btnListar);

		btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableDiscos.setModel(TelaPrincipalController.pesquisarDiscos(filtroPesquisa, txtPesquisar));
			}
		});
		btnPesquisar.setBounds(500, 40, 160, 30);
		panel2.add(btnPesquisar);

		scrollPaneDiscos = new JScrollPane();
		scrollPaneDiscos.setBounds(0, 90, 840, 370);
		panel2.add(scrollPaneDiscos);
		tableDiscos = new JTable();
		tableDiscos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				idDisco = TelaPrincipalController.selecionaLinha(txtDisco, txtBanda, txtGravadora, txtAnoLancamento,
						tableDiscos, btnExcluir, btnAlterar, btnSalvar, btnCancelar, btnAcrescentarMusico);

			}

		});
		tableDiscos.setModel(TelaPrincipalController.listarTabelaDiscos());
		tableDiscos.getColumn(tableDiscos.getColumnName(0)).setMaxWidth(40);
		tableDiscos.setFont(new Font("Dialog", Font.PLAIN, 14));
		tableDiscos.setRowHeight(24);
		scrollPaneDiscos.setViewportView(tableDiscos);

	}
}
