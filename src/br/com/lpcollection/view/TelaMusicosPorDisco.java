package br.com.lpcollection.view;

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

import br.com.lpcollection.controller.TelaMusicosPorDiscoController;
import br.com.lpcollection.model.Disco;


@SuppressWarnings("serial")
public class TelaMusicosPorDisco extends JDialog {
	
	private int idMusico;
	private JButton btnDelete;
	
	/**
	 * Launch the application.
	 */
	public static void exibeTela(int id, JTextField nomeDisco, JTextField banda) {
		try {
			Disco disco = new Disco();
			disco.setIdDisco(id);
			disco.setNomeDoDisco(nomeDisco.getText());
			disco.setBanda(banda.getText());
			TelaMusicosPorDisco dialog = new TelaMusicosPorDisco(disco);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public TelaMusicosPorDisco(Disco disco) {
		
		int idDisco = disco.getIdDisco();
	
		setBounds(250, 200, 600, 475);
		setTitle("LP Collection - Músicos por Disco");
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 600, 448);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblTexto = new JLabel();
		String nomeDisco = disco.getNomeDoDisco();
		String banda = disco.getBanda();
		String texto = String.format("<html>Músicos que participaram do disco %s, do %s: </html>", nomeDisco, banda);
		lblTexto.setText(texto);
		lblTexto.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblTexto.setBounds(40, 40, 520, 35);
		panel.add(lblTexto);

		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(40, 100, 520, 280);
		panel.add(scrollPane);
		JTable tableMusicos = new JTable();
		tableMusicos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				idMusico = TelaMusicosPorDiscoController.selecionaMusico(btnDelete, tableMusicos);
			}

		});
		tableMusicos.setModel(TelaMusicosPorDiscoController.listarMusicosPorDisco(idDisco));
		tableMusicos.getColumn(tableMusicos.getColumnName(0)).setMaxWidth(30);
		tableMusicos.setFont(new Font("Dialog", Font.PLAIN, 14));
		tableMusicos.setRowHeight(24);
		scrollPane.setViewportView(tableMusicos);
		
		btnDelete = new JButton("Desvincular músico deste disco");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaMusicosPorDiscoController.desvinculaMusico(idMusico, idDisco, tableMusicos, btnDelete);
 			}
		});
		btnDelete.setBounds(210, 396, 350, 20);
		panel.add(btnDelete);
		btnDelete.setEnabled(false);
		
	}

}
