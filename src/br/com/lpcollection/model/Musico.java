package br.com.lpcollection.model;

import java.sql.Date;

public class Musico {
	private int idMusico;
	private String nomeMusico;
	private String nacionalidade;
	private Date dataNascimento;
	
	public Musico() {}
	
	public Musico(String nomeMusico, String nacionalidade, String dataNascimento) {
		super();
		this.nomeMusico = nomeMusico;
		this.nacionalidade = nacionalidade;
		String str= dataNascimento;  
	    Date date=Date.valueOf(str);//converting string into sql date  
	    this.dataNascimento = date;
	}

	public int getIdMusico() {
		return idMusico;
	}

	public void setIdMusico(int idMusico) {
		this.idMusico = idMusico;
	}

	public String getNomeMusico() {
		return nomeMusico;
	}

	public void setNomeMusico(String nomeMusico) {
		this.nomeMusico = nomeMusico;
	}

	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	@Override
	public String toString() {
		return "Musico [idMusico=" + idMusico + ", nomeMusico=" + nomeMusico + ", nacionalidade=" + nacionalidade
				+ ", dataNascimento=" + dataNascimento + "]";
	}
	
	
	
}
