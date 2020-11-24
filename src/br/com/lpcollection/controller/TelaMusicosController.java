package br.com.lpcollection.controller;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import br.com.lpcollection.model.Musico;

public class TelaMusicosController {

	public static void resetaMusicos(JTextField nome, JTextField nacionalidade, JTextField dataNascimento, JTable table, JButton excluir, JButton alterar, JButton salvar, JButton cancelar) {
		nome.setText("");
		nacionalidade.setText("");
		dataNascimento.setText("");
		table.setModel(listarTabelaMusicos());
		table.getColumn(table.getColumnName(0)).setMaxWidth(30);
		salvar.setEnabled(true);
		alterar.setEnabled(false);
		excluir.setEnabled(false);
		cancelar.setEnabled(false);
	}

	public static void cadastraMusico(JTextField nome, JTextField nacionalidade, JTextField dataNascimento, JTable table, JButton excluir, JButton alterar, JButton salvar, JButton cancelar) {
		Musico musico = new Musico(nome.getText(), nacionalidade.getText(), dataNascimento.getText());
		LPCollectionDao.cadastraMusico(musico);
		resetaMusicos(nome, nacionalidade, dataNascimento, table, excluir, alterar, salvar, cancelar);
	}

	public static DefaultTableModel listarTabelaMusicos() {
	DefaultTableModel dados = new DefaultTableModel();
	dados.addColumn("id");
	dados.addColumn("Nome");
	dados.addColumn("Nacionalidade");
	dados.addColumn("Data de Nascimento");
	LPCollectionDao.listarMusicos(dados);
	return dados;
	}
	
	public static int selecionaLinha(JTextField nome, JTextField nacionalidade, JTextField dataNascimento, JTable table, JButton excluir, JButton alterar, JButton salvar, JButton cancelar) {
		int row = table.getSelectedRow();
		nome.setText(table.getValueAt(row, 1).toString());
		nacionalidade.setText(table.getValueAt(row, 2).toString());
		dataNascimento.setText(table.getValueAt(row, 3).toString());

		salvar.setEnabled(false);
		alterar.setEnabled(true);
		excluir.setEnabled(true);
		cancelar.setEnabled(true);
		
		return Integer.parseInt(table.getValueAt(row, 0).toString());
	}
	
	public static void atualizaMusico(JTextField nome, JTextField nacionalidade, JTextField dataNascimento, JTable table, JButton excluir, JButton alterar, JButton salvar, JButton cancelar, int id) {
		Musico musico = new Musico(nome.getText(), nacionalidade.getText(), dataNascimento.getText());
		LPCollectionDao.updateMusico(musico, id);
		resetaMusicos(nome, nacionalidade, dataNascimento, table, excluir, alterar, salvar, cancelar);
	}
	
	public static void apagaMusico(JTextField nome, JTextField nacionalidade, JTextField dataNascimento, JTable table, JButton excluir, JButton alterar, JButton salvar, JButton cancelar, int id) {
		LPCollectionDao.deleteMusico(id);
		resetaMusicos(nome, nacionalidade, dataNascimento, table, excluir, alterar, salvar, cancelar);
	}
}
