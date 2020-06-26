package com.academy.businesscomponent.model;

public class Docenti {
	private String nomeDocente;
	private String cognomeDocente;
	private String cvDocente;
	private long codDocente;
	
	public String getNomeDocente() {
		return nomeDocente;
	}
	public void setNomeDocente(String nomeDocente) {
		this.nomeDocente = nomeDocente;
	}
	public String getCognomeDocente() {
		return cognomeDocente;
	}
	public void setCognomeDocente(String cognomeDocente) {
		this.cognomeDocente = cognomeDocente;
	}
	public String getCvDocente() {
		return cvDocente;
	}
	public void setCvDocente(String cvDocente) {
		this.cvDocente = cvDocente;
	}
	public long getCodDocente() {
		return codDocente;
	}
	public void setCodDocente(long codDocente) {
		this.codDocente = codDocente;
	}
	
	@Override
	public String toString() {
		return "Docenti [nomeDocente=" + nomeDocente + ", cognomeDocente=" + cognomeDocente + ", cvDocente=" + cvDocente
				+ ", codDocente=" + codDocente + "]";
	}
	
}
