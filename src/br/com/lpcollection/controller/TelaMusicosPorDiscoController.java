package br.com.lpcollection.controller;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TelaMusicosPorDiscoController {
	
	public static DefaultTableModel listarMusicosPorDisco(int id) {
		DefaultTableModel dados = new DefaultTableModel();
		dados.addColumn("id");
		dados.addColumn("Nome");
		dados.addColumn("Nacionalidade");
		dados.addColumn("Data de Nascimento");
		LPCollectionDao.readMusicosPorDisco(dados, id);
		return dados;
		}
	
	public static int selecionaMusico(JButton excluir, JTable table) {
		int row = table.getSelectedRow();
		excluir.setEnabled(true);
		return Integer.parseInt(table.getValueAt(row, 0).toString());
	}
	
	public static void desvinculaMusico(int idMusico, int idDisco, JTable table, JButton btnExcluir) {
		LPCollectionDao.deleteMusicosPorDisco(idMusico, idDisco);
		table.setModel(listarMusicosPorDisco(idDisco));
		table.getColumn(table.getColumnName(0)).setMaxWidth(30);
		btnExcluir.setEnabled(false);
		
		
	}
}
