package br.com.lpcollection.controller;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import br.com.lpcollection.model.Disco;
import br.com.lpcollection.view.TelaMusicosPorDisco;

public class TelaPrincipalController {
	public static DefaultTableModel listarTabelaDiscos() {
		DefaultTableModel dados = new DefaultTableModel();
		dados.addColumn("id");
		dados.addColumn("Disco");
		dados.addColumn("Banda");
		dados.addColumn("Gravadora");
		dados.addColumn("Ano de Lançamento");
		dados.addColumn("Músicos");
		LPCollectionDao.listarDiscos(dados);
		return dados;
	}
	
	public static DefaultTableModel esvaziarTabela() {
		DefaultTableModel dados = new DefaultTableModel();
		return dados;
	}

	public static DefaultTableModel pesquisarDiscos(JComboBox<String> filtroPesquisa, JTextField palavraChave) {
		int filtro = filtroPesquisa.getSelectedIndex();
		String keyWord = "%" + palavraChave.getText() + "%";
		String coluna = "";
		DefaultTableModel dados = new DefaultTableModel();
		dados.addColumn("id");
		dados.addColumn("Disco");
		dados.addColumn("Banda");
		dados.addColumn("Gravadora");
		dados.addColumn("Ano de Lançamento");
		dados.addColumn("Músicos");

		if (filtro == 2) {
			LPCollectionDao.readDiscosPorMusico(dados, keyWord);
			palavraChave.setText("Pesquisar");
			return dados;
		} else if (filtro == 1) {
			coluna = "banda";
		} else {
			coluna = "nomeDisco";
		}

		LPCollectionDao.readDiscos(dados, coluna, keyWord);
		palavraChave.setText("Pesquisar");
		return dados;
	}

	public static int selecionaLinha(JTextField disco, JTextField banda, JTextField gravadora, JTextField ano,
			JTable table, JButton excluir, JButton alterar, JButton salvar, JButton cancelar, JButton addMusico) {
		salvar.setEnabled(false);
		alterar.setEnabled(true);
		excluir.setEnabled(true);
		cancelar.setEnabled(true);
		addMusico.setEnabled(true);
		int row = table.getSelectedRow();
		String strDisco = table.getValueAt(row, 1).toString();
		String strBanda = table.getValueAt(row, 2).toString();
		String strGravadora = (table.getValueAt(row, 3) != null) ? table.getValueAt(row, 3).toString() : "";
		String strAno = (table.getValueAt(row, 4) != "0") ? table.getValueAt(row, 4).toString() : "";
		disco.setText(strDisco);
		banda.setText(strBanda);
		gravadora.setText(strGravadora);
		ano.setText(strAno);
		int id = Integer.parseInt(table.getValueAt(row, 0).toString());

		if (table.getSelectedColumn() == 5) {
			TelaMusicosPorDisco.exibeTela(id, disco, banda);
		}

		return id;
	}

	public static void resetaDiscos(JTextField disco, JTextField banda, JTextField gravadora, JTextField ano,
			JTextField musico, JTable table, JTable tableMusicos, JButton excluir, JButton alterar, JButton salvar,
			JButton cancelar, JButton pesquisarMusico, JButton addMusico) {
		resetaDiscos(disco, banda, gravadora, ano, musico, table, excluir, alterar, salvar, cancelar, pesquisarMusico, addMusico);
		tableMusicos.setModel(esvaziarTabela());

	}

	public static void resetaDiscos(JTextField disco, JTextField banda, JTextField gravadora, JTextField ano,
			JTextField musico, JTable table, JButton excluir, JButton alterar, JButton salvar, JButton cancelar,
			JButton pesquisarMusico, JButton addMusico) {
		salvar.setEnabled(true);
		alterar.setEnabled(false);
		excluir.setEnabled(false);
		cancelar.setEnabled(false);
		pesquisarMusico.setEnabled(true);
		addMusico.setEnabled(false);

		disco.setText("");
		banda.setText("");
		gravadora.setText("");
		ano.setText("");
		musico.setText("");
		table.setModel(listarTabelaDiscos());
		table.getColumn(table.getColumnName(0)).setMaxWidth(40);

	}

	public static void cadastraDisco(JTextField nomeDisco, JTextField banda, JTextField gravadora, JTextField ano,
			JTextField musico, JTable table, JButton excluir, JButton alterar, JButton salvar, JButton cancelar,
			JButton pesquisarMusico, JButton addMusico) {
		Disco disco = setDisco(nomeDisco, banda, gravadora, ano);
		LPCollectionDao.saveDisco(disco);
		resetaDiscos(nomeDisco, banda, gravadora, ano, musico, table, excluir, alterar, salvar, cancelar,
				pesquisarMusico, addMusico);

	}

	public static void apagaDisco(JTextField nomeDisco, JTextField banda, JTextField gravadora, JTextField ano,
			JTextField musico, JTable table, JButton excluir, JButton alterar, JButton salvar, JButton cancelar,
			JButton pesquisarMusico, JButton addMusico, int id) {
		LPCollectionDao.deleteDisco(id);
		resetaDiscos(nomeDisco, banda, gravadora, ano, musico, table, excluir, alterar, salvar, cancelar,
				pesquisarMusico, addMusico);

	}

	public static void alteraDisco(JTextField nomeDisco, JTextField banda, JTextField gravadora, JTextField ano,
			JTextField musico, JTable table, JButton excluir, JButton alterar, JButton salvar, JButton cancelar,
			JButton pesquisarMusico, JButton addMusico, int id) {
		Disco disco = setDisco(nomeDisco, banda, gravadora, ano);
		LPCollectionDao.updateDisco(disco, id);
		resetaDiscos(nomeDisco, banda, gravadora, ano, musico, table, excluir, alterar, salvar, cancelar,
				pesquisarMusico, addMusico);
	}

	public static Disco setDisco(JTextField nomeDisco, JTextField banda, JTextField gravadora, JTextField ano) {
		if (nomeDisco.getText().isEmpty() || banda.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Disco e Banda são campos obrigatórios");
			return null;
		}
		String strDisco = nomeDisco.getText();
		String strBanda = banda.getText();
		String strGravadora = (gravadora.getText().isEmpty() ? "" : gravadora.getText());
		int intAno = (ano.getText().isEmpty() ? 0 : Integer.parseInt(ano.getText()));
		Disco disco = new Disco(strDisco, strBanda, strGravadora, intAno);
		return disco;
	}

	public static DefaultTableModel listarMusicos(JTextField palavraChave) {

		String keyWord = "%" + palavraChave.getText() + "%";

		DefaultTableModel dados = new DefaultTableModel();
		dados.addColumn("id");
		dados.addColumn("Nome");
		LPCollectionDao.readMusicos(dados, keyWord);
		palavraChave.setText("");
		return dados;
	}

	public static int selecionaLinhaMusico(JTextField musico, JTable table, JButton cancelar, JButton pesquisarMusico,
			JButton addMusico) {
		pesquisarMusico.setEnabled(false);
		addMusico.setEnabled(true);
		cancelar.setEnabled(true);
		int row = table.getSelectedRow();
		musico.setText(table.getValueAt(row, 1).toString());
		return Integer.parseInt(table.getValueAt(row, 0).toString());
	}

	public static void cadastraMusicoAoDisco(JTextField nomeDisco, JTextField banda, JTextField gravadora,
			JTextField ano, JTextField musico, JTable table, JTable tableMusicos, JButton excluir, JButton alterar, JButton salvar,
			JButton cancelar, JButton pesquisarMusico, JButton addMusico, int idMusico, int idDisco) {
		if (nomeDisco.getText().isEmpty() || musico.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Selecione um músico e um disco.");
			return;
		}
		LPCollectionDao.insertFKs(idMusico, idDisco);
		resetaDiscos(nomeDisco, banda, gravadora, ano, musico, table, tableMusicos, excluir, alterar, salvar, cancelar,
				pesquisarMusico, addMusico);

	}

}
