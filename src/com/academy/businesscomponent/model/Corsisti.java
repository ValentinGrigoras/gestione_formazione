package com.academy.businesscomponent.model;

public class Corsisti {
	private String nomeCorsista;
	private String cognomeCorsista;
	private long codCorsista;
	private byte precedentiFormativi;

	public String getNomeCorsista() {
		return nomeCorsista;
	}

	public void setNomeCorsista(String nomeCorsista) {
		this.nomeCorsista = nomeCorsista;
	}

	public String getCognomeCorsista() {
		return cognomeCorsista;
	}

	public void setCognomeCorsista(String cognomeCorsista) {
		this.cognomeCorsista = cognomeCorsista;
	}

	public long getCodCorsista() {
		return codCorsista;
	}

	public void setCodCorsista(long codCorsista) {
		this.codCorsista = codCorsista;
	}

	public byte getPrecedentiFormativi() {
		return precedentiFormativi;
	}

	public void setPrecedentiFormativi(byte precedentiFormativi) {
		this.precedentiFormativi = precedentiFormativi;
	}

	@Override
	public String toString() {
		return "Corsisti [nomeCorsista=" + nomeCorsista + ", cognomeCorsista=" + cognomeCorsista + ", codCorsista="
				+ codCorsista + ", precedentiFormativi=" + precedentiFormativi + "]";
	}

}
