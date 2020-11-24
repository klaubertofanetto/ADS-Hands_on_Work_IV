package br.com.lpcollection.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import br.com.lpcollection.model.Disco;
import br.com.lpcollection.model.Musico;

public class LPCollectionDao {

	public static void cadastraMusico(Musico musico) {

		try {
			Conexao conexao = new Conexao();
			String sql = "Insert into musico (nomeMusico, nacionalidade, dataNascimento) values (?, ?, ?)";

			PreparedStatement pstmt = conexao.conectar().prepareStatement(sql);
			pstmt.setString(1, musico.getNomeMusico());
			pstmt.setString(2, musico.getNacionalidade());
			pstmt.setDate(3, musico.getDataNascimento());
			pstmt.execute();
			JOptionPane.showMessageDialog(null, "Músico " + musico.getNomeMusico() + " cadastrado com sucesso");

		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, "Algo saiu errado. ");
			System.out.println(e2);
		}
	}

	public static DefaultTableModel listarMusicos(DefaultTableModel dados) {

		try {
			Conexao conexao = new Conexao();
			String sql = "select * from musico";
			Statement stmt = conexao.conectar().createStatement();

			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				dados.addRow(new Object[] { rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4) });
			}

		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, "Não foi possível carregar os dados da tabela");
			System.out.println(e);

		}

		return dados;
	}

	public static void updateMusico(Musico musico, int id) {

		try {
			Conexao conexao = new Conexao();
			String sql = "update musico set nomeMusico = ?, nacionalidade = ?, dataNascimento = ? where idMusico = ?";
			PreparedStatement pstmt = conexao.conectar().prepareStatement(sql);
			pstmt.setString(1, musico.getNomeMusico());
			pstmt.setString(2, musico.getNacionalidade());
			pstmt.setDate(3, musico.getDataNascimento());
			pstmt.setInt(4, id);
			pstmt.execute();

			JOptionPane.showMessageDialog(null, musico.getNomeMusico() + " alterado com sucesso");

		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, "Algo saiu errado. " + e2.getMessage());
		}
	}

	public static void deleteMusico(int id) {
		try {
			Conexao conexao = new Conexao();
			String sql = "delete from musico where idMusico = ?";
			PreparedStatement pstmt = conexao.conectar().prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.execute();

			JOptionPane.showMessageDialog(null, "Músico apagado da base de dados");

		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, "Algo saiu errado. " + e2.getMessage());
		}
	}

	public static DefaultTableModel listarDiscos(DefaultTableModel dados) {

		try {
			Conexao conexao = new Conexao();
			String sql = "select * from disco";
			Statement stmt = conexao.conectar().createStatement();

			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				dados.addRow(new Object[] { rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						((rs.getString(5).equals("0")) ? "" : rs.getString(5)), "listar" });
			}

		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, "Não foi possível carregar os dados da tabela");
			System.out.println(e);

		}

		return dados;
	}

	public static DefaultTableModel readDiscos(DefaultTableModel dados, String coluna, String palavraChave) {
		try {
			Conexao conexao = new Conexao();
			String sql = "select * from disco where " + coluna + " like ?";
			PreparedStatement pstmt = conexao.conectar().prepareStatement(sql);
			pstmt.setString(1, palavraChave);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				dados.addRow(new Object[] { rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), "listar" });
			}

		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, "Algo saiu errado. " + e2.getMessage());
		}
		return dados;
	}

	public static void saveDisco(Disco disco) {

		try {
			Conexao conexao = new Conexao();
			String sql = "Insert into disco (nomeDisco, banda, gravadora, ano) values (?, ?, ?, ?)";

			PreparedStatement pstmt = conexao.conectar().prepareStatement(sql);
			pstmt.setString(1, disco.getNomeDoDisco());
			pstmt.setString(2, disco.getBanda());
			pstmt.setString(3, disco.getGravadora());
			pstmt.setInt(4, disco.getAno());
			pstmt.execute();
			JOptionPane.showMessageDialog(null, disco.getNomeDoDisco() + " cadastrado com sucesso");

		} catch (NullPointerException eNull) {

		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, "Algo saiu errado.");
			System.out.println(e2);
		}
	}

	public static void deleteDisco(int id) {
		try {
			Conexao conexao = new Conexao();
			String sql = "delete from disco where idDisco = ?";
			PreparedStatement pstmt = conexao.conectar().prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.execute();

			JOptionPane.showMessageDialog(null, "Disco apagado da base de dados");

		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, "Algo saiu errado. " + e2.getMessage());
		}
	}

	public static void updateDisco(Disco disco, int id) {

		try {
			Conexao conexao = new Conexao();
			String sql = "update disco set nomeDisco = ?, banda = ?, gravadora = ?, ano = ? where idDisco = ?";
			PreparedStatement pstmt = conexao.conectar().prepareStatement(sql);
			pstmt.setString(1, disco.getNomeDoDisco());
			pstmt.setString(2, disco.getBanda());
			pstmt.setString(3, disco.getGravadora());
			pstmt.setInt(4, disco.getAno());
			pstmt.setInt(5, id);
			pstmt.execute();
			JOptionPane.showMessageDialog(null, disco.getNomeDoDisco() + " atualizado com sucesso");

		} catch (NullPointerException eNull) {

		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, "Algo saiu errado. " + e2.getMessage());
		}
	}

	public static DefaultTableModel readMusicos(DefaultTableModel dados, String palavraChave) {
		try {
			Conexao conexao = new Conexao();
			String sql = "select * from musico where nomeMusico like ?";
			PreparedStatement pstmt = conexao.conectar().prepareStatement(sql);
			pstmt.setString(1, palavraChave);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				dados.addRow(new Object[] { rs.getInt(1), rs.getString(2) });
			}

		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, "Algo saiu errado. " + e2.getMessage());
		}
		return dados;
	}

	public static void insertFKs(int idMusico, int idDisco) {
		if (isMusicoPorDisco(idMusico, idDisco)) {
			JOptionPane.showMessageDialog(null, "Este músico já está vinculado a este disco.");
			return;
		}
		try {
			Conexao conexao = new Conexao();
			String sql = "Insert into musicoPorDisco (idMusico, idDisco) values (?, ?)";

			PreparedStatement pstmt = conexao.conectar().prepareStatement(sql);
			pstmt.setInt(1, idMusico);
			pstmt.setInt(2, idDisco);
			pstmt.execute();
			JOptionPane.showMessageDialog(null, "Músico cadastrado ao disco com sucesso");

		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, "Algo saiu errado. ");
			System.out.println(e2);
		}
	}

	public static DefaultTableModel readMusicosPorDisco(DefaultTableModel dados, int idDisco) {
		try {
			Conexao conexao = new Conexao();
			String sql = "select m.idMusico, nomeMusico, nacionalidade, dataNascimento from musico m left join musicoPorDisco mpd on m.idMusico = mpd.idMusico where idDisco = ? ";
			PreparedStatement pstmt = conexao.conectar().prepareStatement(sql);
			pstmt.setInt(1, idDisco);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				dados.addRow(new Object[] { rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4) });
			}

		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, "Algo saiu errado. " + e2.getMessage());
		}
		return dados;
	}

	public static void deleteMusicosPorDisco(int idMusico, int idDisco) {
		try {
			Conexao conexao = new Conexao();
			String sql = "delete from musicoPorDisco where idMusico = ? and idDisco = ?";
			PreparedStatement pstmt = conexao.conectar().prepareStatement(sql);
			pstmt.setInt(1, idMusico);
			pstmt.setInt(2, idDisco);
			pstmt.execute();

			JOptionPane.showMessageDialog(null, "Músico desvinculado do disco com sucesso");

		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, "Algo saiu errado. " + e2.getMessage());
		}
	}

	public static DefaultTableModel readDiscosPorMusico(DefaultTableModel dados, String palavraChave) {
		try {
			Conexao conexao = new Conexao();
			String sql = "select d.idDisco, nomeDisco, banda, gravadora, ano from disco d"
					+ " inner join musicoPorDisco mpd on d.idDisco = mpd.idDisco"
					+ " inner join musico m on m.idMusico = mpd.idMusico " + "where m.nomeMusico like ?";
			PreparedStatement pstmt = conexao.conectar().prepareStatement(sql);
			pstmt.setString(1, palavraChave);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				dados.addRow(new Object[] { rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						((rs.getString(5).equals("0")) ? "" : rs.getString(5)), "listar" });
			}

		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, "Algo saiu errado. " + e2.getMessage());
		}
		return dados;
	}

	public static boolean isMusicoPorDisco(int idMusico, int idDisco) {
		
		try {
			Conexao conexao = new Conexao();
			String sql = "select 1 from musicoPorDisco where idMusico = ? and idDisco =?";
			PreparedStatement pstmt;
			pstmt = conexao.conectar().prepareStatement(sql);
			pstmt.setInt(1, idMusico);
			pstmt.setInt(2, idDisco);
			ResultSet rs = pstmt.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
		

	}
}
