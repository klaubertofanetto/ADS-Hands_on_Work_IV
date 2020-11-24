package br.com.lpcollection.model;

public class Disco {
	private int idDisco;
	private String nomeDoDisco;
	private String banda;
	private String gravadora;
	private int ano;
	
	public Disco() {}
	
	public Disco(String nomeDoDisco, String banda, String gravadora, int ano) {
		super();
		this.nomeDoDisco = nomeDoDisco;
		this.banda = banda;
		this.gravadora = gravadora;
		this.ano = ano;
	}

	public int getIdDisco() {
		return idDisco;
	}

	public void setIdDisco(int idDisco) {
		this.idDisco = idDisco;
	}

	public String getNomeDoDisco() {
		return nomeDoDisco;
	}

	public void setNomeDoDisco(String nomeDoDisco) {
		this.nomeDoDisco = nomeDoDisco;
	}

	public String getBanda() {
		return banda;
	}

	public void setBanda(String banda) {
		this.banda = banda;
	}

	public String getGravadora() {
		return gravadora;
	}

	public void setGravadora(String gravadora) {
		this.gravadora = gravadora;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	@Override
	public String toString() {
		return "Disco [idDisco=" + idDisco + ", nomeDoDisco=" + nomeDoDisco + ", banda=" + banda + ", gravadora="
				+ gravadora + ", ano=" + ano + "]";
	}
	
	
}
