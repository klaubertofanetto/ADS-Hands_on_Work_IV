package br.com.lpcollection.controller;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
	
	public static void main(String[] args) {
		Conexao conexao = new Conexao();
		conexao.conectar();
	}
	
	public Connection conectar() {
		Connection conexao = null;
		try {
			//jdbc : mysql : endereço : porta : database
			String url = "jdbc:mysql://localhost:3306/LPCollection";
			String usuario = "*****";
			String senha = "*****";
			
			conexao = DriverManager.getConnection(url, usuario, senha);
			System.out.println("Conectado com sucesso");
			
		} catch (Exception e) {
			System.out.println("Falha ao conectar com o banco" + e.getMessage());
			
		}
		
		
		return conexao;
	}
}
